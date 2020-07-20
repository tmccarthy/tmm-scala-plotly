package au.id.tmm.plotly.syntax

import au.id.tmm.plotly.model.utilities.BooleanOr

trait BooleanOrSyntax {

  implicit def convertBooleanToBooleanOrBoolean(boolean: Boolean): BooleanOr[Nothing] = BooleanOr.BooleanValue(boolean)

  implicit def convertValueToBooleanOrValue[A](a: A): BooleanOr[A] = BooleanOr(a)

}
