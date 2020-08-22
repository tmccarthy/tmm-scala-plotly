package au.id.tmm.plotly.syntax

import au.id.tmm.plotly.model.{DataArray, Datum}
import au.id.tmm.plotly.model.utilities._
import org.scalatest.flatspec.AnyFlatSpec

class OptArgSyntaxSpec extends AnyFlatSpec {

  "the OptArg syntax" should "provide a conversion" in {
    assert((1: OptArg[Int]) === OptArg.Of(1))
  }

  it should "provide a conversion for a DataArray" in {
    assert((List(1): OptArg[DataArray.OfNumbers]) === OptArg.Of(DataArray.OfInts(List(1))))
  }

  it should "provide a conversion for a BlankOr" in {
    assert((1: OptArg[BlankOr[Int]]) === OptArg.Of(BlankOr.Value(1)))
  }

  it should "provide a conversion for a BooleanOr" in {
    assert((1: OptArg[BooleanOr[Int]]) === OptArg.Of(BooleanOr.Value(1)))
  }

  it should "provide a conversion for a FalseOr" in {
    assert((1: OptArg[FalseOr[Int]]) === OptArg.Of(FalseOr.Value(1)))
  }

  it should "provide a conversion for a OneOrArrayOf.Array" in {
    assert((List(1, 2): OptArg[OneOrArrayOf[Int]]) === OptArg.Of(OneOrArrayOf.Array(List(1, 2))))
  }

  it should "provide a conversion for a OneOrArrayOf.One" in {
    assert((1: OptArg[OneOrArrayOf[Int]]) === OptArg.Of(OneOrArrayOf.One(1)))
  }

  it should "provide a conversion for a Datum" in {
    assert((1: OptArg[Datum.OfNumber]) === OptArg.Of(Datum.OfNumber(1d)))
  }

}
