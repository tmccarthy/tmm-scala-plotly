package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum

final case class Shape(
  visible: Option[Boolean] = None,
  layer: Option[Shape.Layer] = None,
  `type`: Option[Shape.Type] = None,
  path: Option[String] = None,
  xref: Option[Shape.CoordinateReference.X] = None,
  xsizemode: Option[Shape.SizeMode] = None,
  xanchor: Option[Anchor.X] = None,
  yref: Option[Shape.CoordinateReference.Y] = None,
  ysizemode: Option[Shape.SizeMode] = None,
  yanchor: Option[Anchor.Y] = None,
  x0: Option[Datum] = None,
  y0: Option[Datum] = None,
  x1: Option[Datum] = None,
  y1: Option[Datum] = None,
  fillcolor: Option[Color] = None,
  name: Option[String] = None,
  templateitemname: Option[String] = None,
  opacity: Option[Number] = None,
  line: Option[ShapeLine] = None,
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
}
