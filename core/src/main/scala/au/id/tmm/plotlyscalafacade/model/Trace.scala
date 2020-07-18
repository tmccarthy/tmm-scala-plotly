package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities._
import cats.instances.int.catsKernelStdOrderForInt
import cats.instances.string.catsKernelStdOrderForString
import cats.instances.tuple.catsKernelStdOrderForTuple2
import cats.kernel.Order
import io.circe.syntax.{EncoderOps, KeyOps}
import io.circe.{Encoder, Json}

// TODO it would probably be a good idea to break this up into the valid fields for each plot type. This information is
//      available in the JS docs.
final case class Trace(
  `type`: Option[Trace.Type] = None,
  x: Option[DataArray] = None,
  y: Option[DataArray] = None,
  // TODO this can be 3 dimensional
  z: Option[DataArray] = None,
  error_x: Option[ErrorBar] = None,
  error_y: Option[ErrorBar] = None,
  xaxis: Option[String] = None,
  yaxis: Option[String] = None,
  text: Option[OneOrArrayOf[String]] = None,
  lat: Option[Seq[Number]] = None,
  lon: Option[Seq[Number]] = None,
  line: Option[ScatterLine] = None,
  marker: Option[PlotMarker] = None,
  mode: Option[Trace.ScatterMode] = None,
  histfunc: Option[Trace.HistogramFunction] = None,
  hoveron: Option[Trace.HoverOn] = None,
  hoverinfo: Option[Trace.HoverInfo] = None,
  hoverlabel: Option[HoverLabel] = None,
  hovertemplate: Option[OneOrArrayOf[String]] = None,
  hovertext: Option[OneOrArrayOf[String]] = None,
  textinfo: Option[Trace.TextInfo] = None,
  textposition: Option[Trace.TextPosition] = None,
  textfont: Option[Font] = None,
  fill: Option[Trace.Fill] = None,
  fillcolor: Option[String] = None,
  showlegend: Option[Boolean] = None,
  legendgroup: Option[String] = None,
  parents: Option[Seq[String]] = None,
  name: Option[String] = None,
  stackgroup: Option[String] = None,
  connectgaps: Option[Boolean] = None,
  visible: Option[BooleanOr[Trace.Visibility]] = None,
  delta: Option[Delta] = None,
  gauge: Option[Gauge] = None,
  number: Option[PlotNumber] = None,
  transforms: Option[Seq[Transform]] = None,
  orientation: Option[Trace.Orientation] = None,
  width: Option[OneOrArrayOf[Number]] = None,
  boxmean: Option[Trace.BoxMean] = None,
  opacity: Option[Number] = None,
  showscale: Option[Boolean] = None,
  colorscale: Option[ColorScale] = None,
  zsmooth: Option[FalseOr[Trace.ZSmooth]] = None,
  ygap: Option[Number] = None,
  xgap: Option[Number] = None,
  transpose: Option[Boolean] = None,
  autobinx: Option[Boolean] = None,
  xbins: Option[Trace.XBins] = None,
  value: Option[Number] = None,
  values: Option[Seq[Datum]] = None,
  labels: Option[Seq[Datum]] = None,
  direction: Option[Trace.Direction] = None,
  hole: Option[Number] = None,
  rotation: Option[Number] = None,
  theta: Option[Seq[Datum]] = None,
  r: Option[Seq[Datum]] = None,
  customdata: Option[Seq[Datum]] = None,
  domain: Option[Trace.Domain] = None,
  title: Option[DataTitle] = None,
  branchvalues: Option[Trace.BranchValues] = None,
)

object Trace {

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

  sealed trait ScatterMode

  object ScatterMode {
    case object None                                       extends ScatterMode
    final case class Of(flags: FlagList[ScatterMode.Flag]) extends ScatterMode

    sealed abstract class Flag(val asString: String) extends JSEnum

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

    implicit val encoder: Encoder[ScatterMode] = {
      case None      => "none".asJson
      case Of(flags) => flags.asJson
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
      case All       => "all".asJson
      case None      => "none".asJson
      case Skip      => "skip".asJson
      case Of(flags) => flags.asJson
    }

  }

  sealed trait TextInfo

  object TextInfo {

    case object None                           extends TextInfo
    final case class Of(flags: FlagList[Flag]) extends TextInfo

    sealed abstract class Flag(val asString: String) extends JSEnum

    object Flag {
      case object Label   extends Flag("label")
      case object Text    extends Flag("text")
      case object Initial extends Flag("initial")
      case object Delta   extends Flag("delta")
      case object Final   extends Flag("final")

      implicit val order: Order[Flag] = Order.by[Flag, Int] {
        case Label   => 0
        case Text    => 1
        case Initial => 2
        case Delta   => 3
        case Final   => 4
      }
    }

    implicit val encoder: Encoder[TextInfo] = {
      case None      => "none".asJson
      case Of(flags) => flags.asJson
    }
  }

  sealed abstract class TextPosition(val asString: String) extends JSEnum

