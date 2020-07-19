package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.OptArg
import io.circe.Encoder

final case class Padding(
  top: OptArg[Number] = OptArg.Undefined,
  right: OptArg[Number] = OptArg.Undefined,
  bottom: OptArg[Number] = OptArg.Undefined,
  left: OptArg[Number] = OptArg.Undefined,
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
