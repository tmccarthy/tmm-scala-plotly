package au.id.tmm.plotlyscalafacade.syntax

import au.id.tmm.plotlyscalafacade.model.utilities.BlankOr

trait BlankOrSyntax {

  implicit def convertValueToBlankOrValue[A](a: A): BlankOr[A] = BlankOr(a)

  def Blank: BlankOr.Blank.type = BlankOr.Blank

}
