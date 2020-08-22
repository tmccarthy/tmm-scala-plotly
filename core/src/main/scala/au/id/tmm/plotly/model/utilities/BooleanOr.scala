package au.id.tmm.plotly.model.utilities

import io.circe.{Encoder, Json}

sealed trait BooleanOr[+A]

object BooleanOr {

  def apply[A](a: A): BooleanOr[A] =
    a match {
      case b: Boolean => BooleanValue(b)
      case v          => Value(v)
    }

  final case class Value[A](a: A)                 extends BooleanOr[A]
  final case class BooleanValue(boolean: Boolean) extends BooleanOr[Nothing]

  implicit def tmmPlotlyEncoderForBooleanOr[A : Encoder]: Encoder[BooleanOr[A]] =
    Encoder[BooleanOr[A]] {
      case Value(a)        => Encoder[A].apply(a)
      case BooleanValue(b) => Json.fromBoolean(b)
    }
}
