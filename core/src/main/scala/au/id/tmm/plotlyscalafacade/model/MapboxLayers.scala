package au.id.tmm.plotlyscalafacade.model

import java.net.URI

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum

final case class MapboxLayers(
  visible: Boolean,
  sourcetype: MapboxLayers.SourceType,
  source: MapboxLayers.Source,
  sourcelayer: String,
  sourceattribution: String,
  `type`: MapboxLayers.Type,
  coordinates: MapboxLayers.Coordinates,
  below: String,
  color: Color,
  opacity: Number,
  minzoom: Number,
  maxzoom: Number,
  circle: MapboxLayers.Circle,
  line: Partial[ShapeLine],
  fill: MapboxLayers.Fill,
  symbol: Partial[MapboxLayers.MapboxSymbol],
  name: String,
  templateitemname: String,
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
    icon: String,
    iconsize: Number,
    text: String,
    placement: MapboxSymbol.Placement,
    textfont: Partial[Font],
    textposition: TextPosition,
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
