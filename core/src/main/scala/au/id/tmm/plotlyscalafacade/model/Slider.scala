package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{Arg, JSEnum}
import io.circe.{Encoder, Json}

final case class Slider(
  visible: Arg[Boolean] = Arg.Undefined,
  active: Arg[Number] = Arg.Undefined,
  steps: Arg[Array[Slider.Step]] = Arg.Undefined,
  lenmode: Arg[Slider.LengthMode] = Arg.Undefined,
  len: Arg[Number] = Arg.Undefined,
  x: Arg[Number] = Arg.Undefined,
  y: Arg[Number] = Arg.Undefined,
  pad: Arg[Padding] = Arg.Undefined,
  xanchor: Arg[Anchor.X] = Arg.Undefined,
  yanchor: Arg[Anchor.Y] = Arg.Undefined,
  transition: Arg[Transition] = Arg.Undefined,
  currentvalue: Arg[Slider.CurrentValue] = Arg.Undefined,
  font: Arg[Font] = Arg.Undefined,
  activebgcolor: Arg[Color] = Arg.Undefined,
  bgcolor: Arg[Color] = Arg.Undefined,
  bordercolor: Arg[Color] = Arg.Undefined,
  borderwidth: Arg[Number] = Arg.Undefined,
  ticklen: Arg[Number] = Arg.Undefined,
  tickcolor: Arg[Color] = Arg.Undefined,
  tickwidth: Arg[Number] = Arg.Undefined,
  minorticklen: Arg[Number] = Arg.Undefined,
)

object Slider {
  final case class Step(
    visible: Arg[Boolean] = Arg.Undefined,
    method: Arg[Step.Method] = Arg.Undefined,
    args: Arg[Seq[Json]] = Arg.Undefined,
    label: Arg[String] = Arg.Undefined,
    value: Arg[String] = Arg.Undefined,
    execute: Arg[Boolean] = Arg.Undefined,
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
    visible: Arg[Boolean] = Arg.Undefined,
    xanchor: Arg[Anchor.X] = Arg.Undefined,
    offset: Arg[Number] = Arg.Undefined,
    prefix: Arg[String] = Arg.Undefined,
    suffix: Arg[String] = Arg.Undefined,
    font: Arg[Font] = Arg.Undefined,
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
