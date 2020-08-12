package au.id.tmm.plotly.model

import au.id.tmm.plotly.model.utilities.OptArg
import io.circe.Encoder

final case class DataTitle(
  text: OptArg[String],
  font: OptArg[Font],
  position: OptArg[TextPosition],
)

object DataTitle {
  implicit val encoder: Encoder[DataTitle] = Encoder.forProduct3(
    "text",
    "font",
    "position",
  )(t =>
    (
      t.text,
      t.font,
      t.position,
    ),
  )
}
