package au.id.tmm.plotlyscalafacade.model

import java.net.URI

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum

final case class Mapbox(
  domain: Option[Domain] = None,
  accesstoken: Option[String] = None,
  style: Option[Mapbox.Style] = None,
  center: Option[Mapbox.Center] = None,
  zoom: Option[Number] = None,
  bearing: Option[Number] = None,
  pitch: Option[Number] = None,
  layers: Option[Seq[MapboxLayers]] = None,
  uirevision: Option[Mapbox.UiRevision] = None,
)

object Mapbox {

  sealed trait Style

  object Style {

    sealed abstract class PlotlyBuiltIn(val asString: String) extends Style with JSEnum

    object PlotlyBuiltIn {
      case object OpenStreetMap    extends PlotlyBuiltIn("open-street-map")
      case object WhiteBg          extends PlotlyBuiltIn("white-bg")
      case object CartoPositron    extends PlotlyBuiltIn("carto-positron")
      case object CartoDarkmatter  extends PlotlyBuiltIn("carto-darkmatter")
      case object StamenTerrain    extends PlotlyBuiltIn("stamen-terrain")
      case object StamenToner      extends PlotlyBuiltIn("stamen-toner")
      case object StamenWatercolor extends PlotlyBuiltIn("stamen-watercolor")
    }

    sealed abstract class MapboxBuiltIn(val asString: String) extends Style with JSEnum

    object MapboxBuiltIn {
      case object Basic            extends MapboxBuiltIn("basic")
      case object Streets          extends MapboxBuiltIn("streets")
      case object Outdoors         extends MapboxBuiltIn("outdoors")
      case object Light            extends MapboxBuiltIn("light")
      case object Dark             extends MapboxBuiltIn("dark")
      case object Satellite        extends MapboxBuiltIn("satellite")
      case object SatelliteStreets extends MapboxBuiltIn("satellite-streets")
    }

    final case class MapboxUri(uri: URI) extends Style

  }

  final case class Center(
    lon: Option[Number] = None,
    lat: Option[Number] = None,
  )

  sealed abstract class UiRevision(val asString: String) extends JSEnum

  object UiRevision {
    case object Center  extends UiRevision("center")
    case object Zoom    extends UiRevision("zoom")
    case object Bearing extends UiRevision("bearing")
    case object Pitch   extends UiRevision("pitch")

  }

}
