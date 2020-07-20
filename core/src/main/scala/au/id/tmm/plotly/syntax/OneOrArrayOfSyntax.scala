package au.id.tmm.plotly.syntax

import au.id.tmm.plotly.model.utilities.OneOrArrayOf

trait OneOrArrayOfSyntax {

  implicit def convertSingleValueToOneOrArray[A](a: A): OneOrArrayOf[A] = OneOrArrayOf.One(a)

  implicit def convertSeqToOneOrArray[A](aSeq: Seq[A]): OneOrArrayOf[A] = OneOrArrayOf.Array(aSeq)

}
