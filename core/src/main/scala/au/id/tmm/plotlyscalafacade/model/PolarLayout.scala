package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum

final case class PolarLayout(
  domain: Option[Domain],
  sector: Option[NumberArray],
  hole: Option[Number],
  bgcolor: Option[Color],
  radialaxis: Option[LayoutAxis],
  angularaxis: Option[LayoutAxis],
  gridshape: Option[PolarLayout.GridShape],
  uirevision: Option[Nothing],
)

object PolarLayout {

  sealed abstract class GridShape(val asString: String) extends JSEnum

  object GridShape {
    case object Circular extends GridShape("circular")
    case object Linear   extends GridShape("linear")
  }

}
