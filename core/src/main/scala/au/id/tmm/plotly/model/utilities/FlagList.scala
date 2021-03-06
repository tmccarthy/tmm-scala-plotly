package au.id.tmm.plotly.model.utilities

import cats.Show
import cats.data.NonEmptySet
import cats.kernel.Order
import io.circe.Encoder

final case class FlagList[A] private (flags: NonEmptySet[A])

object FlagList {

  def apply[A : Order](head: A, tail: A*): FlagList[A] =
    new FlagList(NonEmptySet.of(head, tail: _*))

  implicit def encoder[A : Show]: Encoder[FlagList[A]] =
    flagList => {
      val asString = flagList.flags.reduceLeftTo(Show[A].show)((acc, a) => acc + "+" + Show[A].show(a))

      Encoder[String].apply(asString)
    }

}
