package au.id.tmm.plotly.model.utilities

import cats.{Alternative, Monad}
import io.circe.{Encoder, Json}

import scala.annotation.tailrec

/**
  * An optional argument for one of the classes in the `au.id.tmm.plotly.model` package.
  *
  * This type is used to avoid requiring arguments where none is required by the Plotly JS API. We use this dedicated
  * type instead of `Option`.
  *
  * You can import the implicit conversions in `au.id.tmm.plotly.syntax._` so that you do not need to constantly wrap
  * arguments in `OptArg.Of`.
  */
sealed trait OptArg[+A] {
  private[plotly] def toOption: Option[A] =
    this match {
      case OptArg.Of(a)     => Some(a)
      case OptArg.Undefined => None
    }

  private[plotly] def map[B](f: A => B): OptArg[B] =
    this match {
      case OptArg.Of(a)     => OptArg.Of(f(a))
      case OptArg.Undefined => OptArg.Undefined
    }

  private[plotly] def flatMap[B](f: A => OptArg[B]): OptArg[B] =
    this match {
      case OptArg.Of(a)     => f(a)
      case OptArg.Undefined => OptArg.Undefined
    }

  private[plotly] def isDefined: Boolean =
    this match {
      case OptArg.Of(_)     => true
      case OptArg.Undefined => false
    }

  private[plotly] def orElse[A1 >: A](that: => OptArg[A1]): OptArg[A1] =
    this match {
      case self @ OptArg.Of(_) => self
      case OptArg.Undefined    => that
    }

  private[plotly] def getOrElse[A1 >: A](that: => A1): A1 =
    this match {
      case OptArg.Of(a)     => a
      case OptArg.Undefined => that
    }

}

object OptArg {
  case object Undefined        extends OptArg[Nothing]
  final case class Of[A](a: A) extends OptArg[A]

  implicit def encoder[A : Encoder]: Encoder[OptArg[A]] = {
    case Undefined    => Json.Null
    case OptArg.Of(a) => Encoder[A].apply(a)
  }

  implicit val standardCatsInstances: Alternative[OptArg] with Monad[OptArg] =
    new Alternative[OptArg] with Monad[OptArg] {
      override def pure[A](x: A): OptArg[A] = OptArg.Of(x)

      override def empty[A]: OptArg[A] = OptArg.Undefined

      override def combineK[A](x: OptArg[A], y: OptArg[A]): OptArg[A] =
        (x, y) match {
          case (left @ OptArg.Of(_), _)                 => left
          case (OptArg.Undefined, right @ OptArg.Of(_)) => right
          case (OptArg.Undefined, OptArg.Undefined)     => OptArg.Undefined
        }

      override def flatMap[A, B](fa: OptArg[A])(f: A => OptArg[B]): OptArg[B] = fa.flatMap(f)

      override def map[A, B](fa: OptArg[A])(f: A => B): OptArg[B] = fa.map(f)

      @tailrec
      override def tailRecM[A, B](a: A)(f: A => OptArg[Either[A, B]]): OptArg[B] =
        f(a) match {
          case Undefined    => Undefined
          case Of(Right(b)) => pure(b)
          case Of(Left(a))  => tailRecM(a)(f)
        }
    }
}
