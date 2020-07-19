package au.id.tmm.plotlyscalafacade.model

import java.net.URI

import au.id.tmm.plotlyscalafacade.model.utilities.{OptArg, JSEnum}
import io.circe.Encoder
import io.circe.syntax.EncoderOps

final case class Mapbox(
  domain: OptArg[Domain] = OptArg.Undefined,
  accesstoken: OptArg[String] = OptArg.Undefined,
  style: OptArg[Mapbox.Style] = OptArg.Undefined,
  center: OptArg[Mapbox.Center] = OptArg.Undefined,
  zoom: OptArg[Number] = OptArg.Undefined,
  bearing: OptArg[Number] = OptArg.Undefined,
  pitch: OptArg[Number] = OptArg.Undefined,
  layers: OptArg[Seq[MapboxLayers]] = OptArg.Undefined,
  uirevision: OptArg[Mapbox.UiRevision] = OptArg.Undefined,
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

    implicit val encoder: Encoder[Style] = {
      case p: PlotlyBuiltIn => p.asJson
      case m: MapboxBuiltIn => m.asJson
      case MapboxUri(uri)   => uri.asJson
    }

  }

  final case class Center(
    lon: OptArg[Number] = OptArg.Undefined,
    lat: OptArg[Number] = OptArg.Undefined,
  )

  object Center {
    implicit val encoder: Encoder[Center] = Encoder.forProduct2("lon", "lat")(c => (c.lon, c.lat))
  }

  sealed abstract class UiRevision(val asString: String) extends JSEnum

  object UiRevision {
    case object Center  extends UiRevision("center")
    case object Zoom    extends UiRevision("zoom")
    case object Bearing extends UiRevision("bearing")
    case object Pitch   extends UiRevision("pitch")

  }

  implicit val encoder: Encoder[Mapbox] = Encoder.forProduct9(
    "domain",
    "accesstoken",
    "style",
    "center",
    "zoom",
    "bearing",
    "pitch",
    "layers",
    "uirevision",
  )(m =>
    (
      m.domain,
      m.accesstoken,
      m.style,
      m.center,
      m.zoom,
      m.bearing,
      m.pitch,
      m.layers,
      m.uirevision,
    ),
  )

}
