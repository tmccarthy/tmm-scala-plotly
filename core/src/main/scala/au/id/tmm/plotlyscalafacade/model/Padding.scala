package au.id.tmm.plotlyscalafacade.model

import io.circe.Encoder

final case class Padding(
  top: Option[Number],
  right: Option[Number],
  bottom: Option[Number],
  left: Option[Number],
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
