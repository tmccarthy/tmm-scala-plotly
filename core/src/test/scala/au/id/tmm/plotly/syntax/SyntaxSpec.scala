package au.id.tmm.plotly.syntax

import au.id.tmm.plotly.model.utilities._
import org.scalatest.flatspec.AnyFlatSpec
import au.id.tmm.plotly.syntax.all._

class SyntaxSpec extends AnyFlatSpec {

  "the syntax for BlankOr" should "work as expected for blank values" in {
    assert((Blank: BlankOr[String]) === BlankOr.Blank)
  }

  it should "work as expected for integers" in {
    assert((1: BlankOr[Int]) === BlankOr.Value(1))
  }

  it should "work as expected for strings" in {
    assert(("hello": BlankOr[String]) === BlankOr.Value("hello"))
  }

  it should "work as expected for a blank string" in {
    assert(("": BlankOr[String]) === BlankOr.Blank)
  }

  "the syntax for BooleanOr" should "work as expected for false" in {
    assert((false: BooleanOr[String]) === BooleanOr.BooleanValue(false))
  }

  it should "work as expected for true" in {
    assert((true: BooleanOr[String]) === BooleanOr.BooleanValue(true))
  }

  it should "work as expected for true when moving into a BooleanOr[Boolean]" in {
    assert((true: BooleanOr[Boolean]) === BooleanOr.BooleanValue(true))
  }

  it should "work as expected for a String" in {
    assert(("hello": BooleanOr[String]) === BooleanOr.Value("hello"))
  }

  "the syntax for FalseOr" should "work as expected for False values" in {
    assert((False: FalseOr[String]) === FalseOr.False)
  }

  it should "work as expected for integers" in {
    assert((1: FalseOr[Int]) === FalseOr.Value(1))
  }

  it should "work as expected for strings" in {
    assert(("hello": FalseOr[String]) === FalseOr.Value("hello"))
  }

  it should "work as expected for a false value" in {
    assert((false: FalseOr[Boolean]) === FalseOr.False)
  }

  it should "work as expected for a true value" in {
    assert((true: FalseOr[Boolean]) === FalseOr.Value(true))
  }

  "the syntax for OneOrArrayOf" should "work as expected for a single value" in {
    assert(("hello": OneOrArrayOf[String]) === OneOrArrayOf.One("hello"))
  }

  it should "work as expected for a sequence" in {
    val l = List("hello", "world")
    assert((l: OneOrArrayOf[String]) === OneOrArrayOf.Array(l))
  }

  it should "work as expected for a sequence for a nested OneOrArrayOf" in {
    val l = List("hello", "world")
    assert((l: OneOrArrayOf[List[String]]) === OneOrArrayOf.One(l))
  }

  it should "work as expected for a sequence of sequences for a nested OneOrArrayOf" in {
    val l1 = List("hello", "world")
    val l2 = List("jane", "doe")
    val l  = List(l1, l2)
    assert((l: OneOrArrayOf[List[String]]) === OneOrArrayOf.Array(l))
  }

}
