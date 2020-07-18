package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum
import io.circe.Encoder

final case class Gauge(
  shape: Option[Gauge.Shape] = None,
  bar: Option[Gauge.Bar] = None,
  bgcolor: Option[Color] = None,
  bordercolor: Option[Color] = None,
  borderwidth: Option[Number] = None,
  axis: Option[Axis] = None,
  steps: Option[Seq[Gauge.Step]] = None,
  threshold: Option[Gauge.Threshold] = None,
)

object Gauge {

  sealed abstract class Shape(val asString: String) extends JSEnum

  object Shape {
    case object Angular extends Shape("angular")
    case object Bullet  extends Shape("bullet")
  }

  final case class Bar(
    color: Option[Color] = None,
    line: Option[Gauge.Line] = None,
    thickness: Option[Number] = None,
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
    color: Option[Color] = None,
    width: Option[Number] = None,
  )

  object Line {
    implicit val encoder: Encoder[Line] = Encoder.forProduct2("color", "width")(l => (l.color, l.width))
  }

  final case class Threshold(
    line: Option[Gauge.Line] = None,
    value: Option[Number] = None,
    thickness: Option[Number] = None,
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
    range: Option[NumberArray] = None,
    color: Option[Color] = None,
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
