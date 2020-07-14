package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum

final case class Gauge(
  shape: Gauge.Shape,
  bar: Partial[Gauge.Bar],
  bgcolor: Color,
  bordercolor: Color,
  borderwidth: Number,
  axis: Partial[Axis],
  steps: Seq[Gauge.Step],
  threshold: Partial[Gauge.Threshold],
)

object Gauge {

  sealed abstract class Shape(val asString: String) extends JSEnum

  object Shape {
    case object Angular extends Shape("angular")
    case object Bullet  extends Shape("bullet")
  }

  final case class Bar(
    color: Color,
    line: Partial[Gauge.Line],
    thickness: Number,
  )

  final case class Line(
    color: Color,
    width: Number,
  )

  final case class Threshold(
    line: Partial[Gauge.Line],
    value: Number,
    thickness: Number,
  )

  final case class Step(
    range: NumberArray,
    color: Color,
  )
}
