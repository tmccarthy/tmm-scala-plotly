package au.id.tmm.plotly.syntax

import au.id.tmm.plotly.model.utilities._
import au.id.tmm.plotly.model.{DataArray, Datum}
import munit.FunSuite

class OptArgSyntaxSpec extends FunSuite {

  test("provide a conversion") {
    assertEquals(1: OptArg[Int], OptArg.Of(1))
  }

  test("provide a conversion for a DataArray") {
    assertEquals(List(1): OptArg[DataArray.OfNumbers], OptArg.Of(DataArray.OfInts(List(1))))
  }

  test("provide a conversion for a BlankOr") {
    assertEquals(1: OptArg[BlankOr[Int]], OptArg.Of(BlankOr.Value(1)))
  }

  test("provide a conversion for a BooleanOr") {
    assertEquals(1: OptArg[BooleanOr[Int]], OptArg.Of(BooleanOr.Value(1)))
  }

  test("provide a conversion for a FalseOr") {
    assertEquals(1: OptArg[FalseOr[Int]], OptArg.Of(FalseOr.Value(1)))
  }

  test("provide a conversion for a OneOrArrayOf.Array") {
    assertEquals(List(1, 2): OptArg[OneOrArrayOf[Int]], OptArg.Of(OneOrArrayOf.Array(List(1, 2))))
  }

  test("provide a conversion for a OneOrArrayOf.One") {
    assertEquals(1: OptArg[OneOrArrayOf[Int]], OptArg.Of(OneOrArrayOf.One(1)))
  }

  test("provide a conversion for a Datum") {
    assertEquals(1: OptArg[Datum.OfNumber], OptArg.Of(Datum.OfNumber(1d)))
  }

}
