package au.id.tmm.plotly.syntax

import au.id.tmm.plotly.model.utilities.{BooleanOr, OptArg}

trait BooleanOrSyntax {

  implicit def convertBooleanToBooleanOrBoolean(boolean: Boolean): BooleanOr[Nothing] = BooleanOr.BooleanValue(boolean)

  implicit def convertValueToBooleanOrValue[A](a: A): BooleanOr[A] = BooleanOr(a)

  implicit def convertBooleanToOptArgBooleanOrBoolean(boolean: Boolean): OptArg.Of[BooleanOr[Nothing]] =
    OptArg.Of(BooleanOr.BooleanValue(boolean))

  implicit def convertValueToOptArgBooleanOrValue[A](a: A): OptArg.Of[BooleanOr[A]] = OptArg.Of(BooleanOr(a))

}
