package au.id.tmm.plotlyscalafacade.model

final case class Domain(
  x: Option[NumberArray] = None,
  y: Option[NumberArray] = None,
  row: Option[Number] = None,
  column: Option[Number] = None,
)
