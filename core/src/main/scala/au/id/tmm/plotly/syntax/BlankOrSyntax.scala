package au.id.tmm.plotly.syntax

import au.id.tmm.plotly.model.utilities.{BlankOr, OptArg}

trait BlankOrSyntax {

  implicit def convertValueToBlankOrValue[A](a: A): BlankOr[A] = BlankOr(a)

  implicit def convertValueToOptArgBlankOrValue[A](a: A): OptArg.Of[BlankOr[A]] = OptArg.Of(BlankOr(a))

  def Blank: BlankOr.Blank.type = BlankOr.Blank

}
