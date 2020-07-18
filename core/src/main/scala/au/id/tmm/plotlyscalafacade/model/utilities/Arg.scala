package au.id.tmm.plotlyscalafacade.model.utilities

import io.circe.{Encoder, Json}

// TODO this is a truly terrible name
sealed trait Arg[+A] {
  def toOption: Option[A] =
    this match {
      case Arg.Of(a)     => Some(a)
      case Arg.Undefined => None
    }
}

object Arg {
  case object Undefined        extends Arg[Nothing]
  final case class Of[A](a: A) extends Arg[A]

  implicit def encoder[A : Encoder]: Encoder[Arg[A]] = {
    case Undefined => Json.Null
    case Arg.Of(a) => Encoder[A].apply(a)
  }
}
