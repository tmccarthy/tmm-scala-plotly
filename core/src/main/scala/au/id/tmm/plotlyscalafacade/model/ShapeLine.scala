package au.id.tmm.plotlyscalafacade.model

import io.circe.Encoder

final case class ShapeLine(
  color: Option[Color] = None,
  width: Option[Number] = None,
  dash: Option[Dash] = None,
)

object ShapeLine {
  implicit val encoder: Encoder[ShapeLine] =
    Encoder.forProduct3("color", "width", "dash")(s => (s.color, s.width, s.dash))
}
