package au.id.tmm.plotly.syntax

import au.id.tmm.plotly.model.utilities.{OneOrArrayOf, OptArg}

trait OneOrArrayOfSyntax {

  implicit def convertSingleValueToOneOrArray[A](a: A): OneOrArrayOf[A] = OneOrArrayOf.One(a)

  implicit def convertSeqToOneOrArray[A](aSeq: Seq[A]): OneOrArrayOf[A] = OneOrArrayOf.Array(aSeq)

  implicit def convertSingleValueToOptArgOneOrArray[A](a: A): OptArg.Of[OneOrArrayOf[A]] =
    OptArg.Of(OneOrArrayOf.One(a))

  implicit def convertSeqToOptArgOneOrArray[A](aSeq: Seq[A]): OptArg.Of[OneOrArrayOf[A]] =
    OptArg.Of(OneOrArrayOf.Array(aSeq))

}
