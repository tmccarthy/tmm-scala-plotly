package au.id.tmm.plotlyscalafacade.model

final case class RangeSlider(
  visible: Boolean,
  thickness: Number,
  range: Range[Datum],
  borderwidth: Number,
  bordercolor: Color,
  bgcolor: Color,
)
