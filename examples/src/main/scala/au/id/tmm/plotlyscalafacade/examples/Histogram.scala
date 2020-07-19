package au.id.tmm.plotlyscalafacade.examples

import au.id.tmm.plotlyscalafacade.model.{Layout, Trace}
import au.id.tmm.plotlyscalafacade.syntax.all._
import au.id.tmm.plotlyscalafacade.{Plot, Plotting}

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
          `type` = Trace.Type.Histogram,
          x = data, // TODO why doesn't this conversion work?
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
