package au.id.tmm.plotlyscalafacade.model

import java.time.Duration

import cats.data.NonEmptySet
import cats.instances.int.catsKernelStdOrderForInt
import cats.kernel.Order
import io.circe.Encoder

sealed trait Axis {
  def visible: Boolean
  def color: Color
  def title: Axis.Title
  def titlefont: Partial[Font]
  def `type`: AxisType
  def autorange: BooleanOr[Axis.AutoRange]
  def rangemode: Axis.RangeMode
  def range: Axis.Range
  def tickmode: Axis.TickMode
  def nticks: Number
  def tick0: AxisPosition
  def dtick: Axis.DTick
  def tickvals: Axis.TickVals
  def ticktext: Seq[String]
  def ticks: Axis.TicksDrawn
  def mirror: BooleanOr[Axis.Mirror]
  def ticklen: Number
  def tickwidth: Number
  def tickcolor: Color
  def showticklabels: Boolean
  def showspikes: Boolean
  def spikecolor: Color
  def spikethickness: Number
  def categoryorder: Axis.CategoryOrder
  def categoryarray: Seq[CategoryIndex]
  def tickfont: Partial[Font]
  def tickangle: Number
  def tickprefix: String
  def showtickprefix: Axis.ShowRule
  def ticksuffix: String
  def showticksuffix: Axis.ShowRule
  def showexponent: Axis.ShowRule
  def exponentformat: Axis.ExponentFormat
  def separatethousands: Boolean
  def tickformat: String
  def hoverformat: String
  def showline: Boolean
  def linecolor: Color
  def linewidth: Number
  def showgrid: Boolean
  def gridcolor: Color
  def gridwidth: Number
  def zeroline: Boolean
  def zerolinecolor: Color
  def zerolinewidth: Number
  def calendar: Calendar
}

object Axis {

  def apply(
    visible: Boolean,
    color: Color,
    title: Axis.Title,
    titlefont: Partial[Font],
    `type`: AxisType,
    autorange: BooleanOr[Axis.AutoRange],
    rangemode: Axis.RangeMode,
    range: Axis.Range,
    tickmode: Axis.TickMode,
    nticks: Number,
    tick0: AxisPosition,
    dtick: Axis.DTick,
    tickvals: Axis.TickVals,
    ticktext: Seq[String],
    ticks: Axis.TicksDrawn,
    mirror: BooleanOr[Axis.Mirror],
    ticklen: Number,
    tickwidth: Number,
    tickcolor: Color,
    showticklabels: Boolean,
    showspikes: Boolean,
    spikecolor: Color,
    spikethickness: Number,
    categoryorder: Axis.CategoryOrder,
    categoryarray: Seq[CategoryIndex],
    tickfont: Partial[Font],
    tickangle: Number,
    tickprefix: String,
    showtickprefix: Axis.ShowRule,
    ticksuffix: String,
    showticksuffix: Axis.ShowRule,
    showexponent: Axis.ShowRule,
    exponentformat: Axis.ExponentFormat,
    separatethousands: Boolean,
    tickformat: String,
    hoverformat: String,
    showline: Boolean,
    linecolor: Color,
    linewidth: Number,
    showgrid: Boolean,
    gridcolor: Color,
    gridwidth: Number,
    zeroline: Boolean,
    zerolinecolor: Color,
    zerolinewidth: Number,
    calendar: Calendar,
  ): PlainAxis =
    PlainAxis(
      visible,
      color,
      title,
      titlefont,
      `type`,
      autorange,
      rangemode,
      range,
      tickmode,
      nticks,
      tick0,
      dtick,
      tickvals,
      ticktext,
      ticks,
      mirror,
      ticklen,
      tickwidth,
      tickcolor,
      showticklabels,
      showspikes,
      spikecolor,
      spikethickness,
      categoryorder,
      categoryarray,
      tickfont,
      tickangle,
      tickprefix,
      showtickprefix,
      ticksuffix,
      showticksuffix,
      showexponent,
      exponentformat,
      separatethousands,
      tickformat,
      hoverformat,
      showline,
      linecolor,
      linewidth,
      showgrid,
      gridcolor,
      gridwidth,
      zeroline,
      zerolinecolor,
      zerolinewidth,
      calendar,
    )

