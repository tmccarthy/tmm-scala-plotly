package au.id.tmm.plotly.model

import au.id.tmm.plotly.model.Layout.Title.ContainerReference
import au.id.tmm.plotly.model.utilities.{OptArg, BlankOr, FalseOr, JSEnum}
import io.circe.syntax._
import io.circe.{Encoder, Json}

final case class Layout(
  colorway: OptArg[Seq[Color]] = OptArg.Undefined,
  title: OptArg[Layout.Title] = OptArg.Undefined,
  titlefont: OptArg[Font] = OptArg.Undefined,
  autosize: OptArg[Boolean] = OptArg.Undefined,
  showlegend: OptArg[Boolean] = OptArg.Undefined,
  paper_bgcolor: OptArg[Color] = OptArg.Undefined,
  plot_bgcolor: OptArg[Color] = OptArg.Undefined,
  separators: OptArg[String] = OptArg.Undefined,
  hidesources: OptArg[Boolean] = OptArg.Undefined,
  xaxis: OptArg[LayoutAxis] = OptArg.Undefined,
  xaxis2: OptArg[LayoutAxis] = OptArg.Undefined,
  xaxis3: OptArg[LayoutAxis] = OptArg.Undefined,
  xaxis4: OptArg[LayoutAxis] = OptArg.Undefined,
  xaxis5: OptArg[LayoutAxis] = OptArg.Undefined,
  xaxis6: OptArg[LayoutAxis] = OptArg.Undefined,
  xaxis7: OptArg[LayoutAxis] = OptArg.Undefined,
  xaxis8: OptArg[LayoutAxis] = OptArg.Undefined,
  xaxis9: OptArg[LayoutAxis] = OptArg.Undefined,
  yaxis: OptArg[LayoutAxis] = OptArg.Undefined,
  yaxis2: OptArg[LayoutAxis] = OptArg.Undefined,
  yaxis3: OptArg[LayoutAxis] = OptArg.Undefined,
  yaxis4: OptArg[LayoutAxis] = OptArg.Undefined,
  yaxis5: OptArg[LayoutAxis] = OptArg.Undefined,
  yaxis6: OptArg[LayoutAxis] = OptArg.Undefined,
  yaxis7: OptArg[LayoutAxis] = OptArg.Undefined,
  yaxis8: OptArg[LayoutAxis] = OptArg.Undefined,
  yaxis9: OptArg[LayoutAxis] = OptArg.Undefined,
  margin: OptArg[Margin] = OptArg.Undefined,
  height: OptArg[Number] = OptArg.Undefined,
  width: OptArg[Number] = OptArg.Undefined,
  hovermode: OptArg[FalseOr[Layout.HoverMode]] = OptArg.Undefined,
  hoverdistance: OptArg[Number] = OptArg.Undefined,
  hoverlabel: OptArg[HoverLabel] = OptArg.Undefined,
  calendar: OptArg[Calendar] = OptArg.Undefined,
  mapbox: OptArg[Mapbox] = OptArg.Undefined,
  subplot: OptArg[String] = OptArg.Undefined,
  dragmode: OptArg[FalseOr[Layout.DragMode]] = OptArg.Undefined,
  orientation: OptArg[Number] = OptArg.Undefined,
  annotations: OptArg[Array[Annotations]] = OptArg.Undefined,
  shapes: OptArg[Array[Shape]] = OptArg.Undefined,
  images: OptArg[Array[Image]] = OptArg.Undefined,
  sliders: OptArg[Array[Slider]] = OptArg.Undefined,
  legend: OptArg[Legend] = OptArg.Undefined,
  font: OptArg[Font] = OptArg.Undefined,
  scene: OptArg[Scene] = OptArg.Undefined,
  barmode: OptArg[Layout.BarMode] = OptArg.Undefined,
  barnorm: OptArg[BlankOr[Layout.BarNormalisation]] = OptArg.Undefined,
  bargap: OptArg[Number] = OptArg.Undefined,
  bargroupgap: OptArg[Number] = OptArg.Undefined,
  selectdirection: OptArg[Layout.SelectDirection] = OptArg.Undefined,
  hiddenlabels: OptArg[Seq[String]] = OptArg.Undefined,
  grid: OptArg[Layout.Grid] = OptArg.Undefined,
  polar: OptArg[PolarLayout] = OptArg.Undefined,
  polar2: OptArg[PolarLayout] = OptArg.Undefined,
  polar3: OptArg[PolarLayout] = OptArg.Undefined,
  polar4: OptArg[PolarLayout] = OptArg.Undefined,
  polar5: OptArg[PolarLayout] = OptArg.Undefined,
  polar6: OptArg[PolarLayout] = OptArg.Undefined,
  polar7: OptArg[PolarLayout] = OptArg.Undefined,
  polar8: OptArg[PolarLayout] = OptArg.Undefined,
  polar9: OptArg[PolarLayout] = OptArg.Undefined,
  transition: OptArg[Transition] = OptArg.Undefined,
)

