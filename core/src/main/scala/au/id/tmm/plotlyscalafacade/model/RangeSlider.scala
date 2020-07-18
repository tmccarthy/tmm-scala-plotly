package au.id.tmm.plotlyscalafacade.model

import io.circe.Encoder

final case class RangeSlider(
  visible: Option[Boolean] = None,
  thickness: Option[Number] = None,
  range: Option[Range[Datum]] = None,
  borderwidth: Option[Number] = None,
  bordercolor: Option[Color] = None,
  bgcolor: Option[Color] = None,
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
