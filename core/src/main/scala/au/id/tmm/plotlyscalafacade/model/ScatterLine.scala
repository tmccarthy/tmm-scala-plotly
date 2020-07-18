package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{Arg, JSEnum}
import io.circe.Encoder

final case class ScatterLine(
  color: Arg[Color] = Arg.Undefined,
  width: Arg[Number] = Arg.Undefined,
  dash: Arg[Dash] = Arg.Undefined,
  shape: Arg[ScatterLine.Shape] = Arg.Undefined,
  smoothing: Arg[Number] = Arg.Undefined,
  simplify: Arg[Boolean] = Arg.Undefined,
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

  implicit val encoder: Encoder[ScatterLine] = Encoder.forProduct6(
    "color",
    "width",
    "dash",
    "shape",
    "smoothing",
    "simplify",
  )(s =>
    (
      s.color,
      s.width,
      s.dash,
      s.shape,
      s.smoothing,
      s.simplify,
    ),
  )

}
