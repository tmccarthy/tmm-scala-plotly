package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum
import io.circe.Encoder

final case class Image(
  visible: Option[Boolean] = None,
  source: Option[String] = None,
  layer: Option[Image.Layer] = None,
  sizex: Option[Number] = None,
  sizey: Option[Number] = None,
  sizing: Option[Image.Sizing] = None,
  opacity: Option[Number] = None,
  x: Option[AxisPosition] = None,
  y: Option[AxisPosition] = None,
  xanchor: Option[Anchor.X] = None,
  yanchor: Option[Anchor.Y] = None,
  xref: Option[Image.CoordinateReference.X] = None,
  yref: Option[Image.CoordinateReference.Y] = None,
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

    sealed abstract class Y(val asString: String) extends JSEnum

    object Y {
      case object Paper extends Y("paper")
      case object YAxis extends Y("y")
    }
  }

  implicit val encoder: Encoder[Image] = Encoder.forProduct13(
    "visible",
    "source",
    "layer",
    "sizex",
    "sizey",
    "sizing",
    "opacity",
    "x",
    "y",
    "xanchor",
    "yanchor",
    "xref",
    "yref",
  )(i =>
    (
      i.visible,
      i.source,
      i.layer,
      i.sizex,
      i.sizey,
      i.sizing,
      i.opacity,
      i.x,
      i.y,
      i.xanchor,
      i.yanchor,
      i.xref,
      i.yref,
    ),
  )

}
