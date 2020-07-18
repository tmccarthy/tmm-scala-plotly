package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.Arg
import io.circe.Encoder

final case class Padding(
  top: Arg[Number] = Arg.Undefined,
  right: Arg[Number] = Arg.Undefined,
  bottom: Arg[Number] = Arg.Undefined,
  left: Arg[Number] = Arg.Undefined,
)

object Padding {
  implicit val encoder: Encoder[Padding] = Encoder.forProduct4(
    "top",
    "right",
    "bottom",
    "left",
  )(p =>
    (
      p.top,
      p.right,
      p.bottom,
      p.left,
    ),
  )
}
