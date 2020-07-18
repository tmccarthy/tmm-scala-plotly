package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.Arg
import io.circe.Encoder

final case class Margin(
  top: Arg[Number] = Arg.Undefined,
  bottom: Arg[Number] = Arg.Undefined,
  left: Arg[Number] = Arg.Undefined,
  right: Arg[Number] = Arg.Undefined,
  pad: Arg[Number] = Arg.Undefined,
)

object Margin {
  implicit val encoder: Encoder[Margin] = Encoder.forProduct5(
    "top",
    "bottom",
    "left",
    "right",
    "pad",
  )(m =>
    (
      m.top,
      m.bottom,
      m.left,
      m.right,
      m.pad,
    ),
  )
}
