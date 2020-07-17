package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{BooleanOr, FalseOr, FlagList, OneOrArrayOf}
import cats.instances.int.catsKernelStdOrderForInt
import cats.instances.string.catsKernelStdOrderForString
import cats.instances.tuple.catsKernelStdOrderForTuple2
import cats.kernel.Order
import io.circe.Encoder

// TODO it would probably be a good idea to break this up into the valid fields for each plot type. This information is
//      available in the JS docs.
// TODO this should probably be called Trace
final case class PlotData(
  `type`: PlotData.Type,
  x: DataArray,
  y: DataArray,
  z: DataArray, // TODO this can be 3 dimensional
  xy: Option[PlotData.FastXY],
  error_x: ErrorBar,
  error_y: ErrorBar,
  xaxis: String,
  yaxis: String,
  text: OneOrArrayOf[String],
  lat: Seq[Number],
  lon: Seq[Number],
  line: Partial[ScatterLine],
  marker: Partial[PlotMarker],
  mode: PlotData.ScatterMode,
  histfunc: PlotData.HistogramFunction,
  hoveron: PlotData.HoverOn,
  hoverinfo: PlotData.HoverInfo,
  hoverlabel: Partial[HoverLabel],
  hovertemplate: OneOrArrayOf[String],
  hovertext: OneOrArrayOf[String],
  textinfo: PlotData.TextInfo,
  textposition: PlotData.TextPosition,
  textfont: Partial[Font],
  fill: PlotData.Fill,
  fillcolor: String,
  showlegend: Boolean,
  legendgroup: String,
  parents: Seq[String],
  name: String,
  stackgroup: String,
  connectgaps: Boolean,
  visible: BooleanOr[PlotData.Visibility],
  delta: Partial[Delta],
  gauge: Partial[Gauge],
  number: Partial[PlotNumber],
  transforms: Seq[Partial[Transform]],
  orientation: PlotData.Orientation,
  width: OneOrArrayOf[Number],
  boxmean: PlotData.BoxMean,
  opacity: Number,
  showscale: Boolean,
  colorscale: ColorScale,
  zsmooth: FalseOr[PlotData.ZSmooth],
  ygap: Number,
  xgap: Number,
  transpose: Boolean,
  autobinx: Boolean,
  xbins: PlotData.XBins,
  value: Number,
  values: Seq[Datum],
  labels: Seq[Datum],
  direction: PlotData.Direction,
  hole: Number,
  rotation: Number,
  theta: Seq[Datum],
  r: Seq[Datum],
  customdata: Seq[Datum],
  domain: Partial[PlotData.Domain],
  title: Partial[DataTitle],
  branchvalues: PlotData.BranchValues,
)

object PlotData {

  import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum

  sealed abstract class Type(val asString: String) extends JSEnum

  object Type {
    case object Bar            extends Type("bar")
    case object Box            extends Type("box")
    case object Candlestick    extends Type("candlestick")
    case object Choropleth     extends Type("choropleth")
    case object Contour        extends Type("contour")
    case object Heatmap        extends Type("heatmap")
    case object Histogram      extends Type("histogram")
    case object Indicator      extends Type("indicator")
    case object Mesh3d         extends Type("mesh3d")
    case object Ohlc           extends Type("ohlc")
    case object ParCoords      extends Type("parcoords")
    case object Pie            extends Type("pie")
    case object PointCloud     extends Type("pointcloud")
    case object Scatter        extends Type("scatter")
    case object Scatter3d      extends Type("scatter3d")
    case object ScatterGeo     extends Type("scattergeo")
    case object ScatterGl      extends Type("scattergl")
    case object ScatterPolar   extends Type("scatterpolar")
    case object ScatterTernary extends Type("scatterternary")
    case object Sunburst       extends Type("sunburst")
    case object Surface        extends Type("surface")
    case object Treemap        extends Type("treemap")
    case object Waterfall      extends Type("waterfall")
    case object Funnel         extends Type("funnel")
    case object FunnelArea     extends Type("funnelarea")
    case object ScatterMapbox  extends Type("scattermapbox")
  }

  /**
    * Not supported
    */
  sealed trait FastXY

  sealed trait ScatterMode

  object ScatterMode {
    case object None extends ScatterMode
    final case class Of(flags: FlagList[ScatterMode.Flag])

    sealed abstract class Flag(val asString: String)

    object Flag {
      case object Line                    extends Flag("lines")
      case object Markers                 extends Flag("markers")
      final case class Text(text: String) extends Flag(text)

      implicit val order: Order[Flag] = Order.by[Flag, (Int, String)] {
        case Line       => (0, "")
        case Markers    => (1, "")
        case Text(text) => (2, text)
      }
    }
  }

  sealed abstract class HistogramFunction(val asString: String) extends JSEnum

