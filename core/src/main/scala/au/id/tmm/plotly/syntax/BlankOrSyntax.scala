package au.id.tmm.plotly.syntax

import au.id.tmm.plotly.model.utilities.BlankOr

trait BlankOrSyntax {

  implicit def convertValueToBlankOrValue[A](a: A): BlankOr[A] = BlankOr(a)

  def Blank: BlankOr.Blank.type = BlankOr.Blank

}
