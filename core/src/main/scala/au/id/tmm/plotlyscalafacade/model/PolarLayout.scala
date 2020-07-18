package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum

final case class PolarLayout(
  domain: Option[Domain] = None,
  sector: Option[NumberArray] = None,
  hole: Option[Number] = None,
  bgcolor: Option[Color] = None,
  radialaxis: Option[LayoutAxis] = None,
  angularaxis: Option[LayoutAxis] = None,
  gridshape: Option[PolarLayout.GridShape] = None,
  uirevision: Option[Nothing] = None,
)

object PolarLayout {

  sealed abstract class GridShape(val asString: String) extends JSEnum

  object GridShape {
    case object Circular extends GridShape("circular")
    case object Linear   extends GridShape("linear")
  }

}
