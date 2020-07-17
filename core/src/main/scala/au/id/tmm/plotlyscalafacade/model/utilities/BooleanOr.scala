package au.id.tmm.plotlyscalafacade.model.utilities

import io.circe.{Encoder, Json}

sealed trait BooleanOr[+A]

object BooleanOr {
  final case class Value[A](a: A)                 extends BooleanOr[A]
  final case class BooleanValue(boolean: Boolean) extends BooleanOr[Nothing]

  implicit def plotlyFacadeEncoderForBooleanOr[A : Encoder]: Encoder[BooleanOr[A]] =
    Encoder[BooleanOr[A]] {
      case Value(a)        => Encoder[A].apply(a)
      case BooleanValue(b) => Json.fromBoolean(b)
    }
}
