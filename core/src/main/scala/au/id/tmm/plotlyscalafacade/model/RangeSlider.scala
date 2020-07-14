package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.Range

final case class RangeSlider(
  visible: Boolean,
  thickness: Number,
  range: Range[Datum],
  borderwidth: Number,
  bordercolor: Color,
  bgcolor: Color,
)
