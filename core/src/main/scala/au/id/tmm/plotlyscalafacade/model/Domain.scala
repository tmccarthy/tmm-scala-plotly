package au.id.tmm.plotlyscalafacade.model

final case class Domain(
  x: Option[NumberArray],
  y: Option[NumberArray],
  row: Option[Number],
  column: Option[Number],
)
