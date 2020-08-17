package au.id.tmm.plotly.model.utilities

import io.circe.{Encoder, Json}

/**
  * An optional argument for one of the classes in the `au.id.tmm.plotlyscalafacade.model` package.
  * // TODO needs an explanation of how to dodge this with the syntax.
  */
sealed trait OptArg[+A] {
  def toOption: Option[A] =
    this match {
      case OptArg.Of(a)     => Some(a)
      case OptArg.Undefined => None
    }
}

object OptArg {
  case object Undefined        extends OptArg[Nothing]
  final case class Of[A](a: A) extends OptArg[A]

  implicit def encoder[A : Encoder]: Encoder[OptArg[A]] = {
    case Undefined    => Json.Null
    case OptArg.Of(a) => Encoder[A].apply(a)
  }
}
