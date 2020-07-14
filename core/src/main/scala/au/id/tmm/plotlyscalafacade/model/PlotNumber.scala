package au.id.tmm.plotlyscalafacade.model

final case class PlotNumber(
  valueformat: String,
  font: Partial[Font],
  prefix: String,
  suffix: String,
)
