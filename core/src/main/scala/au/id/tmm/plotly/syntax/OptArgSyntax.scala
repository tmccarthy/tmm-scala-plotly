package au.id.tmm.plotly.syntax

import au.id.tmm.plotly.model.utilities.OptArg

trait OptArgSyntax {

  implicit def convertValueToOptArgOf[A](a: A): OptArg[A] = OptArg.Of(a)

  def Undefined: OptArg.Undefined.type = OptArg.Undefined

}
