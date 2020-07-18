package au.id.tmm.plotlyscalafacade.model

import io.circe.Encoder

final case class Font(
  family: Option[String] = None,
  size: Option[Number] = None,
  color: Option[Color] = None,
)

object Font {
  implicit val encoder: Encoder[Font] = Encoder.forProduct3("family", "size", "color")(f => (f.family, f.size, f.color))
}