object Layout {

  final case class Title(
    text: OptArg[String] = OptArg.Undefined,
    font: OptArg[Font] = OptArg.Undefined,
    xref: OptArg[ContainerReference] = OptArg.Undefined,
    yref: OptArg[ContainerReference] = OptArg.Undefined,
    x: OptArg[Number] = OptArg.Undefined,
    y: OptArg[Number] = OptArg.Undefined,
    xanchor: OptArg[Anchor.X] = OptArg.Undefined,
    yanchor: OptArg[Anchor.Y] = OptArg.Undefined,
    pad: OptArg[Padding] = OptArg.Undefined,
  )

  object Title {

    sealed abstract class ContainerReference(val asString: String) extends JSEnum

    object ContainerReference {
      case object Container extends ContainerReference("container")
      case object Paper     extends ContainerReference("paper")
    }

    implicit val encoder: Encoder[Title] = Encoder.forProduct9(
      "text",
      "font",
      "xref",
      "yref",
      "x",
      "y",
      "xanchor",
      "yanchor",
      "pad",
    )(t =>
      (
        t.text,
        t.font,
        t.xref,
        t.yref,
        t.x,
        t.y,
        t.xanchor,
        t.yanchor,
        t.pad,
      ),
    )

  }

  final case class Grid(
    rows: OptArg[Int] = OptArg.Undefined,
    roworder: OptArg[Grid.RowOrder] = OptArg.Undefined,
    columns: OptArg[Int] = OptArg.Undefined,
    subplots: OptArg[Seq[String]] = OptArg.Undefined,
    xaxes: OptArg[Seq[String]] = OptArg.Undefined,
    yaxes: OptArg[Seq[String]] = OptArg.Undefined,
    pattern: OptArg[Grid.Pattern] = OptArg.Undefined,
    xgap: OptArg[Datum.OfNumber] = OptArg.Undefined,
    ygap: OptArg[Datum.OfNumber] = OptArg.Undefined,
    domain: OptArg[Grid.Domain] = OptArg.Undefined,
    xside: OptArg[Grid.Side.X] = OptArg.Undefined,
    yside: OptArg[Grid.Side.Y] = OptArg.Undefined,
  )

  object Grid {

    sealed abstract class RowOrder(val asString: String) extends JSEnum

    object RowOrder {
      case object TopToBottom extends RowOrder("top to bottom")
      case object BottomToTop extends RowOrder("bottom to top")
    }

    sealed abstract class Pattern(val asString: String) extends JSEnum

    object Pattern {
      case object Independent extends Pattern("independent")
      case object Coupled     extends Pattern("coupled")
    }

    final case class Domain(
      x: OptArg[Seq[Number]] = OptArg.Undefined,
      y: OptArg[Seq[Number]] = OptArg.Undefined,
    )

    object Domain {
      implicit val encoder: Encoder[Domain] = Encoder.forProduct2("x", "y")(d => (d.x, d.y))
    }

    object Side {

      sealed abstract class X(val asString: String) extends JSEnum

      object X {
        case object Left      extends X("left")
        case object LeftPlot  extends X("left plot")
        case object RightPlot extends X("right plot")
        case object Right     extends X("right")
      }

      sealed abstract class Y(val asString: String) extends JSEnum

      object Y {
        case object Bottom     extends Y("bottom")
        case object BottomPlot extends Y("bottom plot")
        case object TopPlot    extends Y("top plot")
        case object Top        extends Y("top")
      }

    }

    implicit val encoder: Encoder[Grid] = Encoder.forProduct12(
      "rows",
      "roworder",
      "columns",
      "subplots",
      "xaxes",
      "yaxes",
      "pattern",
      "xgap",
      "ygap",
      "domain",
      "xside",
      "yside",
    )(g =>
      (
        g.rows,
        g.roworder,
        g.columns,
        g.subplots,
        g.xaxes,
        g.yaxes,
        g.pattern,
        g.xgap,
        g.ygap,
        g.domain,
        g.xside,
        g.yside,
      ),
    )

  }

