package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.OptArg
import io.circe.Encoder

final case class RangeSlider(
  visible: OptArg[Boolean] = OptArg.Undefined,
  thickness: OptArg[Number] = OptArg.Undefined,
  range: OptArg[Range[Datum]] = OptArg.Undefined,
  borderwidth: OptArg[Number] = OptArg.Undefined,
  bordercolor: OptArg[Color] = OptArg.Undefined,
  bgcolor: OptArg[Color] = OptArg.Undefined,
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
