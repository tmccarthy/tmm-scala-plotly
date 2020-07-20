package au.id.tmm.plotly.model

import au.id.tmm.plotly.model.utilities.{OptArg, JSEnum}
import io.circe.Encoder

final case class PolarLayout(
  domain: OptArg[Domain] = OptArg.Undefined,
  sector: OptArg[Seq[Number]] = OptArg.Undefined,
  hole: OptArg[Number] = OptArg.Undefined,
  bgcolor: OptArg[Color] = OptArg.Undefined,
  radialaxis: OptArg[LayoutAxis] = OptArg.Undefined,
  angularaxis: OptArg[LayoutAxis] = OptArg.Undefined,
  gridshape: OptArg[PolarLayout.GridShape] = OptArg.Undefined,
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
