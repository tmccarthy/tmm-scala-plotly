package au.id.tmm.plotlyscalafacade.syntax

import java.time.{Instant, OffsetDateTime, ZonedDateTime}

import au.id.tmm.plotlyscalafacade.model.Datum

trait DatumSyntax {

  implicit def doubleToDatum(double: Double): Datum.OfNumber     = Datum.OfNumber(double)
  implicit def floatToDatum(float: Float): Datum.OfNumber        = Datum.OfNumber(float.toDouble)
  implicit def longToDatum(long: Long): Datum.OfNumber           = Datum.OfNumber(long.toDouble)
  implicit def intToDatum(int: Int): Datum.OfNumber              = Datum.OfNumber(int.toDouble)
  implicit def instantToDatum(instant: Instant): Datum.OfInstant = Datum.OfInstant(instant)
  implicit def zonedDateTimeToDatum(zonedDateTime: ZonedDateTime): Datum.OfInstant =
    Datum.OfZonedDateTime(zonedDateTime)
  implicit def offsetDateTimeToDatum(offsetDateTime: OffsetDateTime): Datum.OfInstant =
    Datum.OfOffsetDateTime(offsetDateTime)
  implicit def stringToDatum(string: String): Datum.OfString = Datum.OfString(string)

}
