package au.id.tmm.plotlyscalafacade.model

sealed trait Datum

object Datum {

  final case class OfNumber(number: Number)                 extends Datum
  final case class OfDate(date: Date)                       extends Datum
  final case class OfString(string: String)                 extends Datum

}
