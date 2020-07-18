package au.id.tmm.plotlyscalafacade.model

import io.circe.Encoder

final case class RangeSlider(
  visible: Option[Boolean],
  thickness: Option[Number],
  range: Option[Range[Datum]],
  borderwidth: Option[Number],
  bordercolor: Option[Color],
  bgcolor: Option[Color],
)

object RangeSlider {
  implicit val encoder: Encoder[RangeSlider] = Encoder.forProduct6(
    "visible",
    "thickness",
    "range",
    "borderwidth",
    "bordercolor",
    "bgcolor",
  )(r =>
    (
      r.visible,
      r.thickness,
      r.range,
      r.borderwidth,
      r.bordercolor,
      r.bgcolor,
    ),
  )
}
