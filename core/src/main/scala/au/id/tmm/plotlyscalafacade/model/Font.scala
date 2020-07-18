package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.Arg
import io.circe.Encoder

final case class Font(
  family: Arg[String] = Arg.Undefined,
  size: Arg[Number] = Arg.Undefined,
  color: Arg[Color] = Arg.Undefined,
)

object Font {
  implicit val encoder: Encoder[Font] = Encoder.forProduct3("family", "size", "color")(f => (f.family, f.size, f.color))
}
