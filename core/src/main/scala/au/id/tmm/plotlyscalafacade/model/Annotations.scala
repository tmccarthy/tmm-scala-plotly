package au.id.tmm.plotlyscalafacade.model

import io.circe.{Encoder, Json}

final case class Annotations(
  bgcolor: Color,
  bordercolor: Color,
  font: Partial[Font],
  visible: Boolean,
  text: String,
  textangle: String,
  width: Number,
  height: Number,
  opacity: Number,
  align: LabelAlignment.Horizontal,
  valign: LabelAlignment.Vertical,
  borderpad: Number,
  borderwidth: Number,
  showarrow: Boolean,
  arrowcolor: String,
  arrowhead: Number,
  startarrowhead: Number,
  arrowside: Annotations.ArrowSide,
  startarrowsize: Number,
  arrowwidth: Number,
  standoff: Number,
  startstandoff: Number,
  ax: Number,
  ay: Number,
  xref: Annotations.CoordinateReference.X,
  x: AxisPosition,
  xanchor: Anchor.X,
  xshift: Number,
  yref: Annotations.CoordinateReference.Y,
  y: AxisPosition,
  yanchor: Anchor.Y,
  yshift: Number,
  clicktoshow: Annotations.ClickToShow,
  xclick: AxisPosition,
  yclick: AxisPosition,
  hovertext: String,
  hoverlabel: Partial[HoverLabel],
  captureevents: Boolean,
) extends Label

object Annotations {

  sealed abstract class ArrowSide(val asString: String) extends JSEnum

  object ArrowSide {
    case object End   extends ArrowSide("end")
    case object Start extends ArrowSide("start")
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

  sealed abstract class ClickToShow

  object ClickToShow {
    case object False extends ClickToShow
    case object OnOff extends ClickToShow
    case object OnOut extends ClickToShow

    implicit val encoder: Encoder[ClickToShow] = {
      case False => Json.fromBoolean(false)
      case OnOff => Json.fromString("onoff")
      case OnOut => Json.fromString("onout")
    }
  }

}