  final case class PlainAxis(
    visible: Boolean,
    color: Color,
    title: Axis.Title,
    titlefont: Partial[Font],
    `type`: AxisType,
    autorange: BooleanOr[Axis.AutoRange],
    rangemode: Axis.RangeMode,
    range: Axis.Range,
    tickmode: Axis.TickMode,
    nticks: Number,
    tick0: AxisPosition,
    dtick: Axis.DTick,
    tickvals: Axis.TickVals,
    ticktext: Seq[String],
    ticks: Axis.TicksDrawn,
    mirror: BooleanOr[Axis.Mirror],
    ticklen: Number,
    tickwidth: Number,
    tickcolor: Color,
    showticklabels: Boolean,
    showspikes: Boolean,
    spikecolor: Color,
    spikethickness: Number,
    categoryorder: Axis.CategoryOrder,
    categoryarray: Seq[CategoryIndex],
    tickfont: Partial[Font],
    tickangle: Number,
    tickprefix: String,
    showtickprefix: Axis.ShowRule,
    ticksuffix: String,
    showticksuffix: Axis.ShowRule,
    showexponent: Axis.ShowRule,
    exponentformat: Axis.ExponentFormat,
    separatethousands: Boolean,
    tickformat: String,
    hoverformat: String,
    showline: Boolean,
    linecolor: Color,
    linewidth: Number,
    showgrid: Boolean,
    gridcolor: Color,
    gridwidth: Number,
    zeroline: Boolean,
    zerolinecolor: Color,
    zerolinewidth: Number,
    calendar: Calendar,
  )

  sealed trait Title

  object Title {
    final case class OfString(title: String)           extends Title
    final case class OfDataTitle(dataTitle: DataTitle) extends Title

    implicit val encoder: Encoder[Title] = {
      case OfString(title)        => Encoder[String].apply(title)
      case OfDataTitle(dataTitle) => Encoder[DataTitle].apply(dataTitle)
    }
  }

  sealed abstract class AutoRange(val asString: String) extends JSEnum

  object AutoRange {
    case object Reversed extends AutoRange("reversed")
  }

  sealed abstract class RangeMode(val asString: String) extends JSEnum

  object RangeMode {
    case object Normal      extends RangeMode("normal")
    case object ToZero      extends RangeMode("tozero")
    case object NonNegative extends RangeMode("nonnegative")
  }

  sealed trait Range

  object Range {
    final case class Numeric(range: NumberArray)           extends Range
    final case class Logarithmic(range: NumberArray)       extends Range
    final case class Dates(range: Seq[Date])               extends Range
    final case class Category(indices: Seq[CategoryIndex]) extends Range
  }

  sealed abstract class TickMode(val asString: String) extends JSEnum

  object TickMode {
    case object Auto   extends TickMode("auto")
    case object Linear extends TickMode("linear")
    case object Array  extends TickMode("array")
  }

  sealed trait DTick

  object DTick {

    final case class Numeric(n: Number)               extends DTick
    final case class CategoryOffset(n: CategoryIndex) extends DTick

    sealed trait Logarithmic extends DTick

    object Logarithmic {
      final case class Log(n: Number)                  extends Logarithmic
      final case class Linear(n: Number)               extends Logarithmic
      final case object PowersOf10PlusAllSmallDigits   extends Logarithmic
      final case object PowersOf10PlusSmallDigits2And5 extends Logarithmic
    }

    sealed trait Date extends DTick

    object Date {
      final case class OfDuration(duration: Duration) extends Date
      final case class OfMonths(numMonths: Int)       extends Date
    }

    implicit val encoder: Encoder[DTick] = {
      case Numeric(n)                                 => Encoder[Number].apply(n)
      case CategoryOffset(n)                          => Encoder[Number].apply(n)
      case Logarithmic.Log(n)                         => Encoder[Number].apply(n)
      case Logarithmic.Linear(n)                      => Encoder[String].apply(s"L$n")
      case Logarithmic.PowersOf10PlusAllSmallDigits   => Encoder[String].apply("D1")
      case Logarithmic.PowersOf10PlusSmallDigits2And5 => Encoder[String].apply("D2")
      case Date.OfDuration(duration)                  => Encoder[Number].apply(duration.toMillis.toDouble)
      case Date.OfMonths(numMonths)                   => Encoder[String].apply(s"M$numMonths")
    }

  }

  sealed trait TickVals

  object TickVals {
    // TODO this is really a "Sequence" type
    final case class Numeric(ticks: NumberArray)                         extends TickVals
    final case class Dates(ticks: Seq[Date])                             extends TickVals
    final case class Categories(tickCategoryIndices: Seq[CategoryIndex]) extends TickVals
  }

