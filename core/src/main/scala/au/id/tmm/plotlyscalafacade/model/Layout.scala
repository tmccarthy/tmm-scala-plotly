package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.Layout.Title.ContainerReference
import au.id.tmm.plotlyscalafacade.model.utilities.{Arg, BlankOr, FalseOr, JSEnum}
import io.circe.syntax._
import io.circe.{Encoder, Json}

final case class Layout(
  colorway: Arg[Seq[Color]] = Arg.Undefined,
  title: Arg[Layout.Title] = Arg.Undefined,
  titlefont: Arg[Font] = Arg.Undefined,
  autosize: Arg[Boolean] = Arg.Undefined,
  showlegend: Arg[Boolean] = Arg.Undefined,
  paper_bgcolor: Arg[Color] = Arg.Undefined,
  plot_bgcolor: Arg[Color] = Arg.Undefined,
  separators: Arg[String] = Arg.Undefined,
  hidesources: Arg[Boolean] = Arg.Undefined,
  xaxis: Arg[LayoutAxis] = Arg.Undefined,
  xaxis2: Arg[LayoutAxis] = Arg.Undefined,
  xaxis3: Arg[LayoutAxis] = Arg.Undefined,
  xaxis4: Arg[LayoutAxis] = Arg.Undefined,
  xaxis5: Arg[LayoutAxis] = Arg.Undefined,
  xaxis6: Arg[LayoutAxis] = Arg.Undefined,
  xaxis7: Arg[LayoutAxis] = Arg.Undefined,
  xaxis8: Arg[LayoutAxis] = Arg.Undefined,
  xaxis9: Arg[LayoutAxis] = Arg.Undefined,
  yaxis: Arg[LayoutAxis] = Arg.Undefined,
  yaxis2: Arg[LayoutAxis] = Arg.Undefined,
  yaxis3: Arg[LayoutAxis] = Arg.Undefined,
  yaxis4: Arg[LayoutAxis] = Arg.Undefined,
  yaxis5: Arg[LayoutAxis] = Arg.Undefined,
  yaxis6: Arg[LayoutAxis] = Arg.Undefined,
  yaxis7: Arg[LayoutAxis] = Arg.Undefined,
  yaxis8: Arg[LayoutAxis] = Arg.Undefined,
  yaxis9: Arg[LayoutAxis] = Arg.Undefined,
  margin: Arg[Margin] = Arg.Undefined,
  height: Arg[Number] = Arg.Undefined,
  width: Arg[Number] = Arg.Undefined,
  hovermode: Arg[FalseOr[Layout.HoverMode]] = Arg.Undefined,
  hoverdistance: Arg[Number] = Arg.Undefined,
  hoverlabel: Arg[HoverLabel] = Arg.Undefined,
  calendar: Arg[Calendar] = Arg.Undefined,
  mapbox: Arg[Mapbox] = Arg.Undefined,
  subplot: Arg[String] = Arg.Undefined,
  dragmode: Arg[FalseOr[Layout.DragMode]] = Arg.Undefined,
  orientation: Arg[Number] = Arg.Undefined,
  annotations: Arg[Array[Annotations]] = Arg.Undefined,
  shapes: Arg[Array[Shape]] = Arg.Undefined,
  images: Arg[Array[Image]] = Arg.Undefined,
  sliders: Arg[Array[Slider]] = Arg.Undefined,
  legend: Arg[Legend] = Arg.Undefined,
  font: Arg[Font] = Arg.Undefined,
  scene: Arg[Scene] = Arg.Undefined,
  barmode: Arg[Layout.BarMode] = Arg.Undefined,
  barnorm: Arg[BlankOr[Layout.BarNormalisation]] = Arg.Undefined,
  bargap: Arg[Number] = Arg.Undefined,
  bargroupgap: Arg[Number] = Arg.Undefined,
  selectdirection: Arg[Layout.SelectDirection] = Arg.Undefined,
  hiddenlabels: Arg[Seq[String]] = Arg.Undefined,
  grid: Arg[Layout.Grid] = Arg.Undefined,
  polar: Arg[PolarLayout] = Arg.Undefined,
  polar2: Arg[PolarLayout] = Arg.Undefined,
  polar3: Arg[PolarLayout] = Arg.Undefined,
  polar4: Arg[PolarLayout] = Arg.Undefined,
  polar5: Arg[PolarLayout] = Arg.Undefined,
  polar6: Arg[PolarLayout] = Arg.Undefined,
  polar7: Arg[PolarLayout] = Arg.Undefined,
  polar8: Arg[PolarLayout] = Arg.Undefined,
  polar9: Arg[PolarLayout] = Arg.Undefined,
  transition: Arg[Transition] = Arg.Undefined,
)

object Layout {

  final case class Title(
    text: Arg[String] = Arg.Undefined,
    font: Arg[Font] = Arg.Undefined,
    xref: Arg[ContainerReference] = Arg.Undefined,
    yref: Arg[ContainerReference] = Arg.Undefined,
    x: Arg[Number] = Arg.Undefined,
    y: Arg[Number] = Arg.Undefined,
    xanchor: Arg[Anchor.X] = Arg.Undefined,
    yanchor: Arg[Anchor.Y] = Arg.Undefined,
    pad: Arg[Padding] = Arg.Undefined,
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
    rows: Arg[Number] = Arg.Undefined,
    roworder: Arg[Grid.RowOrder] = Arg.Undefined,
    columns: Arg[Number] = Arg.Undefined,
    subplots: Arg[Seq[String]] = Arg.Undefined,
    xaxes: Arg[Seq[String]] = Arg.Undefined,
    yaxes: Arg[Seq[String]] = Arg.Undefined,
    pattern: Arg[Grid.Pattern] = Arg.Undefined,
    xgap: Arg[Number] = Arg.Undefined,
    ygap: Arg[Number] = Arg.Undefined,
    domain: Arg[Grid.Domain] = Arg.Undefined,
    xside: Arg[Grid.Side.X] = Arg.Undefined,
    yside: Arg[Grid.Side.Y] = Arg.Undefined,
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
      x: Arg[NumberArray] = Arg.Undefined,
      y: Arg[NumberArray] = Arg.Undefined,
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
