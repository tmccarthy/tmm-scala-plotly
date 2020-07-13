package au.id.tmm.plotlyscalafacade.model

final case class HoverLabel(
  bgcolor: Color,
  bordercolor: Color,
  font: Partial[Font],
  align: HorizontalLabelAlignment,
  namelength: Number,
) extends Label
