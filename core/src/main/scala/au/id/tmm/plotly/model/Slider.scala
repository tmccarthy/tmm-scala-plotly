package au.id.tmm.plotly.model

import au.id.tmm.plotly.model.utilities.{OptArg, JSEnum}
import io.circe.{Encoder, Json}

final case class Slider(
  visible: OptArg[Boolean] = OptArg.Undefined,
  active: OptArg[Number] = OptArg.Undefined,
  steps: OptArg[Array[Slider.Step]] = OptArg.Undefined,
  lenmode: OptArg[Slider.LengthMode] = OptArg.Undefined,
  len: OptArg[Number] = OptArg.Undefined,
  x: OptArg[Number] = OptArg.Undefined,
  y: OptArg[Number] = OptArg.Undefined,
  pad: OptArg[Padding] = OptArg.Undefined,
  xanchor: OptArg[Anchor.X] = OptArg.Undefined,
  yanchor: OptArg[Anchor.Y] = OptArg.Undefined,
  transition: OptArg[Transition] = OptArg.Undefined,
  currentvalue: OptArg[Slider.CurrentValue] = OptArg.Undefined,
  font: OptArg[Font] = OptArg.Undefined,
  activebgcolor: OptArg[Color] = OptArg.Undefined,
  bgcolor: OptArg[Color] = OptArg.Undefined,
  bordercolor: OptArg[Color] = OptArg.Undefined,
  borderwidth: OptArg[Number] = OptArg.Undefined,
  ticklen: OptArg[Number] = OptArg.Undefined,
  tickcolor: OptArg[Color] = OptArg.Undefined,
  tickwidth: OptArg[Number] = OptArg.Undefined,
  minorticklen: OptArg[Number] = OptArg.Undefined,
)

object Slider {
  final case class Step(
    visible: OptArg[Boolean] = OptArg.Undefined,
    method: OptArg[Step.Method] = OptArg.Undefined,
    args: OptArg[Seq[Json]] = OptArg.Undefined,
    label: OptArg[String] = OptArg.Undefined,
    value: OptArg[String] = OptArg.Undefined,
    execute: OptArg[Boolean] = OptArg.Undefined,
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
    visible: OptArg[Boolean] = OptArg.Undefined,
    xanchor: OptArg[Anchor.X] = OptArg.Undefined,
    offset: OptArg[Number] = OptArg.Undefined,
    prefix: OptArg[String] = OptArg.Undefined,
    suffix: OptArg[String] = OptArg.Undefined,
    font: OptArg[Font] = OptArg.Undefined,
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
