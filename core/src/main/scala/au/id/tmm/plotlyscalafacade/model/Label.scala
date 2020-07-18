package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{FalseOr, JSEnum}
import io.circe.Encoder

sealed trait Label extends Product {
  def bgcolor: Option[Color]
  def bordercolor: Option[Color]
  def font: Option[Font]
}

object Label {

  def apply(
    bgcolor: Option[Color],
    bordercolor: Option[Color],
    font: Option[Font],
  ): PlainLabel = PlainLabel(bgcolor, bordercolor, font)

  final case class PlainLabel(
    bgcolor: Option[Color],
    bordercolor: Option[Color],
    font: Option[Font],
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
  bgcolor: Option[Color],
  bordercolor: Option[Color],
  font: Option[Font],
  align: Option[Label.Alignment.Horizontal],
  namelength: Option[Number],
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
  bgcolor: Option[Color],
  bordercolor: Option[Color],
  font: Option[Font],
  traceorder: Option[Legend.TraceOrder],
  x: Option[Number],
  y: Option[Number],
  borderwidth: Option[Number],
  orientation: Option[Legend.Orientation],
  tracegroupgap: Option[Number],
  xanchor: Option[Anchor.X],
  yanchor: Option[Anchor.Y],
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
  bgcolor: Option[Color],
  bordercolor: Option[Color],
  font: Option[Font],
  visible: Option[Boolean],
  text: Option[String],
  textangle: Option[String],
  width: Option[Number],
  height: Option[Number],
  opacity: Option[Number],
  align: Option[Label.Alignment.Horizontal],
  valign: Option[Label.Alignment.Vertical],
  borderpad: Option[Number],
  borderwidth: Option[Number],
  showarrow: Option[Boolean],
  arrowcolor: Option[String],
  arrowhead: Option[Number],
  startarrowhead: Option[Number],
  arrowside: Option[Annotations.ArrowSide],
  startarrowsize: Option[Number],
  arrowwidth: Option[Number],
  standoff: Option[Number],
  startstandoff: Option[Number],
  ax: Option[Number],
  ay: Option[Number],
  xref: Option[Annotations.CoordinateReference.X],
  x: Option[AxisPosition],
  xanchor: Option[Anchor.X],
  xshift: Option[Number],
  yref: Option[Annotations.CoordinateReference.Y],
  y: Option[AxisPosition],
  yanchor: Option[Anchor.Y],
  yshift: Option[Number],
  clicktoshow: Option[FalseOr[Annotations.ClickToShow]],
  xclick: Option[AxisPosition],
  yclick: Option[AxisPosition],
  hovertext: Option[String],
  hoverlabel: Option[HoverLabel],
  captureevents: Option[Boolean],
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

}

final case class RangeSelector(
  bgcolor: Option[Color],
  bordercolor: Option[Color],
  font: Option[Font],
  buttons: Option[Seq[RangeSelector.Button]],
  visible: Option[Boolean],
  x: Option[Number],
  xanchor: Option[Anchor.X],
  y: Option[Number],
  yanchor: Option[Anchor.Y],
  activecolor: Option[Color],
  borderwidth: Option[Number],
) extends Label

object RangeSelector {

  final case class Button(
    step: Option[Button.Step],
    stepmode: Option[Button.StepMode],
    count: Option[Number],
    label: Option[String],
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
