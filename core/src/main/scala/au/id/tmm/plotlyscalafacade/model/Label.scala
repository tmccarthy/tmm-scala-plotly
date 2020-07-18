package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{FalseOr, JSEnum}
import io.circe.syntax._
import io.circe.{Encoder, Json}

sealed trait Label extends Product {
  def bgcolor: Option[Color]
  def bordercolor: Option[Color]
  def font: Option[Font]
}

object Label {

  def apply(
    bgcolor: Option[Color] = None,
    bordercolor: Option[Color] = None,
    font: Option[Font] = None,
  ): PlainLabel = PlainLabel(bgcolor, bordercolor, font)

  final case class PlainLabel(
    bgcolor: Option[Color] = None,
    bordercolor: Option[Color] = None,
    font: Option[Font] = None,
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
  bgcolor: Option[Color] = None,
  bordercolor: Option[Color] = None,
  font: Option[Font] = None,
  align: Option[Label.Alignment.Horizontal] = None,
  namelength: Option[Number] = None,
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
  bgcolor: Option[Color] = None,
  bordercolor: Option[Color] = None,
  font: Option[Font] = None,
  traceorder: Option[Legend.TraceOrder] = None,
  x: Option[Number] = None,
  y: Option[Number] = None,
  borderwidth: Option[Number] = None,
  orientation: Option[Legend.Orientation] = None,
  tracegroupgap: Option[Number] = None,
  xanchor: Option[Anchor.X] = None,
  yanchor: Option[Anchor.Y] = None,
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
  bgcolor: Option[Color] = None,
  bordercolor: Option[Color] = None,
  font: Option[Font] = None,
  visible: Option[Boolean] = None,
  text: Option[String] = None,
  textangle: Option[String] = None,
  width: Option[Number] = None,
  height: Option[Number] = None,
  opacity: Option[Number] = None,
  align: Option[Label.Alignment.Horizontal] = None,
  valign: Option[Label.Alignment.Vertical] = None,
  borderpad: Option[Number] = None,
  borderwidth: Option[Number] = None,
  showarrow: Option[Boolean] = None,
  arrowcolor: Option[String] = None,
  arrowhead: Option[Number] = None,
  startarrowhead: Option[Number] = None,
  arrowside: Option[Annotations.ArrowSide] = None,
  startarrowsize: Option[Number] = None,
  arrowwidth: Option[Number] = None,
  standoff: Option[Number] = None,
  startstandoff: Option[Number] = None,
  ax: Option[Number] = None,
  ay: Option[Number] = None,
  xref: Option[Annotations.CoordinateReference.X] = None,
  x: Option[AxisPosition] = None,
  xanchor: Option[Anchor.X] = None,
  xshift: Option[Number] = None,
  yref: Option[Annotations.CoordinateReference.Y] = None,
  y: Option[AxisPosition] = None,
  yanchor: Option[Anchor.Y] = None,
  yshift: Option[Number] = None,
  clicktoshow: Option[FalseOr[Annotations.ClickToShow]] = None,
  xclick: Option[AxisPosition] = None,
  yclick: Option[AxisPosition] = None,
  hovertext: Option[String] = None,
  hoverlabel: Option[HoverLabel] = None,
  captureevents: Option[Boolean] = None,
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
  bgcolor: Option[Color] = None,
  bordercolor: Option[Color] = None,
  font: Option[Font] = None,
  buttons: Option[Seq[RangeSelector.Button]] = None,
  visible: Option[Boolean] = None,
  x: Option[Number] = None,
  xanchor: Option[Anchor.X] = None,
  y: Option[Number] = None,
  yanchor: Option[Anchor.Y] = None,
  activecolor: Option[Color] = None,
  borderwidth: Option[Number] = None,
) extends Label

object RangeSelector {

  final case class Button(
    step: Option[Button.Step] = None,
    stepmode: Option[Button.StepMode] = None,
    count: Option[Number] = None,
    label: Option[String] = None,
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
