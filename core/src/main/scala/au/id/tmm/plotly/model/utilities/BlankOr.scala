package au.id.tmm.plotly.model.utilities

import io.circe.Encoder

sealed trait BlankOr[+A]

object BlankOr {

  def apply[A](a: A): BlankOr[A] =
    a match {
      case "" => Blank
      case v  => Value(v)
    }

  final case class Value[A](a: A) extends BlankOr[A]
  case object Blank               extends BlankOr[Nothing]

  implicit def tmmPlotlyEncoderForBlankOr[A : Encoder]: Encoder[BlankOr[A]] =
    Encoder {
      case Value(a) => Encoder[A].apply(a)
      case Blank    => Encoder[String].apply("")
    }
}
