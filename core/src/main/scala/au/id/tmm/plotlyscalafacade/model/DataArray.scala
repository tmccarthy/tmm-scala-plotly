package au.id.tmm.plotlyscalafacade.model

import java.time._

import io.circe.Encoder
import io.circe.syntax.EncoderOps

// TODO the two-dimensional and three-dimensional versions of this only work in some contexts. May want some way to constrain this
sealed trait DataArray

object DataArray {

  final case class OfStrings(strings: Seq[String]) extends DataArray

  sealed trait OfDateTimes extends DataArray

  final case class OfInstants(instants: Seq[Instant])                      extends OfDateTimes
  final case class OfZonedDateTimes(zonedDateTimes: Seq[ZonedDateTime])    extends OfDateTimes
  final case class OfOffsetDateTimes(offsetDateTimes: Seq[OffsetDateTime]) extends OfDateTimes

  final case class OfLocalDateTimes(localDateTimes: Seq[LocalDateTime], timezone: ZoneId) extends OfDateTimes
  final case class OfLocalDates(localDates: Seq[LocalDate])                               extends OfDateTimes

  sealed trait OfNumbers extends DataArray

  final case class OfDoubles(doubles: Seq[Double]) extends OfNumbers
  final case class OfFloats(floats: Seq[Float])    extends OfNumbers
  final case class OfLongs(longs: Seq[Long])       extends OfNumbers
  final case class OfInts(ints: Seq[Int])          extends OfNumbers

  final case class TwoDimensional(dataArrays: Seq[DataArray.OfNumbers])        extends DataArray
  final case class ThreeDimensional(dataArrays: Seq[DataArray.TwoDimensional]) extends DataArray

  implicit val encoder: Encoder[DataArray] = {
    val encodeDataArrayOfNumbers: Encoder[OfNumbers] = {
      case OfDoubles(doubles) => doubles.asJson
      case OfFloats(floats)   => floats.asJson
      case OfLongs(longs)     => longs.asJson
      case OfInts(ints)       => ints.asJson
    }

    val encodeTwoDimensional: Encoder[TwoDimensional] =
      Encoder.encodeSeq(encodeDataArrayOfNumbers).contramap(_.dataArrays)

    val encodeThreeDimensional: Encoder[ThreeDimensional] =
      Encoder.encodeSeq(encodeTwoDimensional).contramap(_.dataArrays)

    Encoder {
      case OfStrings(strings)                         => strings.asJson
      case OfInstants(instants)                       => instants.asJson
      case OfZonedDateTimes(zonedDateTimes)           => zonedDateTimes.map(_.toInstant).asJson
      case OfOffsetDateTimes(offsetDateTimes)         => offsetDateTimes.map(_.toInstant).asJson
      case OfLocalDateTimes(localDateTimes, timezone) => localDateTimes.map(_.atZone(timezone).toInstant).asJson
      case OfLocalDates(localDates)                   => localDates.asJson
      case dataArrayOfNumbers: OfNumbers              => encodeDataArrayOfNumbers.apply(dataArrayOfNumbers)
      case twoD: TwoDimensional                       => encodeTwoDimensional.apply(twoD)
      case threeD: ThreeDimensional                   => encodeThreeDimensional.apply(threeD)
    }
  }

}
