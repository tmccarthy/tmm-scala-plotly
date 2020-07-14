package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum

final case class Image(
  visible: Boolean,
  source: String,
  layer: Image.Layer,
  sizex: Number,
  sizey: Number,
  sizing: Image.Sizing,
  opacity: Number,
  x: AxisPosition,
  y: AxisPosition,
  xanchor: Anchor.X,
  yanchor: Anchor.Y,
  xref: Image.CoordinateReference.X,
  yref: Image.CoordinateReference.Y,
)

object Image {

  sealed abstract class Layer(val asString: String) extends JSEnum

  object Layer {
    case object Above extends Layer("above")
    case object Below extends Layer("below")
  }

  sealed abstract class Sizing(val asString: String) extends JSEnum

  object Sizing {
    case object Fill    extends Sizing("fill")
    case object Contain extends Sizing("contain")
    case object Stretch extends Sizing("stretch")
  }

  object CoordinateReference {
    sealed abstract class X(val asString: String) extends JSEnum

    object X {
      case object Paper extends X("paper")
      case object XAxis extends X("x")
    }

    sealed abstract class Y(val asString: String)

    object Y {
      case object Paper extends Y("paper")
      case object YAxis extends Y("y")
    }
  }

}
