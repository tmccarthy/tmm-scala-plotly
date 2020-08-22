package au.id.tmm.plotly.model.traceinterfaces

import au.id.tmm.plotly.model._
import au.id.tmm.plotly.model.utilities._
import cats.instances.vector.catsStdInstancesForVector
import cats.syntax.traverse.toTraverseOps
import com.github.ghik.silencer.silent

private[model] trait SunburstFactory { this: Trace.type =>

  object Sunburst {
    def apply(
      rootSectors: OptArg[Seq[Sector]]                           = OptArg.Undefined,
      name: OptArg[String]                                       = OptArg.Undefined,
      visible: OptArg[BooleanOr[Trace.Visibility]]               = OptArg.Undefined,
      opacity: OptArg[Number]                                    = OptArg.Undefined,
      globalTextTemplate: OptArg[String]                         = OptArg.Undefined,
      globalHoverText: OptArg[String]                            = OptArg.Undefined,
      hoverinfo: OptArg[Trace.HoverInfo]                         = OptArg.Undefined,
      globalHoverTemplate: OptArg[String]                        = OptArg.Undefined,
      domain: OptArg[Trace.Domain]                               = OptArg.Undefined,
      marker: OptArg[PlotMarker]                                 = OptArg.Undefined,
      textfont: OptArg[Font]                                     = OptArg.Undefined,
      textinfo: OptArg[Trace.TextInfo]                           = OptArg.Undefined,
      branchvalues: OptArg[Trace.BranchValues]                   = OptArg.Undefined,
      count: OptArg[FlagList[Trace.Count]]                       = OptArg.Undefined,
      hoverlabel: OptArg[HoverLabel]                             = OptArg.Undefined,
      hole: OptArg[Number]                                       = OptArg.Undefined,
      insidetextfont: OptArg[Font]                               = OptArg.Undefined,
      insidetextorientation: OptArg[Trace.InsideTextOrientation] = OptArg.Undefined,
      outsidetextfont: OptArg[Font]                              = OptArg.Undefined,
      leaf: OptArg[Trace.Leaf]                                   = OptArg.Undefined,
      level: OptArg[Datum]                                       = OptArg.Undefined,
      maxdepth: OptArg[Trace.MaxDepth]                           = OptArg.Undefined,
    ): Trace = {
      val allSectors: Vector[Sector.WithParent] = enumerateAllSectors(rootSectors.getOrElse(List.empty)).toVector

      val values = DataArray.Wrapping(allSectors.map(_.sector.value))

      val idPerSector: Map[Sector, String] = generateIdsFor(allSectors.map(_.sector))

      val labels: OptArg[DataArray] =
        spread(allSectors.map(_.sector.label)).map(DataArray.Wrapping)

      val texts: OptArg[OneOrArrayOf[String]] =
        spread(allSectors.map(_.sector.text), nullValue = "").map(OneOrArrayOf.Array.apply)

      val textTemplates: OptArg[OneOrArrayOf[String]] =
        spread(allSectors.map(_.sector.textTemplate), nullValue = "").map(OneOrArrayOf.Array.apply)

      val hoverTexts: OptArg[OneOrArrayOf[String]] =
        spread(allSectors.map(_.sector.hoverText), nullValue = "").map(OneOrArrayOf.Array.apply)

      val hoverTemplates: OptArg[OneOrArrayOf[String]] =
        spread(allSectors.map(_.sector.hoverTemplate), nullValue = "").map(OneOrArrayOf.Array.apply)

      val customData: OptArg[DataArray] =
        spread(allSectors.map(_.sector.customData)).map(DataArray.Wrapping.apply)

      val markers: Vector[OptArg[Sector.Marker]] = allSectors.map(_.sector.marker)

      // TODO the use of an empty string for colors is a bit dodgy. Would be better to have a general way of representing null
      val plotMarkerFromSectors: OptArg[PlotMarker] = {

        val colors: OptArg[Vector[Color]] =
          spread(markers.map(_.flatMap(_.color)), nullValue = Color(""))

        val lineFromSectors = {

          val linesPerSector: Vector[OptArg[Sector.Marker.Line]] = markers.map(_.flatMap(_.line))

          // TODO the use of sequence here is dodgy. Only necessary because we can't put a null width in :-/
          val width: OptArg[OneOrArrayOf[Number]] = linesPerSector
            .map(_.flatMap(_.width))
            .sequence
            .map(OneOrArrayOf.Array.apply)

          val color: OptArg[OneOrArrayOf[Color]] =
            spread(linesPerSector.map(_.flatMap(_.color)), nullValue = Color("")).map(OneOrArrayOf.Array.apply)

          if (width.isDefined || color.isDefined)
            OptArg.Of(PlotMarker.ScatterMarkerLine(width = width, color = color))
          else
            OptArg.Undefined
        }

        if (colors.isDefined || lineFromSectors.isDefined)
          OptArg.Of(PlotMarker(colors = colors, line = lineFromSectors))
        else
          OptArg.Undefined
      }

      val composedMarker: OptArg[PlotMarker] = merge(marker, plotMarkerFromSectors) { (marker, plotMarkerFromSectors) =>
        PlotMarker(
          color = plotMarkerFromSectors.color orElse marker.color,
          line = merge(marker.line, plotMarkerFromSectors.line) { (line, lineFromSectors) =>
            PlotMarker.ScatterMarkerLine(
              width = lineFromSectors.width orElse line.width,
              color = lineFromSectors.color orElse line.color,
            )
          },
        )
      }

      val parents: DataArray =
        DataArray.OfStrings(allSectors.map(s => s.parent.map(idPerSector).getOrElse("")))

      Trace(
        traceType = OptArg.Of(Trace.Type.Sunburst),
        values = OptArg.Of(values),
        parents = OptArg.Of(parents),
        ids = OptArg.Of(allSectors.map(s => idPerSector(s.sector))),
        labels = labels,
        text = texts,
        texttemplate = textTemplates orElse globalTextTemplate.map(OneOrArrayOf.One.apply),
        hovertext = hoverTexts orElse globalHoverText.map(OneOrArrayOf.One.apply),
        hovertemplate = hoverTemplates orElse globalHoverTemplate.map(OneOrArrayOf.One.apply),
        customdata = customData,
        marker = composedMarker,
        name = name,
        visible = visible,
        opacity = opacity,
        hoverinfo = hoverinfo,
        domain = domain,
        textfont = textfont,
        textinfo = textinfo,
        branchvalues = branchvalues,
        count = count,
        hoverlabel = hoverlabel,
        insidetextfont = insidetextfont,
        insidetextorientation = insidetextorientation,
        outsidetextfont = outsidetextfont,
        leaf = leaf,
        level = level,
        maxdepth = maxdepth,
      )
    }

    private def enumerateAllSectors(rootSectors: Seq[Sector]): Seq[Sector.WithParent] = {
      def go(sectors: Seq[Sector], parent: Option[Sector]): Seq[Sector.WithParent] = {
        val children = sectors.flatMap { sector =>
          go(sector.children, parent = Some(sector))
        }

        sectors.map(s => Sector.WithParent(s, parent)) ++ children
      }

      go(rootSectors, parent = None)
    }

    private def generateIdsFor(allSectors: Seq[Sector]): Map[Sector, String] = {
      def datumToString(datum: Datum): String =
        datum match {
          case Datum.OfNumber(number)       => number.toString
          case Datum.OfInstant(instant)     => instant.toString
          case Datum.OfLocalDate(localDate) => localDate.toString
          case Datum.OfString(string)       => string
          case Datum.OfNull                 => "<null>"
        }

      allSectors.zipWithIndex.map {
        case (sector, index) => {
          val description: String =
            sector.label.map(datumToString) orElse
              sector.text getOrElse
              datumToString(sector.value)

          val id = s"sector_${index}_$description"

          sector -> id
        }
      }.toMap
    }

    type Sector = SunburstFactory.Sector
    val Sector: SunburstFactory.Sector.type = SunburstFactory.Sector

  }

}

