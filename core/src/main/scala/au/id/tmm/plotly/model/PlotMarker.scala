package au.id.tmm.plotly.model

import au.id.tmm.plotly.model.utilities.{JSEnum, OneOrArrayOf, OptArg}
import io.circe.Encoder

final case class PlotMarker(
  symbol: OptArg[OneOrArrayOf[PlotMarker.Symbol]] = OptArg.Undefined,
  color: OptArg[OneOrArrayOf[Color]] = OptArg.Undefined,
  colors: OptArg[Seq[Color]] = OptArg.Undefined,
  colorscale: OptArg[ColorScale] = OptArg.Undefined,
  cauto: OptArg[Boolean] = OptArg.Undefined,
  cmax: OptArg[Number] = OptArg.Undefined,
  cmin: OptArg[Number] = OptArg.Undefined,
  autocolorscale: OptArg[Boolean] = OptArg.Undefined,
  reversescale: OptArg[Boolean] = OptArg.Undefined,
  opacity: OptArg[OneOrArrayOf[Number]] = OptArg.Undefined,
  size: OptArg[OneOrArrayOf[Number]] = OptArg.Undefined,
  maxdisplayed: OptArg[Number] = OptArg.Undefined,
  sizeref: OptArg[Number] = OptArg.Undefined,
  sizemax: OptArg[Number] = OptArg.Undefined,
  sizemin: OptArg[Number] = OptArg.Undefined,
  sizemode: OptArg[PlotMarker.SizeMode] = OptArg.Undefined,
  showscale: OptArg[Boolean] = OptArg.Undefined,
  line: OptArg[PlotMarker.ScatterMarkerLine] = OptArg.Undefined,
  pad: OptArg[Padding] = OptArg.Undefined,
  width: OptArg[Number] = OptArg.Undefined,
  colorbar: OptArg[PlotMarker.ColorBar] = OptArg.Undefined,
  gradient: OptArg[PlotMarker.Gradient] = OptArg.Undefined,
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
    width: OptArg[OneOrArrayOf[Number]] = OptArg.Undefined,
    // TODO this is either a colour, an array of colours or an array of numbers that is combined with the colorscale
    color: OptArg[OneOrArrayOf[Color]] = OptArg.Undefined,
    colorscale: OptArg[ColorScale] = OptArg.Undefined,
    cauto: OptArg[Boolean] = OptArg.Undefined,
    cmax: OptArg[Number] = OptArg.Undefined,
    cmin: OptArg[Number] = OptArg.Undefined,
    autocolorscale: OptArg[Boolean] = OptArg.Undefined,
    reversescale: OptArg[Boolean] = OptArg.Undefined,
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
    thicknessmode: OptArg[ColorBar.LineSizeMode] = OptArg.Undefined,
    thickness: OptArg[Number] = OptArg.Undefined,
    lenmode: OptArg[ColorBar.LineSizeMode] = OptArg.Undefined,
    len: OptArg[Number] = OptArg.Undefined,
    x: OptArg[Number] = OptArg.Undefined,
    xanchor: OptArg[Anchor.X] = OptArg.Undefined,
    xpad: OptArg[Number] = OptArg.Undefined,
    y: OptArg[Number] = OptArg.Undefined,
    yanchor: OptArg[Anchor.Y] = OptArg.Undefined,
    ypad: OptArg[Number] = OptArg.Undefined,
    outlinecolor: OptArg[Color] = OptArg.Undefined,
    outlinewidth: OptArg[Number] = OptArg.Undefined,
    bordercolor: OptArg[Color] = OptArg.Undefined,
    borderwidth: OptArg[Color] = OptArg.Undefined,
    bgcolor: OptArg[Color] = OptArg.Undefined,
    tickProperties: OptArg[TickProperties] = OptArg.Undefined,
    title: OptArg[String] = OptArg.Undefined,
    titlefont: OptArg[Font] = OptArg.Undefined,
    titleside: OptArg[ColorBar.TitleSide] = OptArg.Undefined,
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
    `type`: OptArg[OneOrArrayOf[Gradient.Type]] = OptArg.Undefined,
    color: OptArg[OneOrArrayOf[Color]] = OptArg.Undefined,
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

  implicit val encoder: Encoder[PlotMarker] = Encoder.forProduct22(
    "symbol",
    "color",
    "colors",
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
      p.colors,
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
