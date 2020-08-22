package au.id.tmm.plotly.syntax

import java.time.{Instant, LocalDate, OffsetDateTime, ZonedDateTime}

import au.id.tmm.plotly.model.DataArray

trait DataArraySyntax {

  implicit def stringsToDataArray(strings: Seq[String]): DataArray.OfStrings =
    DataArray.OfStrings(strings)

  implicit def instantsToDataArray(instants: Seq[Instant]): DataArray.OfInstants =
    DataArray.OfInstants(instants)

  implicit def zonedDateTimesToDataArray(zonedDateTimes: Seq[ZonedDateTime]): DataArray.OfInstants =
    DataArray.OfZonedDateTimes(zonedDateTimes)

  implicit def offsetDateTimesToDataArray(offsetDateTimes: Seq[OffsetDateTime]): DataArray.OfInstants =
    DataArray.OfOffsetDateTimes(offsetDateTimes)

  implicit def localDatesToDataArray(localDates: Seq[LocalDate]): DataArray.OfLocalDates =
    DataArray.OfLocalDates(localDates)

  implicit def doublesToDataArray(doubles: Seq[Double]): DataArray.OfDoubles =
    DataArray.OfDoubles(doubles)

  implicit def floatsToDataArray(floats: Seq[Float]): DataArray.OfFloats =
    DataArray.OfFloats(floats)

  implicit def longsToDataArray(longs: Seq[Long]): DataArray.OfLongs =
    DataArray.OfLongs(longs)

  implicit def intsToDataArray(ints: Seq[Int]): DataArray.OfInts =
    DataArray.OfInts(ints)

  implicit def onceNestedDoublesToDataArray(onceNestedDoubles: Seq[Seq[Double]]): DataArray.TwoDimensional =
    DataArray.TwoDimensional(onceNestedDoubles.map(doublesToDataArray))

  implicit def onceNestedFloatsToDataArray(onceNestedFloats: Seq[Seq[Float]]): DataArray.TwoDimensional =
    DataArray.TwoDimensional(onceNestedFloats.map(floatsToDataArray))

  implicit def onceNestedLongsToDataArray(onceNestedLongs: Seq[Seq[Long]]): DataArray.TwoDimensional =
    DataArray.TwoDimensional(onceNestedLongs.map(longsToDataArray))

  implicit def onceNestedIntsToDataArray(onceNestedInts: Seq[Seq[Int]]): DataArray.TwoDimensional =
    DataArray.TwoDimensional(onceNestedInts.map(intsToDataArray))

  implicit def twiceNestedDoublesToDataArray(twiceNestedDoubles: Seq[Seq[Seq[Double]]]): DataArray.ThreeDimensional =
    DataArray.ThreeDimensional(twiceNestedDoubles.map(onceNestedDoublesToDataArray))

  implicit def twiceNestedFloatsToDataArray(twiceNestedFloats: Seq[Seq[Seq[Float]]]): DataArray.ThreeDimensional =
    DataArray.ThreeDimensional(twiceNestedFloats.map(onceNestedFloatsToDataArray))

  implicit def twiceNestedLongsToDataArray(twiceNestedLongs: Seq[Seq[Seq[Long]]]): DataArray.ThreeDimensional =
    DataArray.ThreeDimensional(twiceNestedLongs.map(onceNestedLongsToDataArray))

  implicit def twiceNestedIntsToDataArray(twiceNestedInts: Seq[Seq[Seq[Int]]]): DataArray.ThreeDimensional =
    DataArray.ThreeDimensional(twiceNestedInts.map(onceNestedIntsToDataArray))

}
