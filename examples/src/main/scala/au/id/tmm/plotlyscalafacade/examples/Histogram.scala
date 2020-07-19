package au.id.tmm.plotlyscalafacade.examples

import au.id.tmm.plotlyscalafacade.model.utilities.OptArg
import au.id.tmm.plotlyscalafacade.{Plot, Plotting}
import au.id.tmm.plotlyscalafacade.model.{DataArray, Layout, Trace}

import scala.collection.immutable.ArraySeq
import scala.util.Random

object Histogram {

  def main(args: Array[String]): Unit = {

    val data = ArraySeq.fill(10_000) {
      val diceRoll1 = Random.nextInt(6) + 1
      val diceRoll2 = Random.nextInt(6) + 1

      diceRoll1 + diceRoll2
    }

    val plot = Plot(
      data = ArraySeq(
        Trace(
          `type` = OptArg.Of(Trace.Type.Histogram),
          x = OptArg.Of(DataArray.OfInts(data)),
        ),
      ),
      layout = OptArg.Of(
        Layout(
          title = OptArg.Of(
            Layout.Title(
              text = OptArg.Of("Two dice roll"),
            ),
          ),
        ),
      ),
    )

    Plotting.openInBrowser(plot)
  }

}
