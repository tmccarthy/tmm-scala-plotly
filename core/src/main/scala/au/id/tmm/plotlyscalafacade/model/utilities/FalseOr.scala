package au.id.tmm.plotlyscalafacade.model.utilities

import io.circe.{Encoder, Json}

sealed trait FalseOr[+A]

object FalseOr {

  def apply[A](a: A): FalseOr[A] =
    a match {
      case false => False
      case v     => Value(v)
    }

  final case class Value[A](a: A) extends FalseOr[A]
  case object False               extends FalseOr[Nothing]

  implicit def plotlyFacadeEncoderForFalseOr[A : Encoder]: Encoder[FalseOr[A]] =
    Encoder {
      case Value(a) => Encoder[A].apply(a)
      case False    => Json.fromBoolean(false)
    }
}
