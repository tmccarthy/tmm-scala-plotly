package au.id.tmm.plotlyscalafacade.model

import java.net.URI

import au.id.tmm.plotlyscalafacade.model.utilities.{Arg, JSEnum, OneOrArrayOf}
import io.circe.Encoder

final case class MapboxLayers(
  visible: Arg[Boolean] = Arg.Undefined,
  sourcetype: Arg[MapboxLayers.SourceType] = Arg.Undefined,
  source: Arg[OneOrArrayOf[URI]] = Arg.Undefined,
  sourcelayer: Arg[String] = Arg.Undefined,
  sourceattribution: Arg[String] = Arg.Undefined,
  `type`: Arg[MapboxLayers.Type] = Arg.Undefined,
  coordinates: Arg[MapboxLayers.Coordinates] = Arg.Undefined,
  below: Arg[String] = Arg.Undefined,
  color: Arg[Color] = Arg.Undefined,
  opacity: Arg[Number] = Arg.Undefined,
  minzoom: Arg[Number] = Arg.Undefined,
  maxzoom: Arg[Number] = Arg.Undefined,
  circle: Arg[MapboxLayers.Circle] = Arg.Undefined,
  line: Arg[ShapeLine] = Arg.Undefined,
  fill: Arg[MapboxLayers.Fill] = Arg.Undefined,
  symbol: Arg[MapboxLayers.MapboxSymbol] = Arg.Undefined,
  name: Arg[String] = Arg.Undefined,
  templateitemname: Arg[String] = Arg.Undefined,
)

object MapboxLayers {

  sealed abstract class SourceType(val asString: String) extends JSEnum

  object SourceType {
    case object Geojson extends SourceType("geojson")
    case object Vector  extends SourceType("vector")
    case object Raster  extends SourceType("raster")
    case object Image   extends SourceType("image")
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

    object Point {
      implicit val encoder: Encoder[Point] =
        Encoder.forProduct2("longitude", "latitude")(p => (p.longitude, p.latitude))
    }

    implicit val encoder: Encoder[Coordinates] = Encoder.forProduct4(
      "topLeft",
      "topRight",
      "bottomRight",
      "bottomLeft",
    )(c =>
      (
        c.topLeft,
        c.topRight,
        c.bottomRight,
        c.bottomLeft,
      ),
    )
  }

  final case class Circle(radius: Number)

  object Circle {
    implicit val encoder: Encoder[Circle] = Encoder.forProduct1("radius")(_.radius)
  }

  final case class Fill(outlinecolor: Color)

  object Fill {
    implicit val encoder: Encoder[Fill] = Encoder.forProduct1("outlinecolor")(_.outlinecolor)
  }

  final case class MapboxSymbol(
    icon: Arg[String] = Arg.Undefined,
    iconsize: Arg[Number] = Arg.Undefined,
    text: Arg[String] = Arg.Undefined,
    placement: Arg[MapboxSymbol.Placement] = Arg.Undefined,
    textfont: Arg[Font] = Arg.Undefined,
    textposition: Arg[TextPosition] = Arg.Undefined,
  )

  object MapboxSymbol {

    sealed abstract class Placement(val asString: String) extends JSEnum

    object Placement {
      case object Point      extends Placement("point")
      case object Line       extends Placement("line")
      case object LineCenter extends Placement("line-center")
    }

    implicit val encoder: Encoder[MapboxSymbol] = Encoder.forProduct6(
      "icon",
      "iconsize",
      "text",
      "placement",
      "textfont",
      "textposition",
    )(m =>
      (
        m.icon,
        m.iconsize,
        m.text,
        m.placement,
        m.textfont,
        m.textposition,
      ),
    )

  }

  implicit val encoder: Encoder[MapboxLayers] = Encoder.forProduct18(
    "visible",
    "sourcetype",
    "source",
    "sourcelayer",
    "sourceattribution",
    "type",
    "coordinates",
    "below",
    "color",
    "opacity",
    "minzoom",
    "maxzoom",
    "circle",
    "line",
    "fill",
    "symbol",
    "name",
    "templateitemname",
  )(m =>
    (
      m.visible,
      m.sourcetype,
      m.source,
      m.sourcelayer,
      m.sourceattribution,
      m.`type`,
      m.coordinates,
      m.below,
      m.color,
      m.opacity,
      m.minzoom,
      m.maxzoom,
      m.circle,
      m.line,
      m.fill,
      m.symbol,
      m.name,
      m.templateitemname,
    ),
  )

}
