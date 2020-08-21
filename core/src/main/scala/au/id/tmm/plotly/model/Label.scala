package au.id.tmm.plotly.model

import au.id.tmm.plotly.model.utilities.{OptArg, FalseOr, JSEnum}
import io.circe.syntax._
import io.circe.{Encoder, Json}

sealed trait Label extends Product {
  def bgcolor: OptArg[Color]
  def bordercolor: OptArg[Color]
  def font: OptArg[Font]
}

object Label {

  def apply(
    bgcolor: OptArg[Color]     = OptArg.Undefined,
    bordercolor: OptArg[Color] = OptArg.Undefined,
    font: OptArg[Font]         = OptArg.Undefined,
  ): PlainLabel = PlainLabel(bgcolor, bordercolor, font)

  final case class PlainLabel(
    bgcolor: OptArg[Color]     = OptArg.Undefined,
    bordercolor: OptArg[Color] = OptArg.Undefined,
    font: OptArg[Font]         = OptArg.Undefined,
  ) extends Label

  object Alignment {

    sealed abstract class Horizontal(val asString: String) extends JSEnum

    object Horizontal {
      case object Left  extends Horizontal("left")
      case object Right extends Horizontal("right")
      case object Auto  extends Horizontal("auto")
    }

    sealed abstract class Vertical(val asString: String) extends JSEnum

    object Vertical {
      case object Top    extends Vertical("top")
      case object Middle extends Vertical("middle")
      case object Bottom extends Vertical("bottom")
    }

  }

}

final case class HoverLabel(
  bgcolor: OptArg[Color]                    = OptArg.Undefined,
  bordercolor: OptArg[Color]                = OptArg.Undefined,
  font: OptArg[Font]                        = OptArg.Undefined,
  align: OptArg[Label.Alignment.Horizontal] = OptArg.Undefined,
  namelength: OptArg[Number]                = OptArg.Undefined,
) extends Label

object HoverLabel {
  implicit val encoder: Encoder[HoverLabel] = Encoder.forProduct5(
    "bgcolor",
    "bordercolor",
    "font",
    "align",
    "namelength",
  )(l =>
    (
      l.bgcolor,
      l.bordercolor,
      l.font,
      l.align,
      l.namelength,
    ),
  )
}

final case class Legend(
  bgcolor: OptArg[Color]                  = OptArg.Undefined,
  bordercolor: OptArg[Color]              = OptArg.Undefined,
  font: OptArg[Font]                      = OptArg.Undefined,
  traceorder: OptArg[Legend.TraceOrder]   = OptArg.Undefined,
  x: OptArg[Number]                       = OptArg.Undefined,
  y: OptArg[Number]                       = OptArg.Undefined,
  borderwidth: OptArg[Number]             = OptArg.Undefined,
  orientation: OptArg[Legend.Orientation] = OptArg.Undefined,
  tracegroupgap: OptArg[Number]           = OptArg.Undefined,
  xanchor: OptArg[Anchor.X]               = OptArg.Undefined,
  yanchor: OptArg[Anchor.Y]               = OptArg.Undefined,
) extends Label

object Legend {
  sealed abstract class TraceOrder(val asString: String) extends JSEnum

  object TraceOrder {
    case object Grouped  extends TraceOrder("grouped")
    case object Normal   extends TraceOrder("normal")
    case object Reversed extends TraceOrder("reversed")
  }

  sealed abstract class Orientation(val asString: String) extends JSEnum

  object Orientation {
    case object Vertical   extends Orientation("v")
    case object Horizontal extends Orientation("h")
  }

  implicit val encoder: Encoder[Legend] = Encoder.forProduct11(
    "bgcolor",
    "bordercolor",
    "font",
    "traceorder",
    "x",
    "y",
    "borderwidth",
    "orientation",
    "tracegroupgap",
    "xanchor",
    "yanchor",
  )(l =>
    (
      l.bgcolor,
      l.bordercolor,
      l.font,
      l.traceorder,
      l.x,
      l.y,
      l.borderwidth,
      l.orientation,
      l.tracegroupgap,
      l.xanchor,
      l.yanchor,
    ),
  )
}