  sealed abstract class TicksDrawn(val asString: String) extends JSEnum

  object TicksDrawn {
    case object NotDrawn extends TicksDrawn("")
    case object Outside  extends TicksDrawn("outside")
    case object Inside   extends TicksDrawn("inside")
  }

  sealed abstract class Mirror(val asString: String) extends JSEnum

  object Mirror {
    case object Ticks    extends Mirror("ticks")
    case object All      extends Mirror("all")
    case object AllTicks extends Mirror("allticks")
  }

  sealed abstract class CategoryOrder(val asString: String) extends JSEnum

  object CategoryOrder {
    case object Trace              extends CategoryOrder("trace")
    case object CategoryAscending  extends CategoryOrder("category ascending")
    case object CategoryDescending extends CategoryOrder("category descending")
    case object Array              extends CategoryOrder("array")
    case object TotalAscending     extends CategoryOrder("total ascending")
    case object TotalDescending    extends CategoryOrder("total descending")
    case object MinAscending       extends CategoryOrder("min ascending")
    case object MinDescending      extends CategoryOrder("min descending")
    case object MaxAscending       extends CategoryOrder("max ascending")
    case object MaxDescending      extends CategoryOrder("max descending")
    case object SumAscending       extends CategoryOrder("sum ascending")
    case object SumDescending      extends CategoryOrder("sum descending")
    case object MeanAscending      extends CategoryOrder("mean ascending")
    case object MeanDescending     extends CategoryOrder("mean descending")
    case object MedianAscending    extends CategoryOrder("median ascending")
    case object MedianDescending   extends CategoryOrder("median descending")
  }

  sealed abstract class ShowRule(val asString: String) extends JSEnum

  object ShowRule {
    case object All   extends ShowRule("all")
    case object First extends ShowRule("first")
    case object Last  extends ShowRule("last")
    case object None  extends ShowRule("none")
  }

  sealed abstract class ExponentFormat(val asString: String) extends JSEnum

  object ExponentFormat {
    case object None     extends ExponentFormat("none")
    case object SmallE   extends ExponentFormat("e")
    case object CapitalE extends ExponentFormat("E")
    case object Power    extends ExponentFormat("power")
    case object SI       extends ExponentFormat("SI")
    case object B        extends ExponentFormat("B")
  }

}

final case class LayoutAxis(
  visible: Boolean,
  color: Color,
  title: Axis.Title,
  titlefont: Partial[Font],
  `type`: AxisType,
  autorange: BooleanOr[Axis.AutoRange],
  rangemode: Axis.RangeMode,
  range: Axis.Range,
  tickmode: Axis.TickMode,
  nticks: Number,
  tick0: AxisPosition,
  dtick: Axis.DTick,
  tickvals: Axis.TickVals,
  ticktext: Seq[String],
  ticks: Axis.TicksDrawn,
  mirror: BooleanOr[Axis.Mirror],
  ticklen: Number,
  tickwidth: Number,
  tickcolor: Color,
  showticklabels: Boolean,
  showspikes: Boolean,
  spikecolor: Color,
  spikethickness: Number,
  categoryorder: Axis.CategoryOrder,
  categoryarray: Seq[CategoryIndex],
  tickfont: Partial[Font],
  tickangle: Number,
  tickprefix: String,
  showtickprefix: Axis.ShowRule,
  ticksuffix: String,
  showticksuffix: Axis.ShowRule,
  showexponent: Axis.ShowRule,
  exponentformat: Axis.ExponentFormat,
  separatethousands: Boolean,
  tickformat: String,
  hoverformat: String,
  showline: Boolean,
  linecolor: Color,
  linewidth: Number,
  showgrid: Boolean,
  gridcolor: Color,
  gridwidth: Number,
  zeroline: Boolean,
  zerolinecolor: Color,
  zerolinewidth: Number,
  calendar: Calendar,
  fixedrange: Boolean,
  scaleanchor: AxisName,
  scaleratio: Number,
  constrain: LayoutAxis.Constrain,
  constraintoward: LayoutAxis.ConstrainToward,
  spikedash: String,
  spikemode: LayoutAxis.SpikeMode,
  anchor: LayoutAxis.Anchor,
  side: LayoutAxis.Side,
  overlaying: LayoutAxis.Overlaying,
  layer: LayoutAxis.Layer,
  domain: Seq[Number],
  position: Number,
  rangeslider: Partial[RangeSlider],
  rangeselector: Partial[RangeSelector],
  automargin: Boolean,
  autotick: Boolean,
) extends Axis

