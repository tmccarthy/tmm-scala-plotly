package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{BooleanOr, FlagList, JSEnum}
import cats.Show
import cats.data.NonEmptyList
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
  def mirror: BooleanOr[Axis.Mirror]
  def showspikes: Boolean
  def spikecolor: Color
  def spikethickness: Number
  def categoryorder: Axis.CategoryOrder
  def categoryarray: Seq[CategoryIndex]
  def tickProperties: TickProperties
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
    tickProperties: TickProperties,
    mirror: BooleanOr[Axis.Mirror],
    showspikes: Boolean,
    spikecolor: Color,
    spikethickness: Number,
    categoryorder: Axis.CategoryOrder,
    categoryarray: Seq[CategoryIndex],
    separatethousands: Boolean,
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
      tickProperties,
      mirror,
      showspikes,
      spikecolor,
      spikethickness,
      categoryorder,
      categoryarray,
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
    tickProperties: TickProperties,
    mirror: BooleanOr[Axis.Mirror],
    showspikes: Boolean,
    spikecolor: Color,
    spikethickness: Number,
    categoryorder: Axis.CategoryOrder,
    categoryarray: Seq[CategoryIndex],
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
  ) extends Axis

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
  tickProperties: TickProperties,
  mirror: BooleanOr[Axis.Mirror],
  showspikes: Boolean,
  spikecolor: Color,
  spikethickness: Number,
  categoryorder: Axis.CategoryOrder,
  categoryarray: Seq[CategoryIndex],
  separatethousands: Boolean,
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
  spikemode: FlagList[LayoutAxis.SpikeMode],
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

  sealed abstract class SpikeMode(val asString: String) extends JSEnum

  object SpikeMode {

    case object ToAxis extends SpikeMode("toaxis")
    case object Across extends SpikeMode("across")
    case object Marker extends SpikeMode("marker")

    implicit val order: Order[SpikeMode] = Order.by[SpikeMode, Int] {
      case ToAxis => 0
      case Across => 1
      case Marker => 2
    }
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
  tickProperties: TickProperties,
  mirror: BooleanOr[Axis.Mirror],
  showspikes: Boolean,
  spikecolor: Color,
  spikethickness: Number,
  categoryorder: Axis.CategoryOrder,
  categoryarray: Seq[CategoryIndex],
  separatethousands: Boolean,
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
