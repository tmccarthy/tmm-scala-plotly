package au.id.tmm.plotly.syntax

import java.time.{Instant, LocalDate, OffsetDateTime, ZonedDateTime}

import au.id.tmm.plotly.model.DataArray
import au.id.tmm.plotly.model.utilities.OptArg

trait DataArraySyntax {

  implicit def stringsToDataArray(strings: Seq[String]): DataArray.OfStrings = DataArray.OfStrings(strings)

  implicit def instantsToDataArray(instants: Seq[Instant]): DataArray.OfInstants = DataArray.OfInstants(instants)
  implicit def zonedDateTimesToDataArray(zonedDateTimes: Seq[ZonedDateTime]): DataArray.OfInstants =
    DataArray.OfZonedDateTimes(zonedDateTimes)
  implicit def offsetDateTimesToDataArray(offsetDateTimes: Seq[OffsetDateTime]): DataArray.OfInstants =
    DataArray.OfOffsetDateTimes(offsetDateTimes)
  implicit def localDatesToDataArray(localDates: Seq[LocalDate]): DataArray.OfLocalDates =
    DataArray.OfLocalDates(localDates)

  implicit def doublesToDataArray(doubles: Seq[Double]): DataArray.OfDoubles = DataArray.OfDoubles(doubles)
  implicit def floatsToDataArray(floats: Seq[Float]): DataArray.OfFloats     = DataArray.OfFloats(floats)
  implicit def longsToDataArray(longs: Seq[Long]): DataArray.OfLongs         = DataArray.OfLongs(longs)
  implicit def intsToDataArray(ints: Seq[Int]): DataArray.OfInts             = DataArray.OfInts(ints)

  implicit def stringsToOptArgDataArray(strings: Seq[String]): OptArg.Of[DataArray.OfStrings] =
    OptArg.Of(DataArray.OfStrings(strings))

  implicit def instantsToOptArgDataArray(instants: Seq[Instant]): OptArg.Of[DataArray.OfInstants] =
    OptArg.Of(DataArray.OfInstants(instants))
  implicit def zonedDateOptArgTimesToDataArray(zonedDateTimes: Seq[ZonedDateTime]): OptArg.Of[DataArray.OfInstants] =
    OptArg.Of(DataArray.OfZonedDateTimes(zonedDateTimes))
  implicit def offsetDateOptArgTimesToDataArray(offsetDateTimes: Seq[OffsetDateTime]): OptArg.Of[DataArray.OfInstants] =
    OptArg.Of(DataArray.OfOffsetDateTimes(offsetDateTimes))
  implicit def localDatesOptArgToDataArray(localDates: Seq[LocalDate]): OptArg.Of[DataArray.OfLocalDates] =
    OptArg.Of(DataArray.OfLocalDates(localDates))

  implicit def doublesToOptArgDataArray(doubles: Seq[Double]): OptArg.Of[DataArray.OfDoubles] =
    OptArg.Of(DataArray.OfDoubles(doubles))
  implicit def floatsToOptArgDataArray(floats: Seq[Float]): OptArg.Of[DataArray.OfFloats] =
    OptArg.Of(DataArray.OfFloats(floats))
  implicit def longsToOptArgDataArray(longs: Seq[Long]): OptArg.Of[DataArray.OfLongs] =
    OptArg.Of(DataArray.OfLongs(longs))
  implicit def intsToOptArgDataArray(ints: Seq[Int]): OptArg.Of[DataArray.OfInts] = OptArg.Of(DataArray.OfInts(ints))

}
