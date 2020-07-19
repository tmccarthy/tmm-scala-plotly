package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.OptArg
import io.circe.Encoder

final case class Font(
  family: OptArg[String] = OptArg.Undefined,
  size: OptArg[Number] = OptArg.Undefined,
  color: OptArg[Color] = OptArg.Undefined,
)

object Font {
  implicit val encoder: Encoder[Font] = Encoder.forProduct3("family", "size", "color")(f => (f.family, f.size, f.color))
}
