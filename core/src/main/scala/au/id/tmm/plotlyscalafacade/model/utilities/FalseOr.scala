package au.id.tmm.plotlyscalafacade.model.utilities

import io.circe.{Encoder, Json}

sealed trait FalseOr[+A]

object FalseOr {
  final case class Value[A](a: A) extends FalseOr[A]
  case object False               extends FalseOr[Nothing]

  implicit def plotlyFacadeEncoderForFalseOr[A : Encoder, T](implicit ev: T <:< FalseOr[A]): Encoder[T] =
    Encoder[T] { t =>
      ev(t) match {
        case Value(a) => Encoder[A].apply(a)
        case False    => Json.fromBoolean(false)
      }
    }
}
