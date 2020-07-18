package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.Arg
import io.circe.Encoder

final case class ShapeLine(
  color: Arg[Color] = Arg.Undefined,
  width: Arg[Number] = Arg.Undefined,
  dash: Arg[Dash] = Arg.Undefined,
)

object ShapeLine {
  implicit val encoder: Encoder[ShapeLine] =
    Encoder.forProduct3("color", "width", "dash")(s => (s.color, s.width, s.dash))
}
