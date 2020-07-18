package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{Arg, JSEnum}
import io.circe.Encoder

final case class PolarLayout(
  domain: Arg[Domain] = Arg.Undefined,
  sector: Arg[NumberArray] = Arg.Undefined,
  hole: Arg[Number] = Arg.Undefined,
  bgcolor: Arg[Color] = Arg.Undefined,
  radialaxis: Arg[LayoutAxis] = Arg.Undefined,
  angularaxis: Arg[LayoutAxis] = Arg.Undefined,
  gridshape: Arg[PolarLayout.GridShape] = Arg.Undefined,
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
