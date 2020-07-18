package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{Arg, JSEnum}
import io.circe.Encoder

final case class Gauge(
  shape: Arg[Gauge.Shape] = Arg.Undefined,
  bar: Arg[Gauge.Bar] = Arg.Undefined,
  bgcolor: Arg[Color] = Arg.Undefined,
  bordercolor: Arg[Color] = Arg.Undefined,
  borderwidth: Arg[Number] = Arg.Undefined,
  axis: Arg[Axis] = Arg.Undefined,
  steps: Arg[Seq[Gauge.Step]] = Arg.Undefined,
  threshold: Arg[Gauge.Threshold] = Arg.Undefined,
)

object Gauge {

  sealed abstract class Shape(val asString: String) extends JSEnum

  object Shape {
    case object Angular extends Shape("angular")
    case object Bullet  extends Shape("bullet")
  }

  final case class Bar(
    color: Arg[Color] = Arg.Undefined,
    line: Arg[Gauge.Line] = Arg.Undefined,
    thickness: Arg[Number] = Arg.Undefined,
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
    color: Arg[Color] = Arg.Undefined,
    width: Arg[Number] = Arg.Undefined,
  )

  object Line {
    implicit val encoder: Encoder[Line] = Encoder.forProduct2("color", "width")(l => (l.color, l.width))
  }

  final case class Threshold(
    line: Arg[Gauge.Line] = Arg.Undefined,
    value: Arg[Number] = Arg.Undefined,
    thickness: Arg[Number] = Arg.Undefined,
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
    range: Arg[NumberArray] = Arg.Undefined,
    color: Arg[Color] = Arg.Undefined,
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