private[model] object SunburstFactory {

  @silent("outer reference")
  final case class Sector(
    value: Datum,
    label: OptArg[Datum]          = OptArg.Undefined,
    text: OptArg[String]          = OptArg.Undefined,
    textTemplate: OptArg[String]  = OptArg.Undefined,
    hoverText: OptArg[String]     = OptArg.Undefined,
    hoverTemplate: OptArg[String] = OptArg.Undefined,
    customData: OptArg[Datum]     = OptArg.Undefined,
    marker: OptArg[Sector.Marker] = OptArg.Undefined, // TODO need documentation on how this is composed
    children: Seq[Sector]         = List.empty,
  )

  object Sector {
    final case class Marker(
      color: OptArg[Color]      = OptArg.Undefined,
      line: OptArg[Marker.Line] = OptArg.Undefined,
    )

    object Marker {
      final case class Line(
        color: OptArg[Color]  = OptArg.Undefined,
        width: OptArg[Number] = OptArg.Undefined,
      ) {
        private[SunburstFactory] def asScatterMarkerLine: PlotMarker.ScatterMarkerLine =
          PlotMarker.ScatterMarkerLine(
            color = color.map(OneOrArrayOf.One.apply),
            width = width.map(OneOrArrayOf.One.apply),
          )
      }
    }

    private[SunburstFactory] final case class WithParent(sector: Sector, parent: Option[Sector])

  }

}
