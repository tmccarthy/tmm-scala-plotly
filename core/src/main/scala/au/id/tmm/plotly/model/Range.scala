package au.id.tmm.plotly.model

import io.circe.Encoder

final case class Range[+A](min: A, max: A)

object Range {
  implicit def encoder[A : Encoder]: Encoder[Range[A]] = Encoder[List[A]].contramap(r => List(r.min, r.max))
}
