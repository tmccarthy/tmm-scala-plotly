package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{BooleanOr, FlagList, JSEnum}
import cats.instances.int.catsKernelStdOrderForInt
import cats.kernel.Order
import io.circe.syntax.{EncoderOps, KeyOps}
import io.circe.{Encoder, Json}

sealed trait Axis {
  def visible: Option[Boolean]
  def color: Option[Color]
  def title: Option[Axis.Title]
  def titlefont: Option[Font]
  def `type`: Option[Axis.Type]
  def autorange: Option[BooleanOr[Axis.AutoRange]]
  def rangemode: Option[Axis.RangeMode]
  def range: Option[Axis.Range]
  def mirror: Option[BooleanOr[Axis.Mirror]]
  def showspikes: Option[Boolean]
  def spikecolor: Option[Color]
  def spikethickness: Option[Number]
  def categoryorder: Option[Axis.CategoryOrder]
  def categoryarray: Option[Seq[CategoryIndex]]
  def tickProperties: Option[TickProperties]
  def hoverformat: Option[String]
  def showline: Option[Boolean]
  def linecolor: Option[Color]
  def linewidth: Option[Number]
  def showgrid: Option[Boolean]
  def gridcolor: Option[Color]
  def gridwidth: Option[Number]
  def zeroline: Option[Boolean]
  def zerolinecolor: Option[Color]
  def zerolinewidth: Option[Number]
  def calendar: Option[Calendar]
}

object Axis {

  sealed abstract class Type(val asString: String) extends JSEnum

  object Type {
    case object AutomaticallyDetermined extends Type("-")
    case object Linear                  extends Type("linear")
    case object Log                     extends Type("log")
    case object Date                    extends Type("date")
    case object Category                extends Type("category")
  }

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

    implicit val encoder: Encoder[Range] = {
      case Numeric(range)     => range.asJson
      case Logarithmic(range) => range.asJson
      case Dates(range)       => range.asJson
      case Category(indices)  => indices.asJson
    }
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

  sealed abstract class Name(val asString: String) extends JSEnum

  object Name {
    case object x  extends Name("x")
    case object x2 extends Name("x2")
    case object x3 extends Name("x3")
    case object x4 extends Name("x4")
    case object x5 extends Name("x5")
    case object x6 extends Name("x6")
    case object x7 extends Name("x7")
    case object x8 extends Name("x8")
    case object x9 extends Name("x9")

    case object y  extends Name("y")
    case object y2 extends Name("y2")
    case object y3 extends Name("y3")
    case object y4 extends Name("y4")
    case object y5 extends Name("y5")
    case object y6 extends Name("y6")
    case object y7 extends Name("y7")
    case object y8 extends Name("y8")
    case object y9 extends Name("y9")
  }

  implicit val encoder: Encoder[Axis] = {
    case l: LayoutAxis => l.asJson
    case s: SceneAxis  => s.asJson
  }

}

final case class LayoutAxis(
  visible: Option[Boolean],
  color: Option[Color],
  title: Option[Axis.Title],
  titlefont: Option[Font],
  `type`: Option[Axis.Type],
  autorange: Option[BooleanOr[Axis.AutoRange]],
  rangemode: Option[Axis.RangeMode],
  range: Option[Axis.Range],
  tickProperties: Option[TickProperties],
  mirror: Option[BooleanOr[Axis.Mirror]],
  showspikes: Option[Boolean],
  spikecolor: Option[Color],
  spikethickness: Option[Number],
  categoryorder: Option[Axis.CategoryOrder],
  categoryarray: Option[Seq[CategoryIndex]],
  separatethousands: Option[Boolean],
  hoverformat: Option[String],
  showline: Option[Boolean],
  linecolor: Option[Color],
  linewidth: Option[Number],
  showgrid: Option[Boolean],
  gridcolor: Option[Color],
  gridwidth: Option[Number],
  zeroline: Option[Boolean],
  zerolinecolor: Option[Color],
  zerolinewidth: Option[Number],
  calendar: Option[Calendar],
  fixedrange: Option[Boolean],
  scaleanchor: Option[Axis.Name],
  scaleratio: Option[Number],
  constrain: Option[LayoutAxis.Constrain],
  constraintoward: Option[LayoutAxis.ConstrainToward],
  spikedash: Option[String],
  spikemode: Option[FlagList[LayoutAxis.SpikeMode]],
  anchor: Option[LayoutAxis.Anchor],
  side: Option[LayoutAxis.Side],
  overlaying: Option[LayoutAxis.Overlaying],
  layer: Option[LayoutAxis.Layer],
  domain: Option[Seq[Number]],
  position: Option[Number],
  rangeslider: Option[RangeSlider],
  rangeselector: Option[RangeSelector],
  automargin: Option[Boolean],
  autotick: Option[Boolean],
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
    case object Free                             extends Anchor("free")
    final case class ToAxis(axisName: Axis.Name) extends Anchor(axisName.asString)
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
    case object Free                             extends Overlaying("free")
    final case class ToAxis(axisName: Axis.Name) extends Overlaying(axisName.asString)
  }

  sealed abstract class Layer(val asString: String) extends JSEnum

  object Layer {
    case object AboveTraces extends Layer("above traces")
    case object BelowTraces extends Layer("below traces")
  }

  implicit val encoder: Encoder[LayoutAxis] =
    Encoder[LayoutAxis] { layoutAxis =>
      Json.obj(
        "visible" := layoutAxis.visible,
        "color" := layoutAxis.color,
        "title" := layoutAxis.title,
        "titlefont" := layoutAxis.titlefont,
        "type" := layoutAxis.`type`,
        "autorange" := layoutAxis.autorange,
        "rangemode" := layoutAxis.rangemode,
        "range" := layoutAxis.range,
        "mirror" := layoutAxis.mirror,
        "showspikes" := layoutAxis.showspikes,
        "spikecolor" := layoutAxis.spikecolor,
        "spikethickness" := layoutAxis.spikethickness,
        "categoryorder" := layoutAxis.categoryorder,
        "categoryarray" := layoutAxis.categoryarray,
        "separatethousands" := layoutAxis.separatethousands,
        "hoverformat" := layoutAxis.hoverformat,
        "showline" := layoutAxis.showline,
        "linecolor" := layoutAxis.linecolor,
        "linewidth" := layoutAxis.linewidth,
        "showgrid" := layoutAxis.showgrid,
        "gridcolor" := layoutAxis.gridcolor,
        "gridwidth" := layoutAxis.gridwidth,
        "zeroline" := layoutAxis.zeroline,
        "zerolinecolor" := layoutAxis.zerolinecolor,
        "zerolinewidth" := layoutAxis.zerolinewidth,
        "calendar" := layoutAxis.calendar,
        "fixedrange" := layoutAxis.fixedrange,
        "scaleanchor" := layoutAxis.scaleanchor,
        "scaleratio" := layoutAxis.scaleratio,
        "constrain" := layoutAxis.constrain,
        "constraintoward" := layoutAxis.constraintoward,
        "spikedash" := layoutAxis.spikedash,
        "spikemode" := layoutAxis.spikemode,
        "anchor" := layoutAxis.anchor,
        "side" := layoutAxis.side,
        "overlaying" := layoutAxis.overlaying,
        "layer" := layoutAxis.layer,
        "domain" := layoutAxis.domain,
        "position" := layoutAxis.position,
        "rangeslider" := layoutAxis.rangeslider,
        "rangeselector" := layoutAxis.rangeselector,
        "automargin" := layoutAxis.automargin,
        "autotick" := layoutAxis.autotick,
      )
    }.mergeFieldsFrom(_.tickProperties)

}

