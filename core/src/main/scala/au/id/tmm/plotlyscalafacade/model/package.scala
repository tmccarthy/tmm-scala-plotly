package au.id.tmm.plotlyscalafacade

import java.time.LocalDateTime

import com.github.ghik.silencer.silent
import io.circe.{Encoder, Json}

package object model {

  type Number = Double

  type NumberArray = Seq[Number]

  type Date = LocalDateTime // TODO is this right?

  type CategoryIndex = Int

  // TODO string | number | Date | null;
  type Datum = Nothing

  type Partial[A] = A // TODO find some way of handling this

  type PlotSelectedData = Partial[PlotDatum]

  @silent("define classes/objects inside of package objects")
  final case class Range[+A](min: A, max: A)

  object Range {
    implicit def encoder[A: Encoder]: Encoder[Range[A]] = Encoder[List[A]].contramap(r => List(r.min, r.max))
  }

  // TODO put this somewhere
  @silent("define classes/objects inside of package objects")
  trait JSEnum {
    def asString: String
  }

  object JSEnum {
    implicit def plotlyFacadeEncoderForJsEnum[T <: JSEnum]: Encoder[T] = Encoder[String].contramap(_.asString)
  }

  @silent("define classes/objects inside of package objects")
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

  @silent("define classes/objects inside of package objects")
  sealed trait BooleanOr[+A]

  object BooleanOr {
    final case class Value[A](a: A)                 extends BooleanOr[A]
    final case class BooleanValue(boolean: Boolean) extends BooleanOr[Nothing]

    implicit def plotlyFacadeEncoderForBooleanOr[A : Encoder, T](implicit ev: T <:< BooleanOr[A]): Encoder[T] =
      Encoder[T] { t =>
        ev(t) match {
          case Value(a)        => Encoder[A].apply(a)
          case BooleanValue(b) => Json.fromBoolean(b)
        }
      }
  }

  @silent("define classes/objects inside of package objects")
  sealed trait BlankOr[+A]

  object BlankOr {
    final case class Value[A](a: A) extends BlankOr[A]
    case object Blank               extends BlankOr[Nothing]

    implicit def plotlyFacadeEncoderForBlankOr[A : Encoder, T](implicit ev: T <:< BlankOr[A]): Encoder[T] =
      Encoder[T] { t =>
        ev(t) match {
          case Value(a) => Encoder[A].apply(a)
          case Blank    => Encoder[String].apply("")
        }
      }
  }

}
