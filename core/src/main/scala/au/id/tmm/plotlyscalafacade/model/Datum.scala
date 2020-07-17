package au.id.tmm.plotlyscalafacade.model

import io.circe.Encoder
import io.circe.syntax.EncoderOps

sealed trait Datum

object Datum {

  final case class OfNumber(number: Number) extends Datum
  final case class OfDate(date: Date)       extends Datum
  final case class OfString(string: String) extends Datum

  implicit val encoder: Encoder[Datum] = {
    case OfNumber(number) => number.asJson
    case OfDate(date)     => date.asJson
    case OfString(string) => string.asJson
  }

}
