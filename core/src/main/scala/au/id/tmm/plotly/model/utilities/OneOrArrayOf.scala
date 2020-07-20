package au.id.tmm.plotly.model.utilities

import io.circe.Encoder

sealed trait OneOrArrayOf[A]

object OneOrArrayOf {

  final case class One[A](a: A)         extends OneOrArrayOf[A]
  final case class Array[A](as: Seq[A]) extends OneOrArrayOf[A]

  implicit def encoder[A : Encoder]: Encoder[OneOrArrayOf[A]] = {
    case One(a)            => Encoder[A].apply(a)
    case Array(as: Seq[A]) => Encoder[Seq[A]].apply(as)
  }

}
