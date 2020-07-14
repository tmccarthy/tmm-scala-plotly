package au.id.tmm.plotlyscalafacade.model

import io.circe.Encoder

final case class Color()

object Color {
  implicit val encoder: Encoder[Color] = _ => ??? // TODO encoder for Color
}
