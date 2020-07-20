package au.id.tmm.plotly.model

import au.id.tmm.plotly.model.utilities.{OptArg, JSEnum}
import io.circe.Encoder

final case class Gauge(
  shape: OptArg[Gauge.Shape] = OptArg.Undefined,
  bar: OptArg[Gauge.Bar] = OptArg.Undefined,
  bgcolor: OptArg[Color] = OptArg.Undefined,
  bordercolor: OptArg[Color] = OptArg.Undefined,
  borderwidth: OptArg[Number] = OptArg.Undefined,
  axis: OptArg[Axis] = OptArg.Undefined,
  steps: OptArg[Seq[Gauge.Step]] = OptArg.Undefined,
  threshold: OptArg[Gauge.Threshold] = OptArg.Undefined,
)

object Gauge {

  sealed abstract class Shape(val asString: String) extends JSEnum

  object Shape {
    case object Angular extends Shape("angular")
    case object Bullet  extends Shape("bullet")
  }

  final case class Bar(
    color: OptArg[Color] = OptArg.Undefined,
    line: OptArg[Gauge.Line] = OptArg.Undefined,
    thickness: OptArg[Number] = OptArg.Undefined,
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
    color: OptArg[Color] = OptArg.Undefined,
    width: OptArg[Number] = OptArg.Undefined,
  )

  object Line {
    implicit val encoder: Encoder[Line] = Encoder.forProduct2("color", "width")(l => (l.color, l.width))
  }

  final case class Threshold(
    line: OptArg[Gauge.Line] = OptArg.Undefined,
    value: OptArg[Number] = OptArg.Undefined,
    thickness: OptArg[Number] = OptArg.Undefined,
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
    range: OptArg[Seq[Number]] = OptArg.Undefined,
    color: OptArg[Color] = OptArg.Undefined,
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
