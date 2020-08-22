package au.id.tmm.plotly.examples

object Pie {

  def main(args: Array[String]): Unit = {
    withoutSyntax()
    withSyntax()
  }

  private def withoutSyntax(): Unit = {
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
          traceType = OptArg.Of(Trace.Type.Pie),
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
  }

  private def withSyntax(): Unit = {
    import au.id.tmm.plotly._
    import au.id.tmm.plotly.model._
    import au.id.tmm.plotly.syntax._

    val (labels, values, colours) = List(
      ("Coalition", 5_906_875, Color("blue")),
      ("Labor", 4_752_160, Color("red")),
      ("Greens", 1_482_923, Color("green")),
      ("Other", 2_111_435, Color("grey")),
    ).unzip3

    val plot = Plot(
      data = List(
        Trace(
          traceType = Trace.Type.Pie,
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
  }
}
