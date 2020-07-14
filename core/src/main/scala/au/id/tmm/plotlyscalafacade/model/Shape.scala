package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum

final case class Shape(
  visible: Boolean,
  layer: Shape.Layer,
  `type`: Shape.Type,
  path: String,
  xref: Shape.CoordinateReference.X,
  xsizemode: Shape.SizeMode,
  xanchor: Anchor.X,
  yref: Shape.CoordinateReference.Y,
  ysizemode: Shape.SizeMode,
  yanchor: Anchor.Y,
  x0: Datum,
  y0: Datum,
  x1: Datum,
  y1: Datum,
  fillcolor: Color,
  name: String,
  templateitemname: String,
  opacity: Number,
  line: Partial[ShapeLine],
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
