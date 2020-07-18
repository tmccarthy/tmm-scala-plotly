package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{Arg, FalseOr, JSEnum}
import io.circe.syntax._
import io.circe.{Encoder, Json}

sealed trait Label extends Product {
  def bgcolor: Arg[Color]
  def bordercolor: Arg[Color]
  def font: Arg[Font]
}

object Label {

  def apply(
    bgcolor: Arg[Color] = Arg.Undefined,
    bordercolor: Arg[Color] = Arg.Undefined,
    font: Arg[Font] = Arg.Undefined,
  ): PlainLabel = PlainLabel(bgcolor, bordercolor, font)

  final case class PlainLabel(
    bgcolor: Arg[Color] = Arg.Undefined,
    bordercolor: Arg[Color] = Arg.Undefined,
    font: Arg[Font] = Arg.Undefined,
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
  bgcolor: Arg[Color] = Arg.Undefined,
  bordercolor: Arg[Color] = Arg.Undefined,
  font: Arg[Font] = Arg.Undefined,
  align: Arg[Label.Alignment.Horizontal] = Arg.Undefined,
  namelength: Arg[Number] = Arg.Undefined,
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
  bgcolor: Arg[Color] = Arg.Undefined,
  bordercolor: Arg[Color] = Arg.Undefined,
  font: Arg[Font] = Arg.Undefined,
  traceorder: Arg[Legend.TraceOrder] = Arg.Undefined,
  x: Arg[Number] = Arg.Undefined,
  y: Arg[Number] = Arg.Undefined,
  borderwidth: Arg[Number] = Arg.Undefined,
  orientation: Arg[Legend.Orientation] = Arg.Undefined,
  tracegroupgap: Arg[Number] = Arg.Undefined,
  xanchor: Arg[Anchor.X] = Arg.Undefined,
  yanchor: Arg[Anchor.Y] = Arg.Undefined,
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
  bgcolor: Arg[Color] = Arg.Undefined,
  bordercolor: Arg[Color] = Arg.Undefined,
  font: Arg[Font] = Arg.Undefined,
  visible: Arg[Boolean] = Arg.Undefined,
  text: Arg[String] = Arg.Undefined,
  textangle: Arg[String] = Arg.Undefined,
  width: Arg[Number] = Arg.Undefined,
  height: Arg[Number] = Arg.Undefined,
  opacity: Arg[Number] = Arg.Undefined,
  align: Arg[Label.Alignment.Horizontal] = Arg.Undefined,
  valign: Arg[Label.Alignment.Vertical] = Arg.Undefined,
  borderpad: Arg[Number] = Arg.Undefined,
  borderwidth: Arg[Number] = Arg.Undefined,
  showarrow: Arg[Boolean] = Arg.Undefined,
  arrowcolor: Arg[String] = Arg.Undefined,
  arrowhead: Arg[Number] = Arg.Undefined,
  startarrowhead: Arg[Number] = Arg.Undefined,
  arrowside: Arg[Annotations.ArrowSide] = Arg.Undefined,
  startarrowsize: Arg[Number] = Arg.Undefined,
  arrowwidth: Arg[Number] = Arg.Undefined,
  standoff: Arg[Number] = Arg.Undefined,
  startstandoff: Arg[Number] = Arg.Undefined,
  ax: Arg[Number] = Arg.Undefined,
  ay: Arg[Number] = Arg.Undefined,
  xref: Arg[Annotations.CoordinateReference.X] = Arg.Undefined,
  x: Arg[AxisPosition] = Arg.Undefined,
  xanchor: Arg[Anchor.X] = Arg.Undefined,
  xshift: Arg[Number] = Arg.Undefined,
  yref: Arg[Annotations.CoordinateReference.Y] = Arg.Undefined,
  y: Arg[AxisPosition] = Arg.Undefined,
  yanchor: Arg[Anchor.Y] = Arg.Undefined,
  yshift: Arg[Number] = Arg.Undefined,
  clicktoshow: Arg[FalseOr[Annotations.ClickToShow]] = Arg.Undefined,
  xclick: Arg[AxisPosition] = Arg.Undefined,
  yclick: Arg[AxisPosition] = Arg.Undefined,
  hovertext: Arg[String] = Arg.Undefined,
  hoverlabel: Arg[HoverLabel] = Arg.Undefined,
  captureevents: Arg[Boolean] = Arg.Undefined,
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
  bgcolor: Arg[Color] = Arg.Undefined,
  bordercolor: Arg[Color] = Arg.Undefined,
  font: Arg[Font] = Arg.Undefined,
  buttons: Arg[Seq[RangeSelector.Button]] = Arg.Undefined,
  visible: Arg[Boolean] = Arg.Undefined,
  x: Arg[Number] = Arg.Undefined,
  xanchor: Arg[Anchor.X] = Arg.Undefined,
  y: Arg[Number] = Arg.Undefined,
  yanchor: Arg[Anchor.Y] = Arg.Undefined,
  activecolor: Arg[Color] = Arg.Undefined,
  borderwidth: Arg[Number] = Arg.Undefined,
) extends Label

object RangeSelector {

  final case class Button(
    step: Arg[Button.Step] = Arg.Undefined,
    stepmode: Arg[Button.StepMode] = Arg.Undefined,
    count: Arg[Number] = Arg.Undefined,
    label: Arg[String] = Arg.Undefined,
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
