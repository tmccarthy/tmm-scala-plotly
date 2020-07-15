package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum

final case class ScatterLine(
  color: Color,
  width: Number,
  dash: Dash,
  shape: ScatterLine.Shape,
  smoothing: Number,
  simplify: Boolean,
)

object ScatterLine {

  sealed abstract class Shape(val asString: String) extends JSEnum

  object Shape {
    case object Linear extends Shape("linear")
    case object Spline extends Shape("spline")
    case object Hv     extends Shape("hv")
    case object Vh     extends Shape("vh")
    case object Hvh    extends Shape("hvh")
    case object Vhv    extends Shape("vhv")
  }

}
