package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum

sealed abstract class PlotType(val asString: String) extends JSEnum

object PlotType {
  case object Bar            extends PlotType("bar")
  case object Box            extends PlotType("box")
  case object Candlestick    extends PlotType("candlestick")
  case object Choropleth     extends PlotType("choropleth")
  case object Contour        extends PlotType("contour")
  case object Heatmap        extends PlotType("heatmap")
  case object Histogram      extends PlotType("histogram")
  case object Indicator      extends PlotType("indicator")
  case object Mesh3d         extends PlotType("mesh3d")
  case object Ohlc           extends PlotType("ohlc")
  case object ParCoords      extends PlotType("parcoords")
  case object Pie            extends PlotType("pie")
  case object PointCloud     extends PlotType("pointcloud")
  case object Scatter        extends PlotType("scatter")
  case object Scatter3d      extends PlotType("scatter3d")
  case object ScatterGeo     extends PlotType("scattergeo")
  case object ScatterGl      extends PlotType("scattergl")
  case object ScatterPolar   extends PlotType("scatterpolar")
  case object ScatterTernary extends PlotType("scatterternary")
  case object Sunburst       extends PlotType("sunburst")
  case object Surface        extends PlotType("surface")
  case object Treemap        extends PlotType("treemap")
  case object Waterfall      extends PlotType("waterfall")
  case object Funnel         extends PlotType("funnel")
  case object FunnelArea     extends PlotType("funnelarea")
  case object ScatterMapbox  extends PlotType("scattermapbox")
}
