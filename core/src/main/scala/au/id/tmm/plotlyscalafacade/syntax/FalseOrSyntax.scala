package au.id.tmm.plotlyscalafacade.syntax

import au.id.tmm.plotlyscalafacade.model.utilities.FalseOr

trait FalseOrSyntax {

  implicit def convertValueToFalseOrValue[A](a: A): FalseOr[A] = FalseOr(a)

  def False: FalseOr.False.type = FalseOr.False

}
