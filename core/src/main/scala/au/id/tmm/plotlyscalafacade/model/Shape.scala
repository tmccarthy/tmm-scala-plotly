package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{OptArg, JSEnum}
import io.circe.Encoder

final case class Shape(
  visible: OptArg[Boolean] = OptArg.Undefined,
  layer: OptArg[Shape.Layer] = OptArg.Undefined,
  `type`: OptArg[Shape.Type] = OptArg.Undefined,
  path: OptArg[String] = OptArg.Undefined,
  xref: OptArg[Shape.CoordinateReference.X] = OptArg.Undefined,
  xsizemode: OptArg[Shape.SizeMode] = OptArg.Undefined,
  xanchor: OptArg[Anchor.X] = OptArg.Undefined,
  yref: OptArg[Shape.CoordinateReference.Y] = OptArg.Undefined,
  ysizemode: OptArg[Shape.SizeMode] = OptArg.Undefined,
  yanchor: OptArg[Anchor.Y] = OptArg.Undefined,
  x0: OptArg[Datum] = OptArg.Undefined,
  y0: OptArg[Datum] = OptArg.Undefined,
  x1: OptArg[Datum] = OptArg.Undefined,
  y1: OptArg[Datum] = OptArg.Undefined,
  fillcolor: OptArg[Color] = OptArg.Undefined,
  name: OptArg[String] = OptArg.Undefined,
  templateitemname: OptArg[String] = OptArg.Undefined,
  opacity: OptArg[Number] = OptArg.Undefined,
  line: OptArg[ShapeLine] = OptArg.Undefined,
)

object Shape {
  sealed abstract class Layer(val asString: String) extends JSEnum

  object Layer {
    case object Below extends Layer("below")
    case object Above extends Layer("above")
  }

  sealed abstract class Type(val asString: String) extends JSEnum

  object Type {
    case object Rect   extends Type("rect")
    case object Circle extends Type("circle")
    case object Line   extends Type("line")
    case object Path   extends Type("path")
  }

  object CoordinateReference {
    sealed abstract class X(val asString: String) extends JSEnum

    object X {
      case object XAxis extends X("x")
      case object Paper extends X("paper")
    }

    sealed abstract class Y(val asString: String) extends JSEnum

    object Y {
      case object YAxis extends Y("y")
      case object Paper extends Y("paper")
    }
  }

  sealed abstract class SizeMode(val asString: String) extends JSEnum

  object SizeMode {
    case object Scaled extends SizeMode("scaled")
    case object Pixel  extends SizeMode("pixel")
  }

  implicit val encoder: Encoder[Shape] = Encoder.forProduct19(
    "visible",
    "layer",
    "type",
    "path",
    "xref",
    "xsizemode",
    "xanchor",
    "yref",
    "ysizemode",
    "yanchor",
    "x0",
    "y0",
    "x1",
    "y1",
    "fillcolor",
    "name",
    "templateitemname",
    "opacity",
    "line",
  )(s =>
    (
      s.visible,
      s.layer,
      s.`type`,
      s.path,
      s.xref,
      s.xsizemode,
      s.xanchor,
      s.yref,
      s.ysizemode,
      s.yanchor,
      s.x0,
      s.y0,
      s.x1,
      s.y1,
      s.fillcolor,
      s.name,
      s.templateitemname,
      s.opacity,
      s.line,
    ),
  )
}
