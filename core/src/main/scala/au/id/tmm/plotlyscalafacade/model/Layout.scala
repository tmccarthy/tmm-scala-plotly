package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.Layout.Title.ContainerReference
import au.id.tmm.plotlyscalafacade.model.utilities.{BlankOr, FalseOr, JSEnum}

final case class Layout(
  colorway: Seq[Color],
  title: Partial[Layout.Title],
  titlefont: Partial[Font],
  autosize: Boolean,
  showlegend: Boolean,
  paper_bgcolor: Color,
  plot_bgcolor: Color,
  separators: String,
  hidesources: Boolean,
  xaxis: Partial[LayoutAxis],
  xaxis2: Partial[LayoutAxis],
  xaxis3: Partial[LayoutAxis],
  xaxis4: Partial[LayoutAxis],
  xaxis5: Partial[LayoutAxis],
  xaxis6: Partial[LayoutAxis],
  xaxis7: Partial[LayoutAxis],
  xaxis8: Partial[LayoutAxis],
  xaxis9: Partial[LayoutAxis],
  yaxis: Partial[LayoutAxis],
  yaxis2: Partial[LayoutAxis],
  yaxis3: Partial[LayoutAxis],
  yaxis4: Partial[LayoutAxis],
  yaxis5: Partial[LayoutAxis],
  yaxis6: Partial[LayoutAxis],
  yaxis7: Partial[LayoutAxis],
  yaxis8: Partial[LayoutAxis],
  yaxis9: Partial[LayoutAxis],
  margin: Partial[Margin],
  height: Number,
  width: Number,
  hovermode: FalseOr[Layout.HoverMode],
  hoverdistance: Number,
  hoverlabel: Partial[HoverLabel],
  calendar: Calendar,
  mapbox: Partial[Mapbox],
  subplot: String,
  dragmode: FalseOr[Layout.DragMode],
  orientation: Number,
  annotations: Array[Partial[Annotations]],
  shapes: Array[Partial[Shape]],
  images: Array[Partial[Image]],
  sliders: Array[Partial[Slider]],
  legend: Partial[Legend],
  font: Partial[Font],
  scene: Partial[Scene],
  barmode: Layout.BarMode,
  barnorm: BlankOr[Layout.BarNormalisation],
  bargap: Number,
  bargroupgap: Number,
  selectdirection: Layout.SelectDirection,
  hiddenlabels: Seq[String],
  grid: Layout.Grid,
  polar: Partial[PolarLayout],
  polar2: Partial[PolarLayout],
  polar3: Partial[PolarLayout],
  polar4: Partial[PolarLayout],
  polar5: Partial[PolarLayout],
  polar6: Partial[PolarLayout],
  polar7: Partial[PolarLayout],
  polar8: Partial[PolarLayout],
  polar9: Partial[PolarLayout],
  transition: Transition,
)

object Layout {

  final case class Title(
    text: String,
    font: Partial[Font],
    xref: ContainerReference,
    yref: ContainerReference,
    x: Number,
    y: Number,
    xanchor: Anchor.X,
    yanchor: Anchor.Y,
    pad: Partial[Padding],
  )

  object Title {

    sealed abstract class ContainerReference(val asString: String) extends JSEnum

    object ContainerReference {
      case object Container extends ContainerReference("container")
      case object Paper     extends ContainerReference("paper")
    }

  }

  final case class Grid(
    rows: Number,
    roworder: Grid.RowOrder,
    columns: Number,
    subplots: Seq[String],
    xaxes: Seq[String],
    yaxes: Seq[String],
    pattern: Grid.Pattern,
    xgap: Number,
    ygap: Number,
    domain: Partial[Grid.Domain],
    xside: Grid.Side.X,
    yside: Grid.Side.Y,
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
      x: NumberArray,
      y: NumberArray,
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
