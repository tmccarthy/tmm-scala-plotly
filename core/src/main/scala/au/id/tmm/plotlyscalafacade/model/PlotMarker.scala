package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{Arg, JSEnum, OneOrArrayOf}
import io.circe.Encoder

final case class PlotMarker(
  symbol: Arg[OneOrArrayOf[PlotMarker.Symbol]] = Arg.Undefined,
  color: Arg[OneOrArrayOf[Color]] = Arg.Undefined,
  colorscale: Arg[ColorScale] = Arg.Undefined,
  cauto: Arg[Boolean] = Arg.Undefined,
  cmax: Arg[Number] = Arg.Undefined,
  cmin: Arg[Number] = Arg.Undefined,
  autocolorscale: Arg[Boolean] = Arg.Undefined,
  reversescale: Arg[Boolean] = Arg.Undefined,
  opacity: Arg[OneOrArrayOf[Number]] = Arg.Undefined,
  size: Arg[OneOrArrayOf[Number]] = Arg.Undefined,
  maxdisplayed: Arg[Number] = Arg.Undefined,
  sizeref: Arg[Number] = Arg.Undefined,
  sizemax: Arg[Number] = Arg.Undefined,
  sizemin: Arg[Number] = Arg.Undefined,
  sizemode: Arg[PlotMarker.SizeMode] = Arg.Undefined,
  showscale: Arg[Boolean] = Arg.Undefined,
  line: Arg[PlotMarker.ScatterMarkerLine] = Arg.Undefined,
  pad: Arg[Padding] = Arg.Undefined,
  width: Arg[Number] = Arg.Undefined,
  colorbar: Arg[PlotMarker.ColorBar] = Arg.Undefined,
  gradient: Arg[PlotMarker.Gradient] = Arg.Undefined,
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
    width: Arg[OneOrArrayOf[Number]] = Arg.Undefined,
    // TODO this is either a colour, an array of colours or an array of numbers that is combined with the colorscale
    color: Arg[OneOrArrayOf[Color]] = Arg.Undefined,
    colorscale: Arg[ColorScale] = Arg.Undefined,
    cauto: Arg[Boolean] = Arg.Undefined,
    cmax: Arg[Number] = Arg.Undefined,
    cmin: Arg[Number] = Arg.Undefined,
    autocolorscale: Arg[Boolean] = Arg.Undefined,
    reversescale: Arg[Boolean] = Arg.Undefined,
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
    thicknessmode: Arg[ColorBar.LineSizeMode] = Arg.Undefined,
    thickness: Arg[Number] = Arg.Undefined,
    lenmode: Arg[ColorBar.LineSizeMode] = Arg.Undefined,
    len: Arg[Number] = Arg.Undefined,
    x: Arg[Number] = Arg.Undefined,
    xanchor: Arg[Anchor.X] = Arg.Undefined,
    xpad: Arg[Number] = Arg.Undefined,
    y: Arg[Number] = Arg.Undefined,
    yanchor: Arg[Anchor.Y] = Arg.Undefined,
    ypad: Arg[Number] = Arg.Undefined,
    outlinecolor: Arg[Color] = Arg.Undefined,
    outlinewidth: Arg[Number] = Arg.Undefined,
    bordercolor: Arg[Color] = Arg.Undefined,
    borderwidth: Arg[Color] = Arg.Undefined,
    bgcolor: Arg[Color] = Arg.Undefined,
    tickProperties: Arg[TickProperties] = Arg.Undefined,
    title: Arg[String] = Arg.Undefined,
    titlefont: Arg[Font] = Arg.Undefined,
    titleside: Arg[ColorBar.TitleSide] = Arg.Undefined,
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
    `type`: Arg[OneOrArrayOf[Gradient.Type]] = Arg.Undefined,
    color: Arg[OneOrArrayOf[Color]] = Arg.Undefined,
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
