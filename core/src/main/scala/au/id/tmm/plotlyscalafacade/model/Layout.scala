package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.Layout.Title.ContainerReference
import au.id.tmm.plotlyscalafacade.model.utilities.{BlankOr, FalseOr, JSEnum}

final case class Layout(
  colorway: Option[Seq[Color]] = None,
  title: Option[Layout.Title] = None,
  titlefont: Option[Font] = None,
  autosize: Option[Boolean] = None,
  showlegend: Option[Boolean] = None,
  paper_bgcolor: Option[Color] = None,
  plot_bgcolor: Option[Color] = None,
  separators: Option[String] = None,
  hidesources: Option[Boolean] = None,
  xaxis: Option[LayoutAxis] = None,
  xaxis2: Option[LayoutAxis] = None,
  xaxis3: Option[LayoutAxis] = None,
  xaxis4: Option[LayoutAxis] = None,
  xaxis5: Option[LayoutAxis] = None,
  xaxis6: Option[LayoutAxis] = None,
  xaxis7: Option[LayoutAxis] = None,
  xaxis8: Option[LayoutAxis] = None,
  xaxis9: Option[LayoutAxis] = None,
  yaxis: Option[LayoutAxis] = None,
  yaxis2: Option[LayoutAxis] = None,
  yaxis3: Option[LayoutAxis] = None,
  yaxis4: Option[LayoutAxis] = None,
  yaxis5: Option[LayoutAxis] = None,
  yaxis6: Option[LayoutAxis] = None,
  yaxis7: Option[LayoutAxis] = None,
  yaxis8: Option[LayoutAxis] = None,
  yaxis9: Option[LayoutAxis] = None,
  margin: Option[Margin] = None,
  height: Option[Number] = None,
  width: Option[Number] = None,
  hovermode: Option[FalseOr[Layout.HoverMode]] = None,
  hoverdistance: Option[Number] = None,
  hoverlabel: Option[HoverLabel] = None,
  calendar: Option[Calendar] = None,
  mapbox: Option[Mapbox] = None,
  subplot: Option[String] = None,
  dragmode: Option[FalseOr[Layout.DragMode]] = None,
  orientation: Option[Number] = None,
  annotations: Option[Array[Annotations]] = None,
  shapes: Option[Array[Shape]] = None,
  images: Option[Array[Image]] = None,
  sliders: Option[Array[Slider]] = None,
  legend: Option[Legend] = None,
  font: Option[Font] = None,
  scene: Option[Scene] = None,
  barmode: Option[Layout.BarMode] = None,
  barnorm: Option[BlankOr[Layout.BarNormalisation]] = None,
  bargap: Option[Number] = None,
  bargroupgap: Option[Number] = None,
  selectdirection: Option[Layout.SelectDirection] = None,
  hiddenlabels: Option[Seq[String]] = None,
  grid: Option[Layout.Grid] = None,
  polar: Option[PolarLayout] = None,
  polar2: Option[PolarLayout] = None,
  polar3: Option[PolarLayout] = None,
  polar4: Option[PolarLayout] = None,
  polar5: Option[PolarLayout] = None,
  polar6: Option[PolarLayout] = None,
  polar7: Option[PolarLayout] = None,
  polar8: Option[PolarLayout] = None,
  polar9: Option[PolarLayout] = None,
  transition: Option[Transition] = None,
)

object Layout {

  final case class Title(
    text: Option[String] = None,
    font: Option[Font] = None,
    xref: Option[ContainerReference] = None,
    yref: Option[ContainerReference] = None,
    x: Option[Number] = None,
    y: Option[Number] = None,
    xanchor: Option[Anchor.X] = None,
    yanchor: Option[Anchor.Y] = None,
    pad: Option[Padding] = None,
  )

  object Title {

    sealed abstract class ContainerReference(val asString: String) extends JSEnum

    object ContainerReference {
      case object Container extends ContainerReference("container")
      case object Paper     extends ContainerReference("paper")
    }

  }

  final case class Grid(
    rows: Option[Number] = None,
    roworder: Option[Grid.RowOrder] = None,
    columns: Option[Number] = None,
    subplots: Option[Seq[String]] = None,
    xaxes: Option[Seq[String]] = None,
    yaxes: Option[Seq[String]] = None,
    pattern: Option[Grid.Pattern] = None,
    xgap: Option[Number] = None,
    ygap: Option[Number] = None,
    domain: Option[Grid.Domain] = None,
    xside: Option[Grid.Side.X] = None,
    yside: Option[Grid.Side.Y] = None,
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
      x: Option[NumberArray] = None,
      y: Option[NumberArray] = None,
    )

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

}
