package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum
import io.circe.Json

final case class Slider(
  visible: Boolean,
  active: Number,
  steps: Array[Partial[Slider.Step]],
  lenmode: Slider.LengthMode,
  len: Number,
  x: Number,
  y: Number,
  pad: Partial[Padding],
  xanchor: Anchor.X,
  yanchor: Anchor.Y,
  transition: Transition,
  currentvalue: Slider.CurrentValue,
  font: Font,
  activebgcolor: Color,
  bgcolor: Color,
  bordercolor: Color,
  borderwidth: Number,
  ticklen: Number,
  tickcolor: Color,
  tickwidth: Number,
  minorticklen: Number,
)

object Slider {
  final case class Step(
    visible: Boolean,
    method: Step.Method,
    args: Seq[Json],
    label: String,
    value: String,
    execute: Boolean,
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
    visible: Boolean,
    xanchor: Anchor.X,
    offset: Number,
    prefix: String,
    suffix: String,
    font: Partial[Font],
  )
}
