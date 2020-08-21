package au.id.tmm.plotly.model

import au.id.tmm.plotly.model.utilities.OptArg
import io.circe.Encoder

final case class Margin(
  top: OptArg[Number]    = OptArg.Undefined,
  bottom: OptArg[Number] = OptArg.Undefined,
  left: OptArg[Number]   = OptArg.Undefined,
  right: OptArg[Number]  = OptArg.Undefined,
  pad: OptArg[Number]    = OptArg.Undefined,
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
