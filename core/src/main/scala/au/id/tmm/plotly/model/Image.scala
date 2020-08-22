package au.id.tmm.plotly.model

import au.id.tmm.plotly.model.utilities.{JSEnum, OptArg}
import io.circe.Encoder

final case class Image(
  visible: OptArg[Boolean]                  = OptArg.Undefined,
  source: OptArg[String]                    = OptArg.Undefined,
  layer: OptArg[Image.Layer]                = OptArg.Undefined,
  sizex: OptArg[Number]                     = OptArg.Undefined,
  sizey: OptArg[Number]                     = OptArg.Undefined,
  sizing: OptArg[Image.Sizing]              = OptArg.Undefined,
  opacity: OptArg[Number]                   = OptArg.Undefined,
  x: OptArg[Datum]                          = OptArg.Undefined,
  y: OptArg[Datum]                          = OptArg.Undefined,
  xanchor: OptArg[Anchor.X]                 = OptArg.Undefined,
  yanchor: OptArg[Anchor.Y]                 = OptArg.Undefined,
  xref: OptArg[Image.CoordinateReference.X] = OptArg.Undefined,
  yref: OptArg[Image.CoordinateReference.Y] = OptArg.Undefined,
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
