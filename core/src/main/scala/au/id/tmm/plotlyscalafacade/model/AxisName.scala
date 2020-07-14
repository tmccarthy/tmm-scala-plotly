package au.id.tmm.plotlyscalafacade.model

sealed abstract class AxisName(val asString: String) extends JSEnum

object AxisName {
  case object x  extends AxisName("x")
  case object x2 extends AxisName("x2")
  case object x3 extends AxisName("x3")
  case object x4 extends AxisName("x4")
  case object x5 extends AxisName("x5")
  case object x6 extends AxisName("x6")
  case object x7 extends AxisName("x7")
  case object x8 extends AxisName("x8")
  case object x9 extends AxisName("x9")

  case object y  extends AxisName("y")
  case object y2 extends AxisName("y2")
  case object y3 extends AxisName("y3")
  case object y4 extends AxisName("y4")
  case object y5 extends AxisName("y5")
  case object y6 extends AxisName("y6")
  case object y7 extends AxisName("y7")
  case object y8 extends AxisName("y8")
  case object y9 extends AxisName("y9")
}
