package au.id.tmm.plotlyscalafacade.syntax

import au.id.tmm.plotlyscalafacade.model.utilities.OptArg

trait OptArgSyntax {

  implicit def convertValueToOptArgOf[A](a: A): OptArg[A] = OptArg.Of(a)

}
