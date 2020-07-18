package au.id.tmm.plotlyscalafacade.model

final case class Margin(
  top: Option[Number] = None,
  bottom: Option[Number] = None,
  left: Option[Number] = None,
  right: Option[Number] = None,
  pad: Option[Number] = None,
)
