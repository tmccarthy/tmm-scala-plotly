package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum
import io.circe.{Encoder, Json}

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

    implicit val encoder: Encoder[Step] = Encoder.forProduct6(
      "visible",
      "method",
      "args",
      "label",
      "value",
      "execute",
    )(s =>
      (
        s.visible,
        s.method,
        s.args,
        s.label,
        s.value,
        s.execute,
      ),
    )
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

  object CurrentValue {
    implicit val encoder: Encoder[CurrentValue] = Encoder.forProduct6(
      "visible",
      "xanchor",
      "offset",
      "prefix",
      "suffix",
      "font",
    )(c =>
      (
        c.visible,
        c.xanchor,
        c.offset,
        c.prefix,
        c.suffix,
        c.font,
      ),
    )
  }

  implicit val encoder: Encoder[Slider] = Encoder.forProduct21(
    "visible",
    "active",
    "steps",
    "lenmode",
    "len",
    "x",
    "y",
    "pad",
    "xanchor",
    "yanchor",
    "transition",
    "currentvalue",
    "font",
    "activebgcolor",
    "bgcolor",
    "bordercolor",
    "borderwidth",
    "ticklen",
    "tickcolor",
    "tickwidth",
    "minorticklen",
  )(s =>
    (
      s.visible,
      s.active,
      s.steps,
      s.lenmode,
      s.len,
      s.x,
      s.y,
      s.pad,
      s.xanchor,
      s.yanchor,
      s.transition,
      s.currentvalue,
      s.font,
      s.activebgcolor,
      s.bgcolor,
      s.bordercolor,
      s.borderwidth,
      s.ticklen,
      s.tickcolor,
      s.tickwidth,
      s.minorticklen,
    ),
  )
}