final case class SceneAxis(
  visible: Option[Boolean],
  color: Option[Color],
  title: Option[Axis.Title],
  titlefont: Option[Font],
  `type`: Option[Axis.Type],
  autorange: Option[BooleanOr[Axis.AutoRange]],
  rangemode: Option[Axis.RangeMode],
  range: Option[Axis.Range],
  tickProperties: Option[TickProperties],
  mirror: Option[BooleanOr[Axis.Mirror]],
  showspikes: Option[Boolean],
  spikecolor: Option[Color],
  spikethickness: Option[Number],
  categoryorder: Option[Axis.CategoryOrder],
  categoryarray: Option[Seq[CategoryIndex]],
  separatethousands: Option[Boolean],
  hoverformat: Option[String],
  showline: Option[Boolean],
  linecolor: Option[Color],
  linewidth: Option[Number],
  showgrid: Option[Boolean],
  gridcolor: Option[Color],
  gridwidth: Option[Number],
  zeroline: Option[Boolean],
  zerolinecolor: Option[Color],
  zerolinewidth: Option[Number],
  calendar: Option[Calendar],
  spikesides: Option[Boolean],
  showbackground: Option[Boolean],
  backgroundcolor: Option[Color],
  showaxeslabels: Option[Boolean],
) extends Axis

object SceneAxis {
  implicit val encoder: Encoder[SceneAxis] =
    Encoder[SceneAxis] { sceneAxis =>
      Json.obj(
        "visible" := sceneAxis.visible,
        "color" := sceneAxis.color,
        "title" := sceneAxis.title,
        "titlefont" := sceneAxis.titlefont,
        "type" := sceneAxis.`type`,
        "autorange" := sceneAxis.autorange,
        "rangemode" := sceneAxis.rangemode,
        "range" := sceneAxis.range,
        "mirror" := sceneAxis.mirror,
        "showspikes" := sceneAxis.showspikes,
        "spikecolor" := sceneAxis.spikecolor,
        "spikethickness" := sceneAxis.spikethickness,
        "categoryorder" := sceneAxis.categoryorder,
        "categoryarray" := sceneAxis.categoryarray,
        "separatethousands" := sceneAxis.separatethousands,
        "hoverformat" := sceneAxis.hoverformat,
        "showline" := sceneAxis.showline,
        "linecolor" := sceneAxis.linecolor,
        "linewidth" := sceneAxis.linewidth,
        "showgrid" := sceneAxis.showgrid,
        "gridcolor" := sceneAxis.gridcolor,
        "gridwidth" := sceneAxis.gridwidth,
        "zeroline" := sceneAxis.zeroline,
        "zerolinecolor" := sceneAxis.zerolinecolor,
        "zerolinewidth" := sceneAxis.zerolinewidth,
        "calendar" := sceneAxis.calendar,
        "spikesides" := sceneAxis.spikesides,
        "showbackground" := sceneAxis.showbackground,
        "backgroundcolor" := sceneAxis.backgroundcolor,
        "showaxeslabels" := sceneAxis.showaxeslabels,
      )
    }.mergeFieldsFrom(_.tickProperties)
}
