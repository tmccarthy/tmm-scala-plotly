package au.id.tmm.plotlyscalafacade

import java.time.LocalDateTime

import com.github.ghik.silencer.silent
import io.circe.{Encoder, Json}

package object model {

  type Number = Double

  type NumberArray = Seq[Number]

  type Date = LocalDateTime // TODO is this right?

  // string | number | Date | null;
  type Datum = Nothing

  type Partial[A] = A // TODO find some way of handling this

  type PlotSelectedData = Partial[PlotDatum]

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

}
