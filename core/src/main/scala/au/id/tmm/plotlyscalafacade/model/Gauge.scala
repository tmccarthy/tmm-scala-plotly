package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum
import io.circe.Encoder

final case class Gauge(
  shape: Gauge.Shape,
  bar: Gauge.Bar,
  bgcolor: Color,
  bordercolor: Color,
  borderwidth: Number,
  axis: Axis,
  steps: Seq[Gauge.Step],
  threshold: Gauge.Threshold,
)

object Gauge {

  sealed abstract class Shape(val asString: String) extends JSEnum

  object Shape {
    case object Angular extends Shape("angular")
    case object Bullet  extends Shape("bullet")
  }

  final case class Bar(
    color: Color,
    line: Gauge.Line,
    thickness: Number,
  )

  object Bar {
    implicit val encoder: Encoder[Bar] = Encoder.forProduct3(
      "color",
      "line",
      "thickness",
    )(b =>
      (
        b.color,
        b.line,
        b.thickness,
      ),
    )
  }

  final case class Line(
    color: Color,
    width: Number,
  )

  object Line {
    implicit val encoder: Encoder[Line] = Encoder.forProduct2("color", "width")(l => (l.color, l.width))
  }

  final case class Threshold(
    line: Gauge.Line,
    value: Number,
    thickness: Number,
  )

  object Threshold {
    implicit val encoder: Encoder[Threshold] = Encoder.forProduct3(
      "line",
      "value",
      "thickness",
    )(t =>
      (
        t.line,
        t.value,
        t.thickness,
      ),
    )
  }

  final case class Step(
    range: NumberArray,
    color: Color,
  )

  object Step {
    implicit val encoder: Encoder[Step] = Encoder.forProduct2("range", "color")(s => (s.range, s.color))
  }

  implicit val encoder: Encoder[Gauge] = Encoder.forProduct8(
    "shape",
    "bar",
    "bgcolor",
    "bordercolor",
    "borderwidth",
    "axis",
    "steps",
    "threshold",
  )(g =>
    (
      g.shape,
      g.bar,
      g.bgcolor,
      g.bordercolor,
      g.borderwidth,
      g.axis,
      g.steps,
      g.threshold,
    ),
  )
}
