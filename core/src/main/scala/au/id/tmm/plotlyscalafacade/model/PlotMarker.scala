package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{JSEnum, OneOrArrayOf}
import io.circe.Encoder

final case class PlotMarker(
  symbol: OneOrArrayOf[PlotMarker.Symbol],
  color: OneOrArrayOf[Color],
  colorscale: ColorScale,
  cauto: Boolean,
  cmax: Number,
  cmin: Number,
  autocolorscale: Boolean,
  reversescale: Boolean,
  opacity: OneOrArrayOf[Number],
  size: OneOrArrayOf[Number],
  maxdisplayed: Number,
  sizeref: Number,
  sizemax: Number,
  sizemin: Number,
  sizemode: PlotMarker.SizeMode,
  showscale: Boolean,
  line: PlotMarker.ScatterMarkerLine,
  pad: Padding,
  width: Number,
  colorbar: PlotMarker.ColorBar,
  gradient: PlotMarker.Gradient,
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
    width: OneOrArrayOf[Number],
    color: OneOrArrayOf[
      Color,
    ], // TODO this is either a colour, an array of colours or an array of numbers that is combined with the colorscale
    colorscale: ColorScale,
    cauto: Boolean,
    cmax: Number,
    cmin: Number,
    autocolorscale: Boolean,
    reversescale: Boolean,
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
    thicknessmode: ColorBar.LineSizeMode,
    thickness: Number,
    lenmode: ColorBar.LineSizeMode,
    len: Number,
    x: Number,
    xanchor: Anchor.X,
    xpad: Number,
    y: Number,
    yanchor: Anchor.Y,
    ypad: Number,
    outlinecolor: Color,
    outlinewidth: Number,
    bordercolor: Color,
    borderwidth: Color,
    bgcolor: Color,
    tickProperties: TickProperties,
    title: String,
    titlefont: Font,
    titleside: ColorBar.TitleSide,
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

    implicit val encoder: Encoder[ColorBar] = Encoder.forProduct19(
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
      "tickProperties",
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
        c.tickProperties,
        c.title,
        c.titlefont,
        c.titleside,
      ),
    )
  }

  final case class Gradient(
    `type`: OneOrArrayOf[Gradient.Type],
    color: OneOrArrayOf[Color],
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
