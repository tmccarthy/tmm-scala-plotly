package au.id.tmm.plotlyscalafacade.model

final case class ShapeLine(
  color: Option[Color] = None,
  width: Option[Number] = None,
  dash: Option[Dash] = None,
)
