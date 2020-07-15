package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{JSEnum, OneOrArrayOf}

final case class PlotMarker(
  symbol: PlotMarker.Symbol,
  color: OneOrArrayOf[Color],
  colors: Seq[Color],
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
  line: Partial[PlotMarker.ScatterMarkerLine],
  pad: Partial[Padding],
  width: Number,
  colorbar: Partial[PlotMarker.ColorBar],
  gradient: PlotMarker.Gradient,
)

object PlotMarker {

  sealed trait Symbol // TODO this will be fun

  final case class ScatterMarkerLine(
    width: OneOrArrayOf[Number],
    color: Color,
    colorscale: ColorScale,
    cauto: Boolean,
    cmax: Number,
    cmin: Number,
    autocolorscale: Boolean,
    reversescale: Boolean,
  )

  sealed abstract class SizeMode(val asString: String)

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
      case object Right extends TitleSide("right")
      case object Top extends TitleSide("top")
      case object Bottom extends TitleSide("bottom")
    }
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
  }

}
