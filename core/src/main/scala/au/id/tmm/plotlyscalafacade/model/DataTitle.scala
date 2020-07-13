package au.id.tmm.plotlyscalafacade.model

import io.circe.Encoder

final case class DataTitle(
  text: String,
  font: Partial[Font],
  position: TextPosition,
)

object DataTitle {
  implicit val encoder: Encoder[DataTitle] = _ => ???
}
