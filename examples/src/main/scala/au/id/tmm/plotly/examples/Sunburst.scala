package au.id.tmm.plotly.examples

import au.id.tmm.plotly.model._
import au.id.tmm.plotly.model.utilities._
import au.id.tmm.plotly.syntax._
import au.id.tmm.plotly.{Plot, Plotting}

object Sunburst {

  def main(args: Array[String]): Unit = {

    val trace = Trace.Sunburst(
      rootSectors = List(
        Trace.Sunburst.Sector(
          value = 200,
          label = "Food",
          children = List(
            Trace.Sunburst.Sector(
              value = 100,
              label = "Fruit",
              marker = Trace.Sunburst.Sector.Marker(
                color = Color("tomato"),
              ),
              children = List(
                Trace.Sunburst.Sector(
                  value = 50,
                  label = "🍎",
                ),
                Trace.Sunburst.Sector(
                  value = 25,
                  label = "🍌",
                ),
                Trace.Sunburst.Sector(
                  value = 25,
                  label = "🍐",
                ),
              ),
            ),
            Trace.Sunburst.Sector(
              value = 75,
              label = "Sweets",
              marker = Trace.Sunburst.Sector.Marker(
                color = Color("pink"),
              ),
              children = List(
                Trace.Sunburst.Sector(
                  value = 50,
                  label = "🍫",
                ),
                Trace.Sunburst.Sector(
                  value = 25,
                  label = "🍩",
                ),
              ),
            ),
            Trace.Sunburst.Sector(
              value = 25,
              label = "Bread",
              marker = Trace.Sunburst.Sector.Marker(
                color = Color("khaki"),
              ),
              children = List(
                Trace.Sunburst.Sector(
                  value = 20,
                  label = "🥖",
                ),
                Trace.Sunburst.Sector(
                  value = 5,
                  label = "🥯",
                ),
              ),
            ),
          ),
        ),
      ),
      branchvalues = Trace.BranchValues.Total,
      hoverinfo = Trace.HoverInfo.Of(
        FlagList(
          Trace.HoverInfo.Flag.Value,
          Trace.HoverInfo.Flag.PercentParent,
          Trace.HoverInfo.Flag.PercentRoot,
        ),
      ),
    )

    val plot = Plot(
      data = List(trace),
      layout = Layout(
        title = Layout.Title(text = "Sunburst example"),
      ),
    )

    Plotting.openInBrowser(plot)
  }

}
