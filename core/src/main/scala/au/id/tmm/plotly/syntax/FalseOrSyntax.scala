package au.id.tmm.plotly.syntax

import au.id.tmm.plotly.model.utilities.{FalseOr, OptArg}

trait FalseOrSyntax {

  implicit def convertValueToFalseOrValue[A](a: A): FalseOr[A] = FalseOr(a)

  implicit def convertValueToOptArgFalseOrValue[A](a: A): OptArg.Of[FalseOr[A]] = OptArg.Of(FalseOr(a))

  def False: FalseOr.False.type = FalseOr.False

}
