package au.id.tmm.plotlyscalafacade

import java.net.URI

sealed abstract class PlotlyVersion(val minifiedJsName: String) {
  def cdnUri: URI = URI.create(s"https://cdn.plot.ly/$minifiedJsName")
}

object PlotlyVersion {
  case object Latest extends PlotlyVersion("plotly-latest.min.js")
  final case class Specific(version: String) extends PlotlyVersion(s"plotly-$version.min.js")
}