  sealed abstract class HoverMode(val asString: String) extends JSEnum

  object HoverMode {
    case object Closest  extends HoverMode("closest")
    case object X        extends HoverMode("x")
    case object Y        extends HoverMode("y")
    case object XUnified extends HoverMode("x unified")
    case object YUnified extends HoverMode("y unified")
  }

  sealed abstract class DragMode(val asString: String) extends JSEnum

  object DragMode {
    case object Zoom      extends DragMode("zoom")
    case object Pan       extends DragMode("pan")
    case object Select    extends DragMode("select")
    case object Lasso     extends DragMode("lasso")
    case object Orbit     extends DragMode("orbit")
    case object Turntable extends DragMode("turntable")
  }

  sealed abstract class BarMode(val asString: String) extends JSEnum

  object BarMode {
    case object Stack    extends BarMode("stack")
    case object Group    extends BarMode("group")
    case object Overlay  extends BarMode("overlay")
    case object Relative extends BarMode("relative")
  }

  sealed abstract class BarNormalisation(val asString: String) extends JSEnum

  object BarNormalisation {
    case object Fraction extends BarNormalisation("fraction")
    case object Percent  extends BarNormalisation("percent")
  }

  sealed abstract class SelectDirection(val asString: String) extends JSEnum

  object SelectDirection {
    case object HorizontalOnly extends SelectDirection("h")
    case object VerticalOnly   extends SelectDirection("v")
    case object DiagonalOnly   extends SelectDirection("d")
    case object Any            extends SelectDirection("a")
  }

  implicit val encoder: Encoder[Layout] = Encoder { layout =>
    Json.obj(
      "colorway" := layout.colorway,
      "title" := layout.title,
      "titlefont" := layout.titlefont,
      "autosize" := layout.autosize,
      "showlegend" := layout.showlegend,
      "paper_bgcolor" := layout.paper_bgcolor,
      "plot_bgcolor" := layout.plot_bgcolor,
      "separators" := layout.separators,
      "hidesources" := layout.hidesources,
      "xaxis" := layout.xaxis,
      "xaxis2" := layout.xaxis2,
      "xaxis3" := layout.xaxis3,
      "xaxis4" := layout.xaxis4,
      "xaxis5" := layout.xaxis5,
      "xaxis6" := layout.xaxis6,
      "xaxis7" := layout.xaxis7,
      "xaxis8" := layout.xaxis8,
      "xaxis9" := layout.xaxis9,
      "yaxis" := layout.yaxis,
      "yaxis2" := layout.yaxis2,
      "yaxis3" := layout.yaxis3,
      "yaxis4" := layout.yaxis4,
      "yaxis5" := layout.yaxis5,
      "yaxis6" := layout.yaxis6,
      "yaxis7" := layout.yaxis7,
      "yaxis8" := layout.yaxis8,
      "yaxis9" := layout.yaxis9,
      "margin" := layout.margin,
      "height" := layout.height,
      "width" := layout.width,
      "hovermode" := layout.hovermode,
      "hoverdistance" := layout.hoverdistance,
      "hoverlabel" := layout.hoverlabel,
      "calendar" := layout.calendar,
      "mapbox" := layout.mapbox,
      "subplot" := layout.subplot,
      "dragmode" := layout.dragmode,
      "orientation" := layout.orientation,
      "annotations" := layout.annotations,
      "shapes" := layout.shapes,
      "images" := layout.images,
      "sliders" := layout.sliders,
      "legend" := layout.legend,
      "font" := layout.font,
      "scene" := layout.scene,
      "barmode" := layout.barmode,
      "barnorm" := layout.barnorm,
      "bargap" := layout.bargap,
      "bargroupgap" := layout.bargroupgap,
      "selectdirection" := layout.selectdirection,
      "hiddenlabels" := layout.hiddenlabels,
      "grid" := layout.grid,
      "polar" := layout.polar,
      "polar2" := layout.polar2,
      "polar3" := layout.polar3,
      "polar4" := layout.polar4,
      "polar5" := layout.polar5,
      "polar6" := layout.polar6,
      "polar7" := layout.polar7,
      "polar8" := layout.polar8,
      "polar9" := layout.polar9,
      "transition" := layout.transition,
    )
  }

}
