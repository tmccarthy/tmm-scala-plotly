package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.Layout.Title.ContainerReference

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
hovermode: "closest" | "x" | "y" | "x unified" | "y unified" | false,
hoverdistance: Number,
hoverlabel: Partial[HoverLabel],
calendar: Calendar,
//ternary: Nothing, // TODO
//geo: Nothing, // TODO
mapbox: Partial[Mapbox],
subplot: String,
radialaxis: Partial[Axis],
//angularaxis: Nothing, // TODO
dragmode: "zoom" | "pan" | "select" | "lasso" | "orbit" | "turntable" | false,
orientation: Number,
annotations: Array[Partial[Annotations]],
shapes: Array[Partial[Shape]],
images: Array[Partial[Image]],
//updatemenus: Nothing, // TODO
sliders: Array[Partial[Slider]],
legend: Partial[Legend],
font: Partial[Font],
scene: Partial[Scene],
barmode: "stack" | "group" | "overlay" | "relative",
barnorm: "" | "fraction" | "percent",
bargap: Number,
bargroupgap: Number,
selectdirection: "h" | "v" | "d" | "any",
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

    sealed abstract class ContainerReference(val asString: String) extends JSEnum // TODO might be common

    object ContainerReference {
      case object Container extends ContainerReference("container")
      case object Paper     extends ContainerReference("paper")
    }

  }

  final case class Grid(
    rows: Number,
  roworder: "top to bottom" | "bottom to top",
  columns: Number,
  subplots: string[],
  xaxes: string[],
  yaxes: string[],
  pattern: "independent" | "coupled",
  xgap: Number,
  ygap: Number,
  domain: Partial[{
    x: number[],
    y: number[],
  }],
  xside: "bottom" | "bottom plot" | "top plot" | "top",
  yside: "left" | "left plot" | "right plot" | "right",
  )

  object Grid {

  }

}
