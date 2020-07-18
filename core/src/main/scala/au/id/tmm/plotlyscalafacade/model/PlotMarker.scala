package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{JSEnum, OneOrArrayOf}
import io.circe.Encoder

final case class PlotMarker(
  symbol: Option[OneOrArrayOf[PlotMarker.Symbol]] = None,
  color: Option[OneOrArrayOf[Color]] = None,
  colorscale: Option[ColorScale] = None,
  cauto: Option[Boolean] = None,
  cmax: Option[Number] = None,
  cmin: Option[Number] = None,
  autocolorscale: Option[Boolean] = None,
  reversescale: Option[Boolean] = None,
  opacity: Option[OneOrArrayOf[Number]] = None,
  size: Option[OneOrArrayOf[Number]] = None,
  maxdisplayed: Option[Number] = None,
  sizeref: Option[Number] = None,
  sizemax: Option[Number] = None,
  sizemin: Option[Number] = None,
  sizemode: Option[PlotMarker.SizeMode] = None,
  showscale: Option[Boolean] = None,
  line: Option[PlotMarker.ScatterMarkerLine] = None,
  pad: Option[Padding] = None,
  width: Option[Number] = None,
  colorbar: Option[PlotMarker.ColorBar] = None,
  gradient: Option[PlotMarker.Gradient] = None,
)

object PlotMarker {

  sealed trait Symbol

  object Symbol {
    final case class OfString(string: String) extends Symbol
    final case class OfNumber(n: Number)      extends Symbol

    implicit val encoder: Encoder[Symbol] = {
      case OfString(string) => Encoder[String].apply(string)
      case OfNumber(n)      => Encoder[Number].apply(n)
    }
  }

  final case class ScatterMarkerLine(
    width: Option[OneOrArrayOf[Number]] = None,
    // TODO this is either a colour, an array of colours or an array of numbers that is combined with the colorscale
    color: Option[OneOrArrayOf[Color]] = None,
    colorscale: Option[ColorScale] = None,
    cauto: Option[Boolean] = None,
    cmax: Option[Number] = None,
    cmin: Option[Number] = None,
    autocolorscale: Option[Boolean] = None,
    reversescale: Option[Boolean] = None,
  )

  object ScatterMarkerLine {
    implicit val encoder: Encoder[ScatterMarkerLine] = Encoder.forProduct8(
      "width",
      "color",
      "colorscale",
      "cauto",
      "cmax",
      "cmin",
      "autocolorscale",
      "reversescale",
    )(s =>
      (
        s.width,
        s.color,
        s.colorscale,
        s.cauto,
        s.cmax,
        s.cmin,
        s.autocolorscale,
        s.reversescale,
      ),
    )
  }

  sealed abstract class SizeMode(val asString: String) extends JSEnum

  object SizeMode {
    case object Diameter extends SizeMode("diameter")
    case object Area     extends SizeMode("area")
  }

  final case class ColorBar(
    thicknessmode: Option[ColorBar.LineSizeMode] = None,
    thickness: Option[Number] = None,
    lenmode: Option[ColorBar.LineSizeMode] = None,
    len: Option[Number] = None,
    x: Option[Number] = None,
    xanchor: Option[Anchor.X] = None,
    xpad: Option[Number] = None,
    y: Option[Number] = None,
    yanchor: Option[Anchor.Y] = None,
    ypad: Option[Number] = None,
    outlinecolor: Option[Color] = None,
    outlinewidth: Option[Number] = None,
    bordercolor: Option[Color] = None,
    borderwidth: Option[Color] = None,
    bgcolor: Option[Color] = None,
    tickProperties: Option[TickProperties] = None,
    title: Option[String] = None,
    titlefont: Option[Font] = None,
    titleside: Option[ColorBar.TitleSide] = None,
  )

  object ColorBar {
    sealed abstract class LineSizeMode(val asString: String) extends JSEnum

    object LineSizeMode {
      case object Fraction extends LineSizeMode("fraction")
      case object Pixels   extends LineSizeMode("pixels")
    }

    sealed abstract class TitleSide(val asString: String) extends JSEnum

    object TitleSide {
      case object Right  extends TitleSide("right")
      case object Top    extends TitleSide("top")
      case object Bottom extends TitleSide("bottom")
    }

    implicit val encoder: Encoder[ColorBar] =
      (Encoder.forProduct18(
        "thicknessmode",
        "thickness",
        "lenmode",
        "len",
        "x",
        "xanchor",
        "xpad",
        "y",
        "yanchor",
        "ypad",
        "outlinecolor",
        "outlinewidth",
        "bordercolor",
        "borderwidth",
        "bgcolor",
        "title",
        "titlefont",
        "titleside",
      )(c =>
        (
          c.thicknessmode,
          c.thickness,
          c.lenmode,
          c.len,
          c.x,
          c.xanchor,
          c.xpad,
          c.y,
          c.yanchor,
          c.ypad,
          c.outlinecolor,
          c.outlinewidth,
          c.bordercolor,
          c.borderwidth,
          c.bgcolor,
          c.title,
          c.titlefont,
          c.titleside,
        ),
      ): Encoder[ColorBar])
        .mergeFieldsFrom(_.tickProperties)
  }

  final case class Gradient(
    `type`: Option[OneOrArrayOf[Gradient.Type]] = None,
    color: Option[OneOrArrayOf[Color]] = None,
  )

  object Gradient {
    sealed abstract class Type(val asString: String) extends JSEnum

    object Type {
      case object Radial     extends Type("radial")
      case object Horizontal extends Type("horizontal")
      case object Vertical   extends Type("vertical")
      case object None       extends Type("none")
    }

    implicit val encoder: Encoder[Gradient] = Encoder.forProduct2("type", "color")(g => (g.`type`, g.color))
  }

  implicit val encoder: Encoder[PlotMarker] = Encoder.forProduct21(
    "symbol",
    "color",
    "colorscale",
    "cauto",
    "cmax",
    "cmin",
    "autocolorscale",
    "reversescale",
    "opacity",
    "size",
    "maxdisplayed",
    "sizeref",
    "sizemax",
    "sizemin",
    "sizemode",
    "showscale",
    "line",
    "pad",
    "width",
    "colorbar",
    "gradient",
  )(p =>
    (
      p.symbol,
      p.color,
      p.colorscale,
      p.cauto,
      p.cmax,
      p.cmin,
      p.autocolorscale,
      p.reversescale,
      p.opacity,
      p.size,
      p.maxdisplayed,
      p.sizeref,
      p.sizemax,
      p.sizemin,
      p.sizemode,
      p.showscale,
      p.line,
      p.pad,
      p.width,
      p.colorbar,
      p.gradient,
    ),
  )

}
