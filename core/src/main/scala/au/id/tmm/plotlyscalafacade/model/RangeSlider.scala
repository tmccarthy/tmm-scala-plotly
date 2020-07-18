package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.Arg
import io.circe.Encoder

final case class RangeSlider(
  visible: Arg[Boolean] = Arg.Undefined,
  thickness: Arg[Number] = Arg.Undefined,
  range: Arg[Range[Datum]] = Arg.Undefined,
  borderwidth: Arg[Number] = Arg.Undefined,
  bordercolor: Arg[Color] = Arg.Undefined,
  bgcolor: Arg[Color] = Arg.Undefined,
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
