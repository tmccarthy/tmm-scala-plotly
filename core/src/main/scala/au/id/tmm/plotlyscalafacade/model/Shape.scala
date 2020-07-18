package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{Arg, JSEnum}
import io.circe.Encoder

final case class Shape(
  visible: Arg[Boolean] = Arg.Undefined,
  layer: Arg[Shape.Layer] = Arg.Undefined,
  `type`: Arg[Shape.Type] = Arg.Undefined,
  path: Arg[String] = Arg.Undefined,
  xref: Arg[Shape.CoordinateReference.X] = Arg.Undefined,
  xsizemode: Arg[Shape.SizeMode] = Arg.Undefined,
  xanchor: Arg[Anchor.X] = Arg.Undefined,
  yref: Arg[Shape.CoordinateReference.Y] = Arg.Undefined,
  ysizemode: Arg[Shape.SizeMode] = Arg.Undefined,
  yanchor: Arg[Anchor.Y] = Arg.Undefined,
  x0: Arg[Datum] = Arg.Undefined,
  y0: Arg[Datum] = Arg.Undefined,
  x1: Arg[Datum] = Arg.Undefined,
  y1: Arg[Datum] = Arg.Undefined,
  fillcolor: Arg[Color] = Arg.Undefined,
  name: Arg[String] = Arg.Undefined,
  templateitemname: Arg[String] = Arg.Undefined,
  opacity: Arg[Number] = Arg.Undefined,
  line: Arg[ShapeLine] = Arg.Undefined,
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
