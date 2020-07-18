package au.id.tmm.plotlyscalafacade.examples

import au.id.tmm.plotlyscalafacade.{Plot, Plotting}
import au.id.tmm.plotlyscalafacade.model.{DataArray, Layout, Trace}

import scala.collection.immutable.ArraySeq
import scala.util.Random

object Histogram {

  def main(args: Array[String]): Unit = {

    val data = ArraySeq.fill(1_000) {
      val diceRoll1 = Random.nextInt(6) + 1
      val diceRoll2 = Random.nextInt(6) + 1

      diceRoll1 + diceRoll2
    }

    val plot = Plot(
      data = ArraySeq(
        Trace(
          `type` = Some(Trace.Type.Histogram),
          x = Some(DataArray.OfInts(data)),
        ),
      ),
      layout = Some(
        Layout(
          title = Some(
            Layout.Title(
              text = Some("Two dice roll"),
            ),
          ),
        ),
      ),
    )

    Plotting.openInBrowser(plot)
  }

}
