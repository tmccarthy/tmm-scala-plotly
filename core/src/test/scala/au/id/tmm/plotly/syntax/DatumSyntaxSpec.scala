package au.id.tmm.plotly.syntax

import java.time.Instant

import au.id.tmm.plotly.model.Datum
import munit.FunSuite

class DatumSyntaxSpec extends FunSuite {

  test("provide a conversion from Double") {
    assertEquals(2d: Datum, Datum.OfNumber(2d))
  }

  test("provide a conversion from Instant") {
    assertEquals(Instant.EPOCH: Datum, Datum.OfInstant(Instant.EPOCH))
  }

  test("provide a conversion from String") {
    assertEquals("hello": Datum, Datum.OfString("hello"))
  }

}
