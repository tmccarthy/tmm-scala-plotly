package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum
import io.circe.Encoder

final case class PolarLayout(
  domain: Option[Domain] = None,
  sector: Option[NumberArray] = None,
  hole: Option[Number] = None,
  bgcolor: Option[Color] = None,
  radialaxis: Option[LayoutAxis] = None,
  angularaxis: Option[LayoutAxis] = None,
  gridshape: Option[PolarLayout.GridShape] = None,
)

object PolarLayout {

  sealed abstract class GridShape(val asString: String) extends JSEnum

  object GridShape {
    case object Circular extends GridShape("circular")
    case object Linear   extends GridShape("linear")
  }

  implicit val encoder: Encoder[PolarLayout] = Encoder.forProduct7(
    "domain",
    "sector",
    "hole",
    "bgcolor",
    "radialaxis",
    "angularaxis",
    "gridshape",
  )(p =>
    (
      p.domain,
      p.sector,
      p.hole,
      p.bgcolor,
      p.radialaxis,
      p.angularaxis,
      p.gridshape,
    ),
  )

}