final case class Annotations(
  bgcolor: OptArg[Color]                                = OptArg.Undefined,
  bordercolor: OptArg[Color]                            = OptArg.Undefined,
  font: OptArg[Font]                                    = OptArg.Undefined,
  visible: OptArg[Boolean]                              = OptArg.Undefined,
  text: OptArg[String]                                  = OptArg.Undefined,
  textangle: OptArg[String]                             = OptArg.Undefined,
  width: OptArg[Number]                                 = OptArg.Undefined,
  height: OptArg[Number]                                = OptArg.Undefined,
  opacity: OptArg[Number]                               = OptArg.Undefined,
  align: OptArg[Label.Alignment.Horizontal]             = OptArg.Undefined,
  valign: OptArg[Label.Alignment.Vertical]              = OptArg.Undefined,
  borderpad: OptArg[Number]                             = OptArg.Undefined,
  borderwidth: OptArg[Number]                           = OptArg.Undefined,
  showarrow: OptArg[Boolean]                            = OptArg.Undefined,
  arrowcolor: OptArg[String]                            = OptArg.Undefined,
  arrowhead: OptArg[Number]                             = OptArg.Undefined,
  startarrowhead: OptArg[Number]                        = OptArg.Undefined,
  arrowside: OptArg[Annotations.ArrowSide]              = OptArg.Undefined,
  startarrowsize: OptArg[Number]                        = OptArg.Undefined,
  arrowwidth: OptArg[Number]                            = OptArg.Undefined,
  standoff: OptArg[Number]                              = OptArg.Undefined,
  startstandoff: OptArg[Number]                         = OptArg.Undefined,
  ax: OptArg[Number]                                    = OptArg.Undefined,
  ay: OptArg[Number]                                    = OptArg.Undefined,
  xref: OptArg[Annotations.CoordinateReference.X]       = OptArg.Undefined,
  x: OptArg[AxisPosition]                               = OptArg.Undefined,
  xanchor: OptArg[Anchor.X]                             = OptArg.Undefined,
  xshift: OptArg[Number]                                = OptArg.Undefined,
  yref: OptArg[Annotations.CoordinateReference.Y]       = OptArg.Undefined,
  y: OptArg[AxisPosition]                               = OptArg.Undefined,
  yanchor: OptArg[Anchor.Y]                             = OptArg.Undefined,
  yshift: OptArg[Number]                                = OptArg.Undefined,
  clicktoshow: OptArg[FalseOr[Annotations.ClickToShow]] = OptArg.Undefined,
  xclick: OptArg[AxisPosition]                          = OptArg.Undefined,
  yclick: OptArg[AxisPosition]                          = OptArg.Undefined,
  hovertext: OptArg[String]                             = OptArg.Undefined,
  hoverlabel: OptArg[HoverLabel]                        = OptArg.Undefined,
  captureevents: OptArg[Boolean]                        = OptArg.Undefined,
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

  sealed abstract class ClickToShow(val asString: String) extends JSEnum

  object ClickToShow {
    case object OnOff extends ClickToShow("onoff")
    case object OnOut extends ClickToShow("onout")
  }

  implicit val encoder: Encoder[Annotations] = Encoder[Annotations] { annotations =>
    Json.obj(
      "bgcolor" := annotations.bgcolor,
      "bordercolor" := annotations.bordercolor,
      "font" := annotations.font,
      "visible" := annotations.visible,
      "text" := annotations.text,
      "textangle" := annotations.textangle,
      "width" := annotations.width,
      "height" := annotations.height,
      "opacity" := annotations.opacity,
      "align" := annotations.align,
      "valign" := annotations.valign,
      "borderpad" := annotations.borderpad,
      "borderwidth" := annotations.borderwidth,
      "showarrow" := annotations.showarrow,
      "arrowcolor" := annotations.arrowcolor,
      "arrowhead" := annotations.arrowhead,
      "startarrowhead" := annotations.startarrowhead,
      "arrowside" := annotations.arrowside,
      "startarrowsize" := annotations.startarrowsize,
      "arrowwidth" := annotations.arrowwidth,
      "standoff" := annotations.standoff,
      "startstandoff" := annotations.startstandoff,
      "ax" := annotations.ax,
      "ay" := annotations.ay,
      "xref" := annotations.xref,
      "x" := annotations.x,
      "xanchor" := annotations.xanchor,
      "xshift" := annotations.xshift,
      "yref" := annotations.yref,
      "y" := annotations.y,
      "yanchor" := annotations.yanchor,
      "yshift" := annotations.yshift,
      "clicktoshow" := annotations.clicktoshow,
      "xclick" := annotations.xclick,
      "yclick" := annotations.yclick,
      "hovertext" := annotations.hovertext,
      "hoverlabel" := annotations.hoverlabel,
      "captureevents" := annotations.captureevents,
    )
  }

}

final case class RangeSelector(
  bgcolor: OptArg[Color]                     = OptArg.Undefined,
  bordercolor: OptArg[Color]                 = OptArg.Undefined,
  font: OptArg[Font]                         = OptArg.Undefined,
  buttons: OptArg[Seq[RangeSelector.Button]] = OptArg.Undefined,
  visible: OptArg[Boolean]                   = OptArg.Undefined,
  x: OptArg[Number]                          = OptArg.Undefined,
  xanchor: OptArg[Anchor.X]                  = OptArg.Undefined,
  y: OptArg[Number]                          = OptArg.Undefined,
  yanchor: OptArg[Anchor.Y]                  = OptArg.Undefined,
  activecolor: OptArg[Color]                 = OptArg.Undefined,
  borderwidth: OptArg[Number]                = OptArg.Undefined,
) extends Label

object RangeSelector {

  final case class Button(
    step: OptArg[Button.Step]         = OptArg.Undefined,
    stepmode: OptArg[Button.StepMode] = OptArg.Undefined,
    count: OptArg[Number]             = OptArg.Undefined,
    label: OptArg[String]             = OptArg.Undefined,
  )

  object Button {
    sealed abstract class Step(val asString: String) extends JSEnum

    object Step {
      case object Second extends Step("second")
      case object Minute extends Step("minute")
      case object Hour   extends Step("hour")
      case object Day    extends Step("day")
      case object Month  extends Step("month")
      case object Year   extends Step("year")
      case object All    extends Step("all")
    }

    sealed abstract class StepMode(val asString: String) extends JSEnum

    object StepMode {
      case object Backward extends StepMode("backward")
      case object ToDate   extends StepMode("todate")
    }

    implicit val encoder: Encoder[Button] = Encoder.forProduct4(
      "step",
      "stepmode",
      "count",
      "label",
    )(b =>
      (
        b.step,
        b.stepmode,
        b.count,
        b.label,
      ),
    )
  }

  implicit val encoder: Encoder[RangeSelector] = Encoder.forProduct11(
    "bgcolor",
    "bordercolor",
    "font",
    "buttons",
    "visible",
    "x",
    "xanchor",
    "y",
    "yanchor",
    "activecolor",
    "borderwidth",
  )(r =>
    (
      r.bgcolor,
      r.bordercolor,
      r.font,
      r.buttons,
      r.visible,
      r.x,
      r.xanchor,
      r.y,
      r.yanchor,
      r.activecolor,
      r.borderwidth,
    ),
  )

}
