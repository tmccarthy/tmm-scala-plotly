package au.id.tmm.plotlyscalafacade.model

import java.net.URI

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum

final case class MapboxLayers(
  visible: Option[Boolean] = None,
  sourcetype: Option[MapboxLayers.SourceType] = None,
  source: Option[MapboxLayers.Source] = None,
  sourcelayer: Option[String] = None,
  sourceattribution: Option[String] = None,
  `type`: Option[MapboxLayers.Type] = None,
  coordinates: Option[MapboxLayers.Coordinates] = None,
  below: Option[String] = None,
  color: Option[Color] = None,
  opacity: Option[Number] = None,
  minzoom: Option[Number] = None,
  maxzoom: Option[Number] = None,
  circle: Option[MapboxLayers.Circle] = None,
  line: Option[ShapeLine] = None,
  fill: Option[MapboxLayers.Fill] = None,
  symbol: Option[MapboxLayers.MapboxSymbol] = None,
  name: Option[String] = None,
  templateitemname: Option[String] = None,
)

object MapboxLayers {

  sealed abstract class SourceType(val asString: String) extends JSEnum

  object SourceType {
    case object Geojson extends SourceType("geojson")
    case object Vector  extends SourceType("vector")
    case object Raster  extends SourceType("raster")
    case object Image   extends SourceType("image")
  }

  sealed trait Source

  object Source {
    final case class OfUrl(uri: URI)        extends Source
    final case class OfUrls(uris: Seq[URI]) extends Source
  }

  sealed abstract class Type(val asString: String) extends JSEnum

  object Type {
    case object Circle extends Type("circle")
    case object Line   extends Type("line")
    case object Fill   extends Type("fill")
    case object Symbol extends Type("symbol")
    case object Raster extends Type("raster")
  }

  final case class Coordinates(
    topLeft: Coordinates.Point,
    topRight: Coordinates.Point,
    bottomRight: Coordinates.Point,
    bottomLeft: Coordinates.Point,
  )

  object Coordinates {
    final case class Point(longitude: Number, latitude: Number)
  }

  final case class Circle(radius: Number)

  final case class Fill(outlineColor: Color)

  final case class MapboxSymbol(
    icon: Option[String] = None,
    iconsize: Option[Number] = None,
    text: Option[String] = None,
    placement: Option[MapboxSymbol.Placement] = None,
    textfont: Option[Font] = None,
    textposition: Option[TextPosition] = None,
  )

  object MapboxSymbol {

    sealed abstract class Placement(val asString: String) extends JSEnum

    object Placement {
      case object Point      extends Placement("point")
      case object Line       extends Placement("line")
      case object LineCenter extends Placement("line-center")
    }

  }

}
