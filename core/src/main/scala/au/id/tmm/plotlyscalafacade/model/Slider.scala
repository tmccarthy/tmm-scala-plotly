package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum
import io.circe.Json

final case class Slider(
  visible: Option[Boolean],
  active: Option[Number],
  steps: Option[Array[Slider.Step]],
  lenmode: Option[Slider.LengthMode],
  len: Option[Number],
  x: Option[Number],
  y: Option[Number],
  pad: Option[Padding],
  xanchor: Option[Anchor.X],
  yanchor: Option[Anchor.Y],
  transition: Option[Transition],
  currentvalue: Option[Slider.CurrentValue],
  font: Option[Font],
  activebgcolor: Option[Color],
  bgcolor: Option[Color],
  bordercolor: Option[Color],
  borderwidth: Option[Number],
  ticklen: Option[Number],
  tickcolor: Option[Color],
  tickwidth: Option[Number],
  minorticklen: Option[Number],
)

object Slider {
  final case class Step(
    visible: Option[Boolean],
    method: Option[Step.Method],
    args: Option[Seq[Json]],
    label: Option[String],
    value: Option[String],
    execute: Option[Boolean],
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
    visible: Option[Boolean],
    xanchor: Option[Anchor.X],
    offset: Option[Number],
    prefix: Option[String],
    suffix: Option[String],
    font: Option[Font],
  )
}
