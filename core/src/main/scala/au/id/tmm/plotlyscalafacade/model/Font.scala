package au.id.tmm.plotlyscalafacade.model

import io.circe.Encoder

final case class Font(
  family: String,
  size: Number,
  color: Color,
)

object Font {
  implicit val encoder: Encoder[Font] = Encoder.forProduct3("family", "size", "color")(f => (f.family, f.size, f.color))
}
