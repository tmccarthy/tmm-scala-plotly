package au.id.tmm.plotlyscalafacade.model

import io.circe.Encoder

final case class Padding(
  top: Option[Number] = None,
  right: Option[Number] = None,
  bottom: Option[Number] = None,
  left: Option[Number] = None,
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
