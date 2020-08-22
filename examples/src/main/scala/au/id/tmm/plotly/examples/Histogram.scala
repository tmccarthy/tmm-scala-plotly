package au.id.tmm.plotly.examples

import au.id.tmm.plotly.model.{Layout, Trace}
import au.id.tmm.plotly.syntax._
import au.id.tmm.plotly.{Plot, Plotting}

import scala.collection.immutable.ArraySeq
import scala.util.Random

object Histogram {

  def main(args: Array[String]): Unit = {

    val data: ArraySeq[Int] = ArraySeq.fill(10_000) {
      val diceRoll1 = Random.nextInt(6) + 1
      val diceRoll2 = Random.nextInt(6) + 1

      diceRoll1 + diceRoll2
    }

    val plot = Plot(
      data = ArraySeq(
        Trace(
          traceType = Trace.Type.Histogram,
          x = data,
        ),
      ),
      layout = Layout(
        title = Layout.Title(
          text = "Two dice roll",
        ),
      ),
    )

    Plotting.openInBrowser(plot)
  }

}
