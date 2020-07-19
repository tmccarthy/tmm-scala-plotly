package au.id.tmm.plotlyscalafacade.syntax

import au.id.tmm.plotlyscalafacade.model.utilities.BooleanOr

trait BooleanOrSyntax {

  implicit def convertBooleanToBooleanOrBoolean(boolean: Boolean): BooleanOr[Nothing] = BooleanOr.BooleanValue(boolean)

  implicit def convertValueToBooleanOrValue[A](a: A): BooleanOr[A] = BooleanOr(a)

}
