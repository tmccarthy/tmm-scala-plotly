package au.id.tmm.plotly.model

import au.id.tmm.plotly.model.utilities.JSEnum

sealed abstract class Dash(val asString: String) extends JSEnum

object Dash {
  case object Solid       extends Dash("solid")
  case object Dot         extends Dash("dot")
  case object Dash        extends Dash("dash")
  case object LongDash    extends Dash("longdash")
  case object DashDot     extends Dash("dashdot")
  case object LongDashDot extends Dash("longdashdot")
}