  object HistogramFunction {
    case object Count extends HistogramFunction("count")
    case object Sum   extends HistogramFunction("sum")
    case object Avg   extends HistogramFunction("avg")
    case object Min   extends HistogramFunction("min")
    case object Max   extends HistogramFunction("max")
  }

  sealed abstract class HoverOn(val asString: String) extends JSEnum

  object HoverOn {
    case object Points extends HoverOn("points")
    case object Fills  extends HoverOn("fills")
  }

  sealed trait HoverInfo

  object HoverInfo {

    case object All                                      extends HoverInfo
    case object None                                     extends HoverInfo
    case object Skip                                     extends HoverInfo
    final case class Of(flags: FlagList[HoverInfo.Flag]) extends HoverInfo

    sealed abstract class Flag(val asString: String) extends JSEnum

    object Flag {
      case object X    extends Flag("x")
      case object Y    extends Flag("y")
      case object Z    extends Flag("z")
      case object Text extends Flag("text")
      case object Name extends Flag("name")

      implicit val order: Order[Flag] = Order.by[Flag, Int] {
        case X    => 0
        case Y    => 1
        case Z    => 2
        case Text => 3
        case Name => 4
      }
    }

    implicit val encoder: Encoder[HoverInfo] = {
      case All       => Encoder[String].apply("all")
      case None      => Encoder[String].apply("none")
      case Skip      => Encoder[String].apply("skip")
      case Of(flags) => Encoder[FlagList[HoverInfo.Flag]].apply(flags)
    }

  }

  sealed trait TextInfo

  object TextInfo {

    case object None extends TextInfo
    final case class Of(flags: FlagList[Flag]) extends TextInfo

    sealed abstract class Flag(val asString: String) extends JSEnum

    object Flag {
      case object Label extends Flag("label")
      case object Text extends Flag("text")
      case object Initial extends Flag("initial")
      case object Delta extends Flag("delta")
      case object Final extends Flag("final")

      implicit val order: Order[Flag] = Order.by[Flag, Int] {
        case Label => 0
        case Text => 1
        case Initial => 2
        case Delta => 3
        case Final => 4
      }
    }

    implicit val encoder: Encoder[TextInfo] = {
      case None => Encoder[String].apply("none")
      case Of(flags) => Encoder[FlagList[Flag]].apply(flags)
    }
  }

  sealed abstract class TextPosition(val asString: String) extends JSEnum

  object TextPosition {
    case object TopLeft extends TextPosition("top left")
    case object TopCenter extends TextPosition("top center")
    case object TopRight extends TextPosition("top right")
    case object MiddleLeft extends TextPosition("middle left")
    case object MiddleCenter extends TextPosition("middle center")
    case object MiddleRight extends TextPosition("middle right")
    case object BottomLeft extends TextPosition("bottom left")
    case object BottomCenter extends TextPosition("bottom center")
    case object BottomRight extends TextPosition("bottom right")
    case object Inside extends TextPosition("inside")
    case object Outside extends TextPosition("outside")
  }

  sealed abstract class Fill(val asString: String) extends JSEnum

  object Fill {
    case object None extends Fill("none")
    case object ToZeroY extends Fill("tozeroy")
    case object ToZeroX extends Fill("tozerox")
    case object ToNextY extends Fill("tonexty")
    case object ToNextX extends Fill("tonextx")
    case object ToSelf extends Fill("toself")
    case object ToNext extends Fill("tonext")
  }

  sealed abstract class Visibility(val asString: String) extends JSEnum

  object Visibility {
    case object LegendOnly extends Visibility("legendOnly")
  }

  sealed abstract class Orientation(val asString: String) extends JSEnum

  object Orientation {
    case object Vertical extends Orientation("v")
    case object Horizontal extends Orientation("h")
  }

  sealed trait BoxMean

  object BoxMean {
    case object True extends BoxMean
    case object WithStandardDeviation extends BoxMean
    case object False extends BoxMean

    implicit val encoder: Encoder[BoxMean] = {
      case True => Encoder[Boolean].apply(true)
      case WithStandardDeviation => Encoder[String].apply("sd")
      case False => Encoder[Boolean].apply(false)
    }
  }

  sealed abstract class ZSmooth(val asString: String) extends JSEnum

  object ZSmooth {
    case object Fast extends ZSmooth("fast")
    case object Best extends ZSmooth("best")
  }

  sealed abstract class Direction(val asString: String) extends JSEnum

  object Direction {
    case object Clockwise extends Direction("clockwise")
    case object CounterClockwise extends Direction("counterclockwise")
  }

  sealed abstract class BranchValues(val asString: String) extends JSEnum

  object BranchValues {
    case object Total extends BranchValues("total")
    case object Remainder extends BranchValues("remainder")
  }

  final case class XBins(
    start: AxisPosition,
    end: AxisPosition,
    size: AxisPosition,
  )

  final case class Domain(
    rows: Number,
    columns: Number,
    x: Seq[Number],
    y: Seq[Number],
  )

}
