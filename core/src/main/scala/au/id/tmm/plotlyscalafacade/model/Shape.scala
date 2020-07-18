package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum

final case class Shape(
  visible: Option[Boolean],
  layer: Option[Shape.Layer],
  `type`: Option[Shape.Type],
  path: Option[String],
  xref: Option[Shape.CoordinateReference.X],
  xsizemode: Option[Shape.SizeMode],
  xanchor: Option[Anchor.X],
  yref: Option[Shape.CoordinateReference.Y],
  ysizemode: Option[Shape.SizeMode],
  yanchor: Option[Anchor.Y],
  x0: Option[Datum],
  y0: Option[Datum],
  x1: Option[Datum],
  y1: Option[Datum],
  fillcolor: Option[Color],
  name: Option[String],
  templateitemname: Option[String],
  opacity: Option[Number],
  line: Option[ShapeLine],
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
