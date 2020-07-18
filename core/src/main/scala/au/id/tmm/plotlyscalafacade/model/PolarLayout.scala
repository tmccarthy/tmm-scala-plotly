package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum

final case class PolarLayout(
  domain: Domain,
  sector: NumberArray,
  hole: Number,
  bgcolor: Color,
  radialaxis: LayoutAxis,
  angularaxis: LayoutAxis,
  gridshape: PolarLayout.GridShape,
  uirevision: Option[Nothing],
)

object PolarLayout {

  sealed abstract class GridShape(val asString: String) extends JSEnum

  object GridShape {
    case object Circular extends GridShape("circular")
    case object Linear   extends GridShape("linear")
  }

}
