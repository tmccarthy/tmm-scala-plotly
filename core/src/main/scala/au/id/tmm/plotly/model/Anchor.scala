package au.id.tmm.plotly.model

import au.id.tmm.plotly.model.utilities.JSEnum

object Anchor {

  sealed abstract class X(val asString: String) extends JSEnum

  object X {
    case object Auto   extends X("auto")
    case object Left   extends X("left")
    case object Center extends X("center")
    case object Right  extends X("right")
  }

  sealed abstract class Y(val asString: String) extends JSEnum

  object Y {
    case object Auto   extends Y("auto")
    case object Top    extends Y("top")
    case object Middle extends Y("middle")
    case object Bottom extends Y("bottom")
  }

}
