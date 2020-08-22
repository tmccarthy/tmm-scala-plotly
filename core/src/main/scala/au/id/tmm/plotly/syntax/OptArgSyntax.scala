package au.id.tmm.plotly.syntax

import au.id.tmm.plotly.model.{DataArray, Datum}
import au.id.tmm.plotly.model.utilities._

trait OptArgSyntax {

  implicit def convertValueToOptArgOf[A](a: A): OptArg[A] = OptArg.Of(a)

  implicit def convertDataArrayToOptArg[A, D <: DataArray](a: A)(implicit ev: A => D): OptArg[D] = OptArg.Of(a)
  implicit def convertDatumToOptArg[A, D <: Datum](a: A)(implicit ev: A => D): OptArg[D] = OptArg.Of(a)
  implicit def convertBlankOrToOptArg[A, D <: BlankOr[Any]](a: A)(implicit ev: A => D): OptArg[D] = OptArg.Of(a)
  implicit def convertBooleanOrToOptArg[A, D <: BooleanOr[Any]](a: A)(implicit ev: A => D): OptArg[D] = OptArg.Of(a)
  implicit def convertFalseOrToOptArg[A, D <: FalseOr[Any]](a: A)(implicit ev: A => D): OptArg[D] = OptArg.Of(a)
  implicit def convertOneOrArrayOfToOptArg[A, D <: OneOrArrayOf[Any]](a: A)(implicit ev: A => D): OptArg[D] =
    OptArg.Of(a)

}
