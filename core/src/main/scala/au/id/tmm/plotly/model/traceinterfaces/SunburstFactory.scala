package au.id.tmm.plotly.model.traceinterfaces

import au.id.tmm.plotly.model._
import au.id.tmm.plotly.model.utilities._
import cats.instances.vector.catsStdInstancesForVector
import cats.syntax.traverse.toTraverseOps
import com.github.ghik.silencer.silent

trait SunburstFactory { this: Trace.type =>

  object Sunburst {
    def apply(
      rootSectors: OptArg[Seq[Sector]] = OptArg.Undefined,
      name: OptArg[String] = OptArg.Undefined,
      visible: OptArg[BooleanOr[Trace.Visibility]] = OptArg.Undefined,
      opacity: OptArg[Number] = OptArg.Undefined,
      globalTextTemplate: OptArg[String] = OptArg.Undefined,
      globalHoverText: OptArg[String] = OptArg.Undefined,
      hoverinfo: OptArg[Trace.HoverInfo] = OptArg.Undefined,
      globalHoverTemplate: OptArg[String] = OptArg.Undefined,
      domain: OptArg[Trace.Domain] = OptArg.Undefined,
      marker: OptArg[PlotMarker] = OptArg.Undefined,
      textfont: OptArg[Font] = OptArg.Undefined,
      textinfo: OptArg[Trace.TextInfo] = OptArg.Undefined,
      branchvalues: OptArg[Trace.BranchValues] = OptArg.Undefined,
      count: OptArg[FlagList[Trace.Count]] = OptArg.Undefined,
      hoverlabel: OptArg[HoverLabel] = OptArg.Undefined,
      insidetextfont: OptArg[Font] = OptArg.Undefined,
      insidetextorientation: OptArg[Trace.InsideTextOrientation] = OptArg.Undefined,
      outsidetextfont: OptArg[Font] = OptArg.Undefined,
      leaf: OptArg[Trace.Leaf] = OptArg.Undefined,
      level: OptArg[Datum] = OptArg.Undefined,
      maxdepth: OptArg[Trace.MaxDepth] = OptArg.Undefined,
    ): Trace = {
      val allSectors: Vector[Sector.WithParent] = enumerateAllSectors(rootSectors.getOrElse(List.empty)).toVector

      val values = DataArray.Wrapping(allSectors.map(_.sector.value))

      val ids: OptArg[Vector[String]] =
        unsquash(allSectors.map(_.sector.id), nullValue = "")

      val labels: OptArg[DataArray] =
        unsquash(allSectors.map(_.sector.label)).map(DataArray.Wrapping)

      val texts: OptArg[OneOrArrayOf[String]] =
        unsquash(allSectors.map(_.sector.text), nullValue = "").map(OneOrArrayOf.Array.apply)

      val textTemplates: OptArg[OneOrArrayOf[String]] =
        unsquash(allSectors.map(_.sector.textTemplate), nullValue = "").map(OneOrArrayOf.Array.apply)

      val hoverTexts: OptArg[OneOrArrayOf[String]] =
        unsquash(allSectors.map(_.sector.hoverText), nullValue = "").map(OneOrArrayOf.Array.apply)

      val hoverTemplates: OptArg[OneOrArrayOf[String]] =
        unsquash(allSectors.map(_.sector.hoverTemplate), nullValue = "").map(OneOrArrayOf.Array.apply)

      val customData: OptArg[DataArray] =
        unsquash(allSectors.map(_.sector.customData)).map(DataArray.Wrapping.apply)

      val markers: Vector[OptArg[Sector.Marker]] = allSectors.map(_.sector.marker)

      val composedMarker: OptArg[PlotMarker] = marker
        .map { existingMarker: PlotMarker =>
          existingMarker.copy(
            color = markers.map(_.flatMap(_.color)).sequence.map(OneOrArrayOf.Array.apply) orElse existingMarker.color,
            line = existingMarker.line.map { existingLine: PlotMarker.ScatterMarkerLine =>
              val linesPerSector: Vector[OptArg[Sector.Marker.Line]] = markers.map(_.flatMap(_.line))
              existingLine.copy(
                width = linesPerSector.map(_.flatMap(_.width)).sequence.map(OneOrArrayOf.Array.apply) orElse existingLine.width,
                color = linesPerSector.map(_.flatMap(_.color)).sequence.map(OneOrArrayOf.Array.apply) orElse existingLine.color,
              )
            }
          )
        }

      val parents: DataArray =
        if (ids.isDefined)
          DataArray.OfStrings(allSectors.map(_.parent.flatMap(_.id.toOption).getOrElse("")))
        else
          DataArray.Wrapping(allSectors.map(_.parent.map(_.value).getOrElse(Datum.OfString(""))))

      Trace(
        traceType = OptArg.Of(Trace.Type.Sunburst),
        values = OptArg.Of(values),
        parents = OptArg.Of(parents),
        ids = ids,
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

    @silent("outer reference")
    final case class Sector(
      value: Datum,
      id: OptArg[String] = OptArg.Undefined, // TODO should these be generated?
      label: OptArg[Datum] = OptArg.Undefined,
      text: OptArg[String] = OptArg.Undefined,
      textTemplate: OptArg[String] = OptArg.Undefined,
      hoverText: OptArg[String] = OptArg.Undefined,
      hoverTemplate: OptArg[String] = OptArg.Undefined,
      customData: OptArg[Datum] = OptArg.Undefined,
      marker: OptArg[Sector.Marker] = OptArg.Undefined, // TODO need documentation on how this is composed
      children: Seq[Sector] = List.empty,
    )

    object Sector {
      @silent("outer reference")
      final case class Marker(
        color: OptArg[Color],
        line: OptArg[Marker.Line],
      )

      object Marker {
        @silent("outer reference")
        final case class Line(
          color: OptArg[Color],
          width: OptArg[Number],
        ) {
          private[Sunburst] def asScatterMarkerLine: PlotMarker.ScatterMarkerLine = PlotMarker.ScatterMarkerLine(
            color = color.map(OneOrArrayOf.One.apply),
            width = width.map(OneOrArrayOf.One.apply),
          )
        }
      }

      @silent("outer reference")
      private[Sunburst] final case class WithParent(sector: Sector, parent: Option[Sector])

    }

  }

}
