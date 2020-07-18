package au.id.tmm.plotlyscalafacade.model

import io.circe.Encoder

final case class Margin(
  top: Option[Number] = None,
  bottom: Option[Number] = None,
  left: Option[Number] = None,
  right: Option[Number] = None,
  pad: Option[Number] = None,
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