  object TextPosition {
    case object TopLeft      extends TextPosition("top left")
    case object TopCenter    extends TextPosition("top center")
    case object TopRight     extends TextPosition("top right")
    case object MiddleLeft   extends TextPosition("middle left")
    case object MiddleCenter extends TextPosition("middle center")
    case object MiddleRight  extends TextPosition("middle right")
    case object BottomLeft   extends TextPosition("bottom left")
    case object BottomCenter extends TextPosition("bottom center")
    case object BottomRight  extends TextPosition("bottom right")
    case object Inside       extends TextPosition("inside")
    case object Outside      extends TextPosition("outside")
  }

  sealed abstract class Fill(val asString: String) extends JSEnum

  object Fill {
    case object None    extends Fill("none")
    case object ToZeroY extends Fill("tozeroy")
    case object ToZeroX extends Fill("tozerox")
    case object ToNextY extends Fill("tonexty")
    case object ToNextX extends Fill("tonextx")
    case object ToSelf  extends Fill("toself")
    case object ToNext  extends Fill("tonext")
  }

  sealed abstract class Visibility(val asString: String) extends JSEnum

  object Visibility {
    case object LegendOnly extends Visibility("legendOnly")
  }

  sealed abstract class Orientation(val asString: String) extends JSEnum

  object Orientation {
    case object Vertical   extends Orientation("v")
    case object Horizontal extends Orientation("h")
  }

  sealed trait BoxMean

  object BoxMean {
    case object True                  extends BoxMean
    case object WithStandardDeviation extends BoxMean
    case object False                 extends BoxMean

    implicit val encoder: Encoder[BoxMean] = {
      case True                  => true.asJson
      case WithStandardDeviation => "sd".asJson
      case False                 => false.asJson
    }
  }

  sealed abstract class ZSmooth(val asString: String) extends JSEnum

  object ZSmooth {
    case object Fast extends ZSmooth("fast")
    case object Best extends ZSmooth("best")
  }

  sealed abstract class Direction(val asString: String) extends JSEnum

  object Direction {
    case object Clockwise        extends Direction("clockwise")
    case object CounterClockwise extends Direction("counterclockwise")
  }

  sealed abstract class BranchValues(val asString: String) extends JSEnum

  object BranchValues {
    case object Total     extends BranchValues("total")
    case object Remainder extends BranchValues("remainder")
  }

  final case class XBins(
    start: AxisPosition,
    end: AxisPosition,
    size: AxisPosition,
  )

  object XBins {
    implicit val encoder: Encoder[XBins] = Encoder.forProduct3(
      "start",
      "end",
      "size",
    )(b =>
      (
        b.start,
        b.end,
        b.size,
      ),
    )
  }

  final case class Domain(
    rows: Number,
    columns: Number,
    x: Seq[Number],
    y: Seq[Number],
  )

  object Domain {
    implicit val encoder: Encoder[Domain] = Encoder.forProduct4(
      "rows",
      "columns",
      "x",
      "y",
    )(d =>
      (
        d.rows,
        d.columns,
        d.x,
        d.y,
      ),
    )
  }

  implicit val encoder: Encoder[Trace] = Encoder[Trace] { trace =>
    Json.obj(
      "type" := trace.`type`,
      "x" := trace.x,
      "y" := trace.y,
      "z" := trace.z,
      "error_x" := trace.error_x,
      "error_y" := trace.error_y,
      "xaxis" := trace.xaxis,
      "yaxis" := trace.yaxis,
      "text" := trace.text,
      "lat" := trace.lat,
      "lon" := trace.lon,
      "line" := trace.line,
      "marker" := trace.marker,
      "mode" := trace.mode,
      "histfunc" := trace.histfunc,
      "hoveron" := trace.hoveron,
      "hoverinfo" := trace.hoverinfo,
      "hoverlabel" := trace.hoverlabel,
      "hovertemplate" := trace.hovertemplate,
      "hovertext" := trace.hovertext,
      "textinfo" := trace.textinfo,
      "textposition" := trace.textposition,
      "textfont" := trace.textfont,
      "fill" := trace.fill,
      "fillcolor" := trace.fillcolor,
      "showlegend" := trace.showlegend,
      "legendgroup" := trace.legendgroup,
      "parents" := trace.parents,
      "name" := trace.name,
      "stackgroup" := trace.stackgroup,
      "connectgaps" := trace.connectgaps,
      "visible" := trace.visible,
      "delta" := trace.delta,
      "gauge" := trace.gauge,
      "number" := trace.number,
      "transforms" := trace.transforms,
      "orientation" := trace.orientation,
      "width" := trace.width,
      "boxmean" := trace.boxmean,
      "opacity" := trace.opacity,
      "showscale" := trace.showscale,
      "colorscale" := trace.colorscale,
      "zsmooth" := trace.zsmooth,
      "ygap" := trace.ygap,
      "xgap" := trace.xgap,
      "transpose" := trace.transpose,
      "autobinx" := trace.autobinx,
      "xbins" := trace.xbins,
      "value" := trace.value,
      "values" := trace.values,
      "labels" := trace.labels,
      "direction" := trace.direction,
      "hole" := trace.hole,
      "rotation" := trace.rotation,
      "theta" := trace.theta,
      "r" := trace.r,
      "customdata" := trace.customdata,
      "domain" := trace.domain,
      "title" := trace.title,
      "branchvalues" := trace.branchvalues,
    )
  }

}
