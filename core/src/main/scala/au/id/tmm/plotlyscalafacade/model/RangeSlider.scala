package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.Range
import io.circe.Encoder

final case class RangeSlider(
  visible: Boolean,
  thickness: Number,
  range: Range[Datum],
  borderwidth: Number,
  bordercolor: Color,
  bgcolor: Color,
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
