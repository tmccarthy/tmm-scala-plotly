package au.id.tmm.plotly.examples

import au.id.tmm.plotly.model._
import au.id.tmm.plotly.model.utilities._
import au.id.tmm.plotly.syntax.all._
import au.id.tmm.plotly.{Plot, Plotting}

object Sunburst {

  def main(args: Array[String]): Unit = {

    val trace = Trace.Sunburst(
      rootSectors = List(
        Trace.Sunburst.Sector(
          value = 200,
          id = "food",
          label = "Food",
          children = List(
            Trace.Sunburst.Sector(
              value = 100,
              id = "fruit",
              label = "Fruit",
              children = List(
                Trace.Sunburst.Sector(
                  value = 50,
                  id = "apple",
                  label = "🍎",
                ),
                Trace.Sunburst.Sector(
                  value = 25,
                  id = "banana",
                  label = "🍌",
                ),
                Trace.Sunburst.Sector(
                  value = 25,
                  id = "pear",
                  label = "🍐",
                ),
              ),
            ),
            Trace.Sunburst.Sector(
              value = 75,
              id = "sweets",
              label = "Sweets",
              children = List(
                Trace.Sunburst.Sector(
                  value = 50,
                  id = "chocolate",
                  label = "🍫",
                ),
                Trace.Sunburst.Sector(
                  value = 25,
                  id = "donuts",
                  label = "🍩",
                ),
              ),
            ),
            Trace.Sunburst.Sector(
              value = 25,
              id = "bread",
              label = "Bread",
              children = List(
                Trace.Sunburst.Sector(
                  value = 20,
                  id = "baguette",
                  label = "🥖",
                ),
                Trace.Sunburst.Sector(
                  value = 5,
                  id = "bagel",
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
