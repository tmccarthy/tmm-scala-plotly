package au.id.tmm.plotlyscalafacade.model

final case class PlotScatterDataPoint(
  curveNumber: Number,
  data: PlotData,
  pointIndex: Number,
  pointNumber: Number,
  x: Number,
  xaxis: LayoutAxis,
  y: Number,
  yaxis: LayoutAxis,
)
