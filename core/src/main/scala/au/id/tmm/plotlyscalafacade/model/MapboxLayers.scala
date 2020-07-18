package au.id.tmm.plotlyscalafacade.model

import java.net.URI

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum

final case class MapboxLayers(
  visible: Option[Boolean],
  sourcetype: Option[MapboxLayers.SourceType],
  source: Option[MapboxLayers.Source],
  sourcelayer: Option[String],
  sourceattribution: Option[String],
  `type`: Option[MapboxLayers.Type],
  coordinates: Option[MapboxLayers.Coordinates],
  below: Option[String],
  color: Option[Color],
  opacity: Option[Number],
  minzoom: Option[Number],
  maxzoom: Option[Number],
  circle: Option[MapboxLayers.Circle],
  line: Option[ShapeLine],
  fill: Option[MapboxLayers.Fill],
  symbol: Option[MapboxLayers.MapboxSymbol],
  name: Option[String],
  templateitemname: Option[String],
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
    icon: Option[String],
    iconsize: Option[Number],
    text: Option[String],
    placement: Option[MapboxSymbol.Placement],
    textfont: Option[Font],
    textposition: Option[TextPosition],
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