object LayoutAxis {

  sealed abstract class Constrain(val asString: String) extends JSEnum

  object Constrain {
    case object Range  extends Constrain("range")
    case object Domain extends Constrain("domain")
  }

  sealed abstract class ConstrainToward(val asString: String) extends JSEnum

  object ConstrainToward {
    case object Left   extends ConstrainToward("left")
    case object Center extends ConstrainToward("center")
    case object Right  extends ConstrainToward("right")
    case object Top    extends ConstrainToward("top")
    case object Middle extends ConstrainToward("middle")
    case object Bottom extends ConstrainToward("bottom")
  }

  final case class SpikeMode(modes: NonEmptySet[SpikeMode.Mode])

  object SpikeMode {

    // TODO confirm this isn't recursive
    def apply(head: Mode, tail: Mode*): SpikeMode = SpikeMode(NonEmptySet.of[Mode](head, tail: _*): NonEmptySet[Mode])

    sealed abstract class Mode(val asString: String) extends JSEnum

    object Mode {
      case object ToAxis extends Mode("toaxis")
      case object Across extends Mode("across")
      case object Marker extends Mode("marker")

      implicit val order: Order[Mode] = Order.by[Mode, Int] {
        case ToAxis => 0
        case Across => 1
        case Marker => 2
      }
    }

    implicit val encoder: Encoder[SpikeMode] = spikeMode =>
      Encoder[String].apply(spikeMode.modes.reduceLeftTo(_.asString)((l, r) => s"$l+$r"))
  }

  sealed abstract class Anchor(val asString: String) extends JSEnum

  object Anchor {
    case object Free                            extends Anchor("free")
    final case class ToAxis(axisName: AxisName) extends Anchor(axisName.asString)
  }

  sealed abstract class Side(val asString: String) extends JSEnum

  object Side {
    case object Top              extends Side("top")
    case object Bottom           extends Side("bottom")
    case object Left             extends Side("left")
    case object Right            extends Side("right")
    case object Clockwise        extends Side("clockwise")
    case object Counterclockwise extends Side("counterclockwise")
  }

  sealed abstract class Overlaying(val asString: String) extends JSEnum

  object Overlaying {
    case object Free                            extends Overlaying("free")
    final case class ToAxis(axisName: AxisName) extends Overlaying(axisName.asString)
  }

  sealed abstract class Layer(val asString: String)

  object Layer {
    case object AboveTraces extends Layer("above traces")
    case object BelowTraces extends Layer("below traces")
  }

}

final case class SceneAxis(
  visible: Boolean,
  color: Color,
  title: Axis.Title,
  titlefont: Partial[Font],
  `type`: AxisType,
  autorange: BooleanOr[Axis.AutoRange],
  rangemode: Axis.RangeMode,
  range: Axis.Range,
  tickmode: Axis.TickMode,
  nticks: Number,
  tick0: AxisPosition,
  dtick: Axis.DTick,
  tickvals: Axis.TickVals,
  ticktext: Seq[String],
  ticks: Axis.TicksDrawn,
  mirror: BooleanOr[Axis.Mirror],
  ticklen: Number,
  tickwidth: Number,
  tickcolor: Color,
  showticklabels: Boolean,
  showspikes: Boolean,
  spikecolor: Color,
  spikethickness: Number,
  categoryorder: Axis.CategoryOrder,
  categoryarray: Seq[CategoryIndex],
  tickfont: Partial[Font],
  tickangle: Number,
  tickprefix: String,
  showtickprefix: Axis.ShowRule,
  ticksuffix: String,
  showticksuffix: Axis.ShowRule,
  showexponent: Axis.ShowRule,
  exponentformat: Axis.ExponentFormat,
  separatethousands: Boolean,
  tickformat: String,
  hoverformat: String,
  showline: Boolean,
  linecolor: Color,
  linewidth: Number,
  showgrid: Boolean,
  gridcolor: Color,
  gridwidth: Number,
  zeroline: Boolean,
  zerolinecolor: Color,
  zerolinewidth: Number,
  calendar: Calendar,
  spikesides: Boolean,
  showbackground: Boolean,
  backgroundcolor: Color,
  showaxeslabels: Boolean,
) extends Axis
