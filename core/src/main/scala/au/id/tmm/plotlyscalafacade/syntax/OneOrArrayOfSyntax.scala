package au.id.tmm.plotlyscalafacade.syntax

import au.id.tmm.plotlyscalafacade.model.utilities.OneOrArrayOf

trait OneOrArrayOfSyntax {

  implicit def convertSingleValueToOneOrArray[A](a: A): OneOrArrayOf[A] = OneOrArrayOf.One(a)

  implicit def convertSeqToOneOrArray[A](aSeq: Seq[A]): OneOrArrayOf[A] = OneOrArrayOf.Array(aSeq)


}
