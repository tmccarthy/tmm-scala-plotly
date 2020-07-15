package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{FlagList, OneOrArrayOf}
import cats.instances.int.catsKernelStdOrderForInt
import cats.instances.string.catsKernelStdOrderForString
import cats.instances.tuple.catsKernelStdOrderForTuple2
import cats.kernel.Order
import io.circe.Encoder

// TODO it would probably be a good idea to break this up into the valid fields for each plot type. This information is
//      available in the JS docs.
final case class PlotData(
  `type`: PlotData.Type,
  x: DataSequence,
  y: DataSequence,
  z: DataSequence, // TODO this can be 3 dimensional
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
  textinfo: "label" | "label+text" | "label+value" | "label+percent" | "label+text+value" | "label+text+percent" | "label+value+percent" | "text" | "text+value" | "text+percent" | "text+value+percent" | "value" | "value+percent" | "percent" | "none",
  textposition: "top left" | "top center" | "top right" | "middle left" | "middle center" | "middle right" | "bottom left" | "bottom center" | "bottom right" | "inside" | "outside",
  textfont: Partial[Font],
  fill: "none" | "tozeroy" | "tozerox" | "tonexty" | "tonextx" | "toself" | "tonext",
  fillcolor: String,
  showlegend: Boolean,
  legendgroup: String,
  parents: Seq[String],
  name: String,
  stackgroup: String,
  connectgaps: Boolean,
  visible: Boolean | "legendonly",
  delta: Partial[Delta],
  gauge: Partial[Gauge],
  number: Partial[PlotNumber],
  transforms: Seq[DataTransform],
  orientation: "v" | "h",
  width: OneOrArrayOf[Number],
  boxmean: Boolean | "sd",
  opacity: Number,
  showscale: Boolean,
  colorscale: ColorScale,
  zsmooth: "fast" | "best" | false,
  ygap: Number,
  xgap: Number,
  transpose: Boolean,
  autobinx: Boolean,
  xbins: PlotData.XBins,
  value: Number,
  values: Seq[Datum],
  labels: Seq[Datum],
  direction: "clockwise" | "counterclockwise",
  hole: Number,
  rotation: Number,
  theta: Seq[Datum],
  r: Seq[Datum],
  customdata: Seq[Datum],
  domain: Partial[PlotData.Domain],
  title: Partial[DataTitle],
  branchvalues: "total" | "remainder",
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

  final case class XBins(
    start: Number | String,
    end: Number | String,
    size: Number | String,
  )

  final case class Domain(
    rows: Number,
    columns: Number,
    x: Seq[Number],
    y: Seq[Number],
  )

}
