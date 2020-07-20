# `tmm-scala-plotly`

Yet another library for integration between [Plotly](https://plotly.com/javascript/) and Scala.

## Getting started

In your `build.sbt` file.

```scala
tmmScalaPlotlyVersion = 0.0.1

libraryDependencies += "au.id.tmm.plotly" %% "plotly-core" % tmmScalaPlotlyVersion
```

## Rationale

This library is a representation of the [Plotly JavaScript API](https://plotly.com/javascript/) in Scala. It is a
hand-written and almost complete representation of the Plotly API as documented in [DefinitelyTyped](https://github.com/DefinitelyTyped/DefinitelyTyped/blob/53e3fb2/types/plotly.js/index.d.ts),
and informed by the official documentation.

There are already at least two other libraries that provide Scala integration for Plotly:

* [`facultyai/scala-plotly-client`](https://github.com/facultyai/scala-plotly-client) is an older library that is 
  documented on the [Plotly website](https://plotly.com/scala/). It does not appear to be under active development.
* [`alexarchambault/plotly-scala`](https://github.com/alexarchambault/plotly-scala/), which is a modern and mature 
  offering.

My advice would be that if you are looking for a library to use Plotly from Scala, you should use [`alexarchambault/plotly-scala`](https://github.com/alexarchambault/plotly-scala/).
It is more mature and better tested than this library, and I've had a lot of success with it.

This library has a couple of key philosophical differences with `alexarchambault/plotly-scala`, which is why I decided 
to write it:

* **Completeness:** This library is an almost exhaustive representation of the Plotly Javascript API. You should be able 
  to produce every kind of plot supported by the official Javascript library.
* **Self-documenting:** This library makes extensive use of value classes and algebraic data types to attempt to clarify 
  the meaning of fields within the Plotly API. Implicit conversions are opt-in and are intended only for ease-of-use 
  (see [below](#usage)).
* **Incorrect:** This library does not (yet) use the power of the Scala type system to ensure valid use of Plotly. For 
  example, you can provide `z` co-ordinates to two-dimensional scatter plots. This is an intentional tradeoff to make it
  simpler to produce an exhaustive representation of the Javascript API.
* **No ScalaJS support:** I don't use ScalaJS, so I'm not interested in supporting it.
* **Circe as a first-class citizen:** This project uses [`circe`](https://github.com/circe/circe) to encode the model to 
  Json. The `circe` integrations are deliberately exposed by the main library to give clients as much flexibility as 
  possible when encoding.
  
Note that I've hand-written this interface over Plotly but have done almost no thorough testing. Many of the corners of 
this library are unlikely to have seen any use. *Caveat emptor*.

## Usage

To draw a pie chart:

```scala
import au.id.tmm.plotly._
import au.id.tmm.plotly.model._
import au.id.tmm.plotly.model.utilities._

val (labels, values, colours) = List(
  ("Coalition", 5_906_875, Color("blue")),
  ("Labor", 4_752_160, Color("red")),
  ("Greens", 1_482_923, Color("green")),
  ("Other", 2_111_435, Color("grey")),
).unzip3

val plot = Plot(
  data = List(
    Trace(
      `type` = OptArg.Of(Trace.Type.Pie),
      labels = OptArg.Of(DataArray.OfStrings(labels)),
      values = OptArg.Of(DataArray.OfInts(values)),
      marker = OptArg.Of(
        PlotMarker(
          colors = OptArg.Of(colours),
        ),
      ),
    ),
  ),
  layout = OptArg.Of(
    Layout(
      title = OptArg.Of(
        Layout.Title(
          text = OptArg.Of("2019 primary votes"),
        ),
      ),
      width = OptArg.Of(300d),
      height = OptArg.Of(300d),
    ),
  ),
)

Plotting.openInBrowser(plot)
```

![A pie chart showing the primary votes at the 2019 Australian Federal Election](/docs/pie.png)

### Implicit conversions for ergonomics

In the above you should note that we make use of types like `OptArg` and `DataArray`. These allow us to have explicit 
and type-safe representations of optional parameters and the different types of data array respectively. Using these
everywhere gets tiring, so we can import `au.id.tmm.plotly.syntax.all._` to make things a little less verbose 
while retaining type safety. With this import, the above becomes:

```scala
import au.id.tmm.plotly._
import au.id.tmm.plotly.model._
import au.id.tmm.plotly.syntax.all._

val (labels, values, colours) = List(
  ("Coalition", 5_906_875, Color("blue")),
  ("Labor", 4_752_160, Color("red")),
  ("Greens", 1_482_923, Color("green")),
  ("Other", 2_111_435, Color("grey")),
).unzip3

val plot = Plot(
  data = List(
    Trace(
      `type` = Trace.Type.Pie,
      labels = labels,
      values = values,
      marker = PlotMarker(colors = colours),
    ),
  ),
  layout = Layout(
    title = Layout.Title(text = "2019 primary votes"),
    width = 300,
    height = 300,
  ),
)

Plotting.openInBrowser(plot)
```