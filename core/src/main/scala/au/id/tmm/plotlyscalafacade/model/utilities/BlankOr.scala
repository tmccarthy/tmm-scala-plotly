package au.id.tmm.plotlyscalafacade.model.utilities

import io.circe.Encoder

sealed trait BlankOr[+A]

object BlankOr {
  final case class Value[A](a: A) extends BlankOr[A]
  case object Blank               extends BlankOr[Nothing]

  implicit def plotlyFacadeEncoderForBlankOr[A : Encoder]: Encoder[BlankOr[A]] =
    Encoder {
      case Value(a) => Encoder[A].apply(a)
      case Blank    => Encoder[String].apply("")
    }
}
