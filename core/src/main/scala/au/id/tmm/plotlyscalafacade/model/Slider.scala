package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum
import io.circe.Json

final case class Slider(
  visible: Option[Boolean] = None,
  active: Option[Number] = None,
  steps: Option[Array[Slider.Step]] = None,
  lenmode: Option[Slider.LengthMode] = None,
  len: Option[Number] = None,
  x: Option[Number] = None,
  y: Option[Number] = None,
  pad: Option[Padding] = None,
  xanchor: Option[Anchor.X] = None,
  yanchor: Option[Anchor.Y] = None,
  transition: Option[Transition] = None,
  currentvalue: Option[Slider.CurrentValue] = None,
  font: Option[Font] = None,
  activebgcolor: Option[Color] = None,
  bgcolor: Option[Color] = None,
  bordercolor: Option[Color] = None,
  borderwidth: Option[Number] = None,
  ticklen: Option[Number] = None,
  tickcolor: Option[Color] = None,
  tickwidth: Option[Number] = None,
  minorticklen: Option[Number] = None,
)

object Slider {
  final case class Step(
    visible: Option[Boolean] = None,
    method: Option[Step.Method] = None,
    args: Option[Seq[Json]] = None,
    label: Option[String] = None,
    value: Option[String] = None,
    execute: Option[Boolean] = None,
  )

  object Step {
    sealed abstract class Method(val asString: String) extends JSEnum

    object Method {
      case object Animate  extends Method("animate")
      case object Relayout extends Method("relayout")
      case object Restyle  extends Method("restyle")
      case object Skip     extends Method("skip")
      case object Update   extends Method("update")
    }
  }

  sealed abstract class LengthMode(val asString: String) extends JSEnum

  object LengthMode {
    case object Fraction extends LengthMode("fraction")
    case object Pixels   extends LengthMode("pixels")
  }

  final case class CurrentValue(
    visible: Option[Boolean] = None,
    xanchor: Option[Anchor.X] = None,
    offset: Option[Number] = None,
    prefix: Option[String] = None,
    suffix: Option[String] = None,
    font: Option[Font] = None,
  )
}
