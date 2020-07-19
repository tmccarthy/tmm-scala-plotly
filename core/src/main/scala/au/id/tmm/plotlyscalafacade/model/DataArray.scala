package au.id.tmm.plotlyscalafacade.model

import java.time._

import io.circe.Encoder
import io.circe.syntax.EncoderOps

// TODO the two-dimensional and three-dimensional versions of this only work in some contexts. May want some way to constrain this
sealed trait DataArray

object DataArray {

  final case class OfStrings(strings: Seq[String]) extends DataArray

  object OfStrings {
    implicit val encoder: Encoder[OfStrings] = Encoder[Seq[String]].contramap(_.strings)
  }

  sealed trait OfDateTimes extends DataArray

  final case class OfInstants(instants: Seq[Instant])       extends OfDateTimes
  final case class OfLocalDates(localDates: Seq[LocalDate]) extends OfDateTimes

  def OfZonedDateTimes(zonedDateTimes: Seq[ZonedDateTime]): OfInstants    = OfInstants(zonedDateTimes.map(_.toInstant))
  def OfOffsetDateTimes(offsetDateTimes: Seq[OffsetDateTime]): OfInstants = OfInstants(offsetDateTimes.map(_.toInstant))
  def OfLocalDateTimes(localDateTimes: Seq[LocalDateTime], timezone: ZoneId): OfInstants =
    OfInstants(localDateTimes.map(_.atZone(timezone).toInstant))

  /**
    * Constructs a `DataArray` containing the given `LocalDateTime` values taken as being in UTC. It is generally safer
    * to supply a timezone using `OfLocalDateTimes(localDateTimes, timezone)`.
    */
  def OfLocalDateTimesUnsafe(localDateTimes: Seq[LocalDateTime]): OfInstants =
    OfLocalDateTimes(localDateTimes, ZoneOffset.UTC)
  def OfEpochMillis(epochMillis: Seq[Long]): OfInstants   = OfInstants(epochMillis.map(Instant.ofEpochMilli))
  def OfEpochSeconds(epochSeconds: Seq[Long]): OfInstants = OfInstants(epochSeconds.map(Instant.ofEpochSecond))

  object OfDateTimes {
    implicit val encoder: Encoder[OfDateTimes] = {
      case OfInstants(instants)     => instants.asJson
      case OfLocalDates(localDates) => localDates.asJson
    }
  }

  sealed trait OfNumbers extends DataArray

  final case class OfDoubles(doubles: Seq[Double]) extends OfNumbers
  final case class OfFloats(floats: Seq[Float])    extends OfNumbers
  final case class OfLongs(longs: Seq[Long])       extends OfNumbers
  final case class OfInts(ints: Seq[Int])          extends OfNumbers

  object OfNumbers {
    implicit val encoder: Encoder[OfNumbers] = {
      case OfDoubles(doubles) => doubles.asJson
      case OfFloats(floats)   => floats.asJson
      case OfLongs(longs)     => longs.asJson
      case OfInts(ints)       => ints.asJson
    }
  }

  final case class TwoDimensional(dataArrays: Seq[DataArray.OfNumbers]) extends DataArray

  object TwoDimensional {
    implicit val encoder: Encoder[TwoDimensional] = Encoder.encodeSeq(OfNumbers.encoder).contramap(_.dataArrays)
  }

  final case class ThreeDimensional(dataArrays: Seq[DataArray.TwoDimensional]) extends DataArray

  object ThreeDimensional {
    implicit val encoder: Encoder[ThreeDimensional] = Encoder.encodeSeq(TwoDimensional.encoder).contramap(_.dataArrays)
  }

  implicit val encoder: Encoder[DataArray] = {
    case s: OfStrings        => s.asJson
    case d: OfDateTimes      => d.asJson
    case n: OfNumbers        => n.asJson
    case t: TwoDimensional   => t.asJson
    case t: ThreeDimensional => t.asJson
  }

}
