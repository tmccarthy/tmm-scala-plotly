package au.id.tmm.plotly.syntax

import java.time.{Instant, OffsetDateTime, ZonedDateTime}

import au.id.tmm.plotly.model.Datum
import au.id.tmm.plotly.model.utilities.OptArg

trait DatumSyntax {

  implicit def doubleToDatum(double: Double): Datum.OfNumber = Datum.OfNumber(double)
  implicit def floatToDatum(float: Float): Datum.OfNumber = Datum.OfNumber(float.toDouble)
  implicit def longToDatum(long: Long): Datum.OfNumber = Datum.OfNumber(long.toDouble)
  implicit def intToDatum(int: Int): Datum.OfNumber = Datum.OfNumber(int.toDouble)
  implicit def instantToDatum(instant: Instant): Datum.OfInstant = Datum.OfInstant(instant)
  implicit def zonedDateTimeToDatum(zonedDateTime: ZonedDateTime): Datum.OfInstant =
    Datum.OfZonedDateTime(zonedDateTime)
  implicit def offsetDateTimeToDatum(offsetDateTime: OffsetDateTime): Datum.OfInstant =
    Datum.OfOffsetDateTime(offsetDateTime)
  implicit def stringToDatum(string: String): Datum.OfString = Datum.OfString(string)

  implicit def doubleToDatumOptArg(double: Double): OptArg.Of[Datum.OfNumber] = OptArg.Of(Datum.OfNumber(double))
  implicit def floatToDatumOptArg(float: Float): OptArg.Of[Datum.OfNumber] = OptArg.Of(Datum.OfNumber(float.toDouble))
  implicit def longToDatumOptArg(long: Long): OptArg.Of[Datum.OfNumber] = OptArg.Of(Datum.OfNumber(long.toDouble))
  implicit def intToDatumOptArg(int: Int): OptArg.Of[Datum.OfNumber] = OptArg.Of(Datum.OfNumber(int.toDouble))
  implicit def instantToDatumOptArg(instant: Instant): OptArg.Of[Datum.OfInstant] = OptArg.Of(Datum.OfInstant(instant))
  implicit def zonedDateTimeToDatumOptArg(zonedDateTime: ZonedDateTime): OptArg.Of[Datum.OfInstant] =
    OptArg.Of(Datum.OfZonedDateTime(zonedDateTime))
  implicit def offsetDateTimeToDatumOptArg(offsetDateTime: OffsetDateTime): OptArg.Of[Datum.OfInstant] =
    OptArg.Of(Datum.OfOffsetDateTime(offsetDateTime))
  implicit def stringToDatumOptArg(string: String): OptArg.Of[Datum.OfString] = OptArg.Of(Datum.OfString(string))

}
