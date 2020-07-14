package au.id.tmm.plotlyscalafacade.model

final case class Gauge(
  shape: "angular" | "bullet",
  bar: Partial[Gauge.Bar],
  bgcolor: Color,
  bordercolor: Color,
  borderwidth: Number,
  axis: Partial[Axis],
  steps: Seq[Gauge.Step],
  threshold: Partial[Gauge.Threshold],
)

object Gauge {
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
