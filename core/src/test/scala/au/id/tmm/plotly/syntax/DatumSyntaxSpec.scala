package au.id.tmm.plotly.syntax

import java.time.Instant

import au.id.tmm.plotly.model.Datum
import au.id.tmm.plotly.syntax.datum._
import org.scalatest.flatspec.AnyFlatSpec

class DatumSyntaxSpec extends AnyFlatSpec {

  "the datum syntax" should "provide a conversion from Double" in {
    assert((2d: Datum) === Datum.OfNumber(2d))
  }

  it should "provide a conversion from Instant" in {
    assert((Instant.EPOCH: Datum) === Datum.OfInstant(Instant.EPOCH))
  }

  it should "provide a conversion from String" in {
    assert(("hello": Datum) === Datum.OfString("hello"))
  }

}
