package au.id.tmm.plotly.model.utilities

import au.id.tmm.plotly.model.utilities.FlagListSpec.Flag
import cats.Show
import cats.instances.string.catsKernelStdOrderForString
import cats.kernel.Order
import io.circe.{Encoder, Json}
import org.scalatest.flatspec.AnyFlatSpec

class FlagListSpec extends AnyFlatSpec {

  "the FlagList encoder" should "encode a single flag" in {
    assert(Encoder[FlagList[Flag]].apply(FlagList[Flag](Flag.A)) === Json.fromString("a"))
  }

  it should "encode multiple flags" in {
    assert(Encoder[FlagList[Flag]].apply(FlagList[Flag](Flag.A, Flag.B, Flag.C)) === Json.fromString("a+b+c"))
  }

}

object FlagListSpec {
  sealed abstract class Flag(val asString: String)

  object Flag {
    case object A extends Flag("a")
    case object B extends Flag("b")
    case object C extends Flag("c")

    implicit val encoder: Encoder[Flag] = Encoder[String].contramap(_.asString)
    implicit val order: Order[Flag] = Order.by[Flag, String](_.asString)
    implicit val show: Show[Flag] = _.asString
  }
}
