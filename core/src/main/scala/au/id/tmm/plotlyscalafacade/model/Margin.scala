package au.id.tmm.plotlyscalafacade.model

final case class Margin(
  top: Option[Number],
  bottom: Option[Number],
  left: Option[Number],
  right: Option[Number],
  pad: Option[Number],
)
