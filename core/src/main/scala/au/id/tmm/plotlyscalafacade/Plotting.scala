package au.id.tmm.plotlyscalafacade

import java.io.InputStream
import java.nio.file.Files

import io.circe.Printer
import io.circe.syntax.EncoderOps

import scala.annotation.tailrec
import scala.io.{Codec, Source}

object Plotting {

  private val circePrinter: Printer = Printer.noSpaces.copy(dropNullValues = true)

  def openInBrowser(plot: Plot, plotlyVersion: PlotlyVersion = PlotlyVersion.Latest): Unit = {
    val path = Files.createTempFile(
      inferTitleFor(plot).map(_.replaceAll("""\s""", "_").replaceAll("""\W""", "")).getOrElse("plot"),
      ".html",
    )

    Files.writeString(path, newPlotHtmlPage(plot, plotlyVersion))

    java.awt.Desktop.getDesktop.browse(path.toUri)
  }

  private def inferTitleFor(plot: Plot): Option[String] = {
    def fromLayoutTitle =
      for {
        layout    <- plot.layout.toOption
        title     <- layout.title.toOption
        titleText <- title.text.toOption
      } yield titleText

    fromLayoutTitle
  }

  def newPlotHtmlPage(plot: Plot, plotlyVersion: PlotlyVersion = PlotlyVersion.Latest): String =
    templatedResource(
      resourceName = "newPlotHtmlPage.html",
      "title"        -> inferTitleFor(plot).getOrElse("Plot"),
      "plot"         -> plot.asJson.printWith(circePrinter),
      "plotlyCdnUri" -> plotlyVersion.cdnUri.toString,
    )

  def newPlotInlineHtmlFragment(divId: String, plot: Plot): String =
    templatedResource(
      resourceName = "newPlotInlineHtmlFragment.html",
      "divId" -> divId,
      "plot"  -> plot.asJson.printWith(circePrinter),
    )

  /**
    * Assumes that the `Plotly` javascript library is available
    */
  def newPlotJSFragment(divId: String, plot: Plot): String =
    templatedResource(
      resourceName = "newPlotFragment.js",
      "divId" -> divId,
      "plot"  -> plot.asJson.printWith(circePrinter),
    )

  private def templatedResource(
    resourceName: String,
    templated: (String, String)*,
  ): String = {
    val template = readToString(resourceName)

    applyTempatedTo(template, templated.sortBy(_._1.length).reverse.toList)
  }

  private def readToString(resourceName: String): String = {
    var is: InputStream = null

    try {
      is = getClass.getResourceAsStream(resourceName)

      Source.fromInputStream(is)(Codec.UTF8).getLines().mkString("\n")
    } finally {
      if (is != null) is.close()
    }
  }

  @tailrec
  private def applyTempatedTo(template: String, templated: List[(String, String)]): String =
    templated match {
      case Nil => template
      case (templatedKey, templatedValue) :: remainingTemplated =>
        applyTempatedTo(template = template.replace("$" + templatedKey, templatedValue), remainingTemplated)
    }

}
