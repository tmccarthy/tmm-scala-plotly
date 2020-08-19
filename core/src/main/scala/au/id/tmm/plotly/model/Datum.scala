package au.id.tmm.plotly.model

import java.time._

import io.circe.syntax.EncoderOps
import io.circe.{Encoder, Json}

// TODO need a way to represent null
/**
  * Represents an individual datum within Plotly. Sequences of `Datum` can be more efficiently represented with
  * `DataArray`.
  */
sealed trait Datum

object Datum {

  final case class OfNumber(number: Number) extends Datum

  object OfNumber {
    implicit val encoder: Encoder[OfNumber] = Encoder[Number].contramap(_.number)
  }

  sealed trait OfDateTime extends Datum

  final case class OfInstant(instant: Instant)       extends OfDateTime
  final case class OfLocalDate(localDate: LocalDate) extends OfDateTime

  def OfZonedDateTime(zonedDateTime: ZonedDateTime): OfInstant    = OfInstant(zonedDateTime.toInstant)
  def OfOffsetDateTime(offsetDateTime: OffsetDateTime): OfInstant = OfInstant(offsetDateTime.toInstant)
  def OfLocalDateTime(localDateTime: LocalDateTime, timezone: ZoneId): OfInstant =
    OfZonedDateTime(localDateTime.atZone(timezone))

  /**
    * Constructs a `Datum` containing the given `LocalDateTime` taken as being in UTC. It is generally safer
    * to supply a timezone using `OfLocalDateTime(localDateTime, timezone)`.
    */
  def OfLocalDateTimeUnsafe(localDateTime: LocalDateTime): OfInstant = OfLocalDateTime(localDateTime, ZoneOffset.UTC)
  def OfEpochMilli(epochMilli: Long): OfInstant                      = OfInstant(Instant.ofEpochMilli(epochMilli))
  def OfEpochSecond(epochSecond: Long): OfInstant                    = OfInstant(Instant.ofEpochSecond(epochSecond))

  object OfDateTime {
    implicit val encoder: Encoder[OfDateTime] = {
      case OfInstant(instant)     => instant.asJson
      case OfLocalDate(localDate) => localDate.asJson
    }
  }

  final case class OfString(string: String) extends Datum

  case object OfNull extends Datum

  object OfString {
    implicit val encoder: Encoder[OfString] = Encoder[String].contramap(_.string)
  }

  implicit val encoder: Encoder[Datum] = {
    case n: OfNumber   => n.asJson
    case d: OfDateTime => d.asJson
    case s: OfString   => s.asJson
    case OfNull        => Json.Null
  }

}
