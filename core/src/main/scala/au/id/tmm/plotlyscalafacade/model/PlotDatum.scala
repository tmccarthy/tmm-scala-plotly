package au.id.tmm.plotlyscalafacade.model

final case class PlotDatum(
  curveNumber: Number,
  data: PlotData,
  customdata: Datum,
  pointIndex: Number,
  pointNumber: Number,
  x: Datum,
  xaxis: LayoutAxis,
  y: Datum,
  yaxis: LayoutAxis,
)
