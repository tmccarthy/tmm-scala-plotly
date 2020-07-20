package au.id.tmm.plotly.syntax

import au.id.tmm.plotly.model.utilities.FalseOr

trait FalseOrSyntax {

  implicit def convertValueToFalseOrValue[A](a: A): FalseOr[A] = FalseOr(a)

  def False: FalseOr.False.type = FalseOr.False

}
