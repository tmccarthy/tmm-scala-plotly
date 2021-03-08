package au.id.tmm.plotly.syntax

import au.id.tmm.plotly.model.utilities._
import munit.FunSuite

class SyntaxSpec extends FunSuite {

  test("the syntax for BlankOr should work as expected for blank values") {
    assertEquals(Blank: BlankOr[String], BlankOr.Blank)
  }

  test("the syntax for BlankOr should work as expected for integers") {
    assertEquals(1: BlankOr[Int], BlankOr.Value(1))
  }

  test("the syntax for BlankOr should work as expected for strings") {
    assertEquals("hello": BlankOr[String], BlankOr.Value("hello"))
  }

  test("the syntax for BlankOr should work as expected for a blank string") {
    assertEquals("": BlankOr[String], BlankOr.Blank)
  }

  test("the syntax for BooleanOr should work as expected for false") {
    assertEquals(false: BooleanOr[String], BooleanOr.BooleanValue(false))
  }

  test("the syntax for BooleanOr should work as expected for true") {
    assertEquals(true: BooleanOr[String], BooleanOr.BooleanValue(true))
  }

  test("the syntax for BooleanOr should work as expected for true when moving into a BooleanOr[Boolean]") {
    assertEquals(true: BooleanOr[Boolean], BooleanOr.BooleanValue(true))
  }

  test("the syntax for BooleanOr should work as expected for a String") {
    assertEquals("hello": BooleanOr[String], BooleanOr.Value("hello"))
  }

  test("the syntax for FalseOr should work as expected for False values") {
    assertEquals(False: FalseOr[String], FalseOr.False)
  }

  test("the syntax for FalseOr should work as expected for integers") {
    assertEquals(1: FalseOr[Int], FalseOr.Value(1))
  }

  test("the syntax for FalseOr should work as expected for strings") {
    assertEquals("hello": FalseOr[String], FalseOr.Value("hello"))
  }

  test("the syntax for FalseOr should work as expected for a false value") {
    assertEquals(false: FalseOr[Boolean], FalseOr.False)
  }

  test("the syntax for FalseOr should work as expected for a true value") {
    assertEquals(true: FalseOr[Boolean], FalseOr.Value(true))
  }

  test("the syntax for OneOrArrayOf should work as expected for a single value") {
    assertEquals("hello": OneOrArrayOf[String], OneOrArrayOf.One("hello"))
  }

  test("the syntax for OneOrArrayOf should work as expected for a sequence") {
    val l = List("hello", "world")
    assertEquals(l: OneOrArrayOf[String], OneOrArrayOf.Array(l))
  }

  test("the syntax for OneOrArrayOf should work as expected for a sequence for a nested OneOrArrayOf") {
    val l = List("hello", "world")
    assertEquals(l: OneOrArrayOf[List[String]], OneOrArrayOf.One(l))
  }

  test("the syntax for OneOrArrayOf should work as expected for a sequence of sequences for a nested OneOrArrayOf") {
    val l1 = List("hello", "world")
    val l2 = List("jane", "doe")
    val l = List(l1, l2)
    assertEquals(l: OneOrArrayOf[List[String]], OneOrArrayOf.Array(l))
  }

}
