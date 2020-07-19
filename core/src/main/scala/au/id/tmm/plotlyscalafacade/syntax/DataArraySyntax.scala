package au.id.tmm.plotlyscalafacade.syntax

import java.time.{Instant, LocalDate, OffsetDateTime, ZonedDateTime}

import au.id.tmm.plotlyscalafacade.model.DataArray

trait DataArraySyntax {

  implicit def stringsToDataArray(strings: Seq[String]): DataArray.OfStrings = DataArray.OfStrings(strings)

  implicit def instantsToDataArray(instants: Seq[Instant]): DataArray.OfInstants = DataArray.OfInstants(instants)
  implicit def zonedDateTimesToDataArray(zonedDateTimes: Seq[ZonedDateTime]): DataArray.OfInstants = DataArray.OfZonedDateTimes(zonedDateTimes)
  implicit def offsetDateTimesToDataArray(offsetDateTimes: Seq[OffsetDateTime]): DataArray.OfInstants = DataArray.OfOffsetDateTimes(offsetDateTimes)
  implicit def localDatesToDataArray(localDates: Seq[LocalDate]): DataArray.OfLocalDates = DataArray.OfLocalDates(localDates)

  implicit def doublesToDataArray(doubles: Seq[Double]): DataArray.OfDoubles = DataArray.OfDoubles(doubles)
  implicit def floatsToDataArray(floats: Seq[Float]): DataArray.OfFloats     = DataArray.OfFloats(floats)
  implicit def longsToDataArray(longs: Seq[Long]): DataArray.OfLongs         = DataArray.OfLongs(longs)
  implicit def intsToDataArray(ints: Seq[Int]): DataArray.OfInts             = DataArray.OfInts(ints)

}
