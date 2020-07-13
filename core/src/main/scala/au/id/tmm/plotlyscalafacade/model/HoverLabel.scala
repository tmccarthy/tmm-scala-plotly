package au.id.tmm.plotlyscalafacade.model

final case class HoverLabel(
  bgcolor: Color,
  bordercolor: Color,
  font: Partial[Font],
  align: LabelAlignment.Horizontal,
  namelength: Number,
) extends Label
