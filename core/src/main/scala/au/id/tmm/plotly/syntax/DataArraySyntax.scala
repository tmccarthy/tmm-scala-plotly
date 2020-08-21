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
  implicit def floatsToDataArray(floats: Seq[Float]): DataArray.OfFloats = DataArray.OfFloats(floats)
  implicit def longsToDataArray(longs: Seq[Long]): DataArray.OfLongs = DataArray.OfLongs(longs)
  implicit def intsToDataArray(ints: Seq[Int]): DataArray.OfInts = DataArray.OfInts(ints)

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

  implicit def onceNestedDoublesToOptArgDataArray(
    onceNestedDoubles: Seq[Seq[Double]],
  ): OptArg.Of[DataArray.TwoDimensional] =
    OptArg.Of(DataArray.TwoDimensional(onceNestedDoubles.map(doublesToDataArray)))
  implicit def onceNestedFloatsToOptArgDataArray(
    onceNestedFloats: Seq[Seq[Float]],
  ): OptArg.Of[DataArray.TwoDimensional] =
    OptArg.Of(DataArray.TwoDimensional(onceNestedFloats.map(floatsToDataArray)))
  implicit def onceNestedLongsToOptArgDataArray(onceNestedLongs: Seq[Seq[Long]]): OptArg.Of[DataArray.TwoDimensional] =
    OptArg.Of(DataArray.TwoDimensional(onceNestedLongs.map(longsToDataArray)))
  implicit def onceNestedIntsToOptArgDataArray(onceNestedInts: Seq[Seq[Int]]): OptArg.Of[DataArray.TwoDimensional] =
    OptArg.Of(DataArray.TwoDimensional(onceNestedInts.map(intsToDataArray)))

  implicit def twiceNestedDoublesToOptArgDataArray(
    twiceNestedDoubles: Seq[Seq[Seq[Double]]],
  ): OptArg.Of[DataArray.ThreeDimensional] =
    OptArg.Of(DataArray.ThreeDimensional(twiceNestedDoubles.map(onceNestedDoublesToDataArray)))
  implicit def twiceNestedFloatsToOptArgDataArray(
    twiceNestedFloats: Seq[Seq[Seq[Float]]],
  ): OptArg.Of[DataArray.ThreeDimensional] =
    OptArg.Of(DataArray.ThreeDimensional(twiceNestedFloats.map(onceNestedFloatsToDataArray)))
  implicit def twiceNestedLongsToOptArgDataArray(
    twiceNestedLongs: Seq[Seq[Seq[Long]]],
  ): OptArg.Of[DataArray.ThreeDimensional] =
    OptArg.Of(DataArray.ThreeDimensional(twiceNestedLongs.map(onceNestedLongsToDataArray)))
  implicit def twiceNestedIntsToOptArgDataArray(
    twiceNestedInts: Seq[Seq[Seq[Int]]],
  ): OptArg.Of[DataArray.ThreeDimensional] =
    OptArg.Of(DataArray.ThreeDimensional(twiceNestedInts.map(onceNestedIntsToDataArray)))

}
