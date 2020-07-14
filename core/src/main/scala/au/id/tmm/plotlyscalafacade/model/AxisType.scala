package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum

sealed abstract class AxisType(val asString: String) extends JSEnum

object AxisType {
  case object AutomaticallyDetermined extends AxisType("-")
  case object Linear                  extends AxisType("linear")
  case object Log                     extends AxisType("log")
  case object Date                    extends AxisType("date")
  case object Category                extends AxisType("category")
}
