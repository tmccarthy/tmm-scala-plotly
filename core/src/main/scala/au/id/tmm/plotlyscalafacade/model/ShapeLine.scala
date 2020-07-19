package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.OptArg
import io.circe.Encoder

final case class ShapeLine(
  color: OptArg[Color] = OptArg.Undefined,
  width: OptArg[Number] = OptArg.Undefined,
  dash: OptArg[Dash] = OptArg.Undefined,
)

object ShapeLine {
  implicit val encoder: Encoder[ShapeLine] =
    Encoder.forProduct3("color", "width", "dash")(s => (s.color, s.width, s.dash))
}
