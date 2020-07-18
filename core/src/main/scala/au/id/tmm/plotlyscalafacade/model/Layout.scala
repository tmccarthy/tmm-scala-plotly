package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.Layout.Title.ContainerReference
import au.id.tmm.plotlyscalafacade.model.utilities.{BlankOr, FalseOr, JSEnum}

final case class Layout(
  colorway: Option[Seq[Color]],
  title: Option[Layout.Title],
  titlefont: Option[Font],
  autosize: Option[Boolean],
  showlegend: Option[Boolean],
  paper_bgcolor: Option[Color],
  plot_bgcolor: Option[Color],
  separators: Option[String],
  hidesources: Option[Boolean],
  xaxis: Option[LayoutAxis],
  xaxis2: Option[LayoutAxis],
  xaxis3: Option[LayoutAxis],
  xaxis4: Option[LayoutAxis],
  xaxis5: Option[LayoutAxis],
  xaxis6: Option[LayoutAxis],
  xaxis7: Option[LayoutAxis],
  xaxis8: Option[LayoutAxis],
  xaxis9: Option[LayoutAxis],
  yaxis: Option[LayoutAxis],
  yaxis2: Option[LayoutAxis],
  yaxis3: Option[LayoutAxis],
  yaxis4: Option[LayoutAxis],
  yaxis5: Option[LayoutAxis],
  yaxis6: Option[LayoutAxis],
  yaxis7: Option[LayoutAxis],
  yaxis8: Option[LayoutAxis],
  yaxis9: Option[LayoutAxis],
  margin: Option[Margin],
  height: Option[Number],
  width: Option[Number],
  hovermode: Option[FalseOr[Layout.HoverMode]],
  hoverdistance: Option[Number],
  hoverlabel: Option[HoverLabel],
  calendar: Option[Calendar],
  mapbox: Option[Mapbox],
  subplot: Option[String],
  dragmode: Option[FalseOr[Layout.DragMode]],
  orientation: Option[Number],
  annotations: Option[Array[Annotations]],
  shapes: Option[Array[Shape]],
  images: Option[Array[Image]],
  sliders: Option[Array[Slider]],
  legend: Option[Legend],
  font: Option[Font],
  scene: Option[Scene],
  barmode: Option[Layout.BarMode],
  barnorm: Option[BlankOr[Layout.BarNormalisation]],
  bargap: Option[Number],
  bargroupgap: Option[Number],
  selectdirection: Option[Layout.SelectDirection],
  hiddenlabels: Option[Seq[String]],
  grid: Option[Layout.Grid],
  polar: Option[PolarLayout],
  polar2: Option[PolarLayout],
  polar3: Option[PolarLayout],
  polar4: Option[PolarLayout],
  polar5: Option[PolarLayout],
  polar6: Option[PolarLayout],
  polar7: Option[PolarLayout],
  polar8: Option[PolarLayout],
  polar9: Option[PolarLayout],
  transition: Option[Transition],
)

object Layout {

  final case class Title(
    text: Option[String],
    font: Option[Font],
    xref: Option[ContainerReference],
    yref: Option[ContainerReference],
    x: Option[Number],
    y: Option[Number],
    xanchor: Option[Anchor.X],
    yanchor: Option[Anchor.Y],
    pad: Option[Padding],
  )

  object Title {

    sealed abstract class ContainerReference(val asString: String) extends JSEnum

    object ContainerReference {
      case object Container extends ContainerReference("container")
      case object Paper     extends ContainerReference("paper")
    }

  }

  final case class Grid(
    rows: Option[Number],
    roworder: Option[Grid.RowOrder],
    columns: Option[Number],
    subplots: Option[Seq[String]],
    xaxes: Option[Seq[String]],
    yaxes: Option[Seq[String]],
    pattern: Option[Grid.Pattern],
    xgap: Option[Number],
    ygap: Option[Number],
    domain: Option[Grid.Domain],
    xside: Option[Grid.Side.X],
    yside: Option[Grid.Side.Y],
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
      x: Option[NumberArray],
      y: Option[NumberArray],
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
