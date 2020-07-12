package au.id.tmm.plotlyscalafacade.model

final case class PolarLayout(
  domain: Partial[Domain],
  sector: NumberArray,
  hole: Number,
  bgcolor: Color,
  radialaxis: Partial[LayoutAxis],
  angularaxis: Partial[LayoutAxis],
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
