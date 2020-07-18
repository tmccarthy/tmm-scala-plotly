package au.id.tmm.plotlyscalafacade

import java.io.{BufferedInputStream, ByteArrayOutputStream}
import java.nio.charset.Charset
import java.nio.file.Files

import io.circe.syntax.EncoderOps

import scala.annotation.tailrec

object Plotting {

  def openInBrowser(plot: Plot, plotlyVersion: PlotlyVersion = PlotlyVersion.Latest): Unit = {
    val path = Files.createTempFile(
      inferTitleFor(plot).getOrElse("plot"),
      ".html",
    )

    Files.writeString(path, newPlotHtmlPage(plot, plotlyVersion))

    java.awt.Desktop.getDesktop.browse(path.toUri)
  }

  private def inferTitleFor(plot: Plot): Option[String] = {
    def fromLayoutTitle =
      for {
        layout <- plot.layout
        title <- layout.title
        titleText <- title.text
      } yield titleText

    fromLayoutTitle.map(_.replaceAll("""\s""", "_").replaceAll("""\W""", ""))
  }

  def newPlotHtmlPage(plot: Plot, plotlyVersion: PlotlyVersion = PlotlyVersion.Latest): String =
    templatedResource(
      resourceName = "newPlotHtmlPage.html",
      "plot"         -> plot.asJson.noSpaces,
      "plotlyCdnUri" -> plotlyVersion.cdnUri.toString,
    )

  def newPlotInlineHtmlFragment(divId: String, plot: Plot): String =
    templatedResource(
      resourceName = "newPlotInlineHtmlFragment.html",
      "divId" -> divId,
      "plot"  -> plot.asJson.noSpaces,
    )

  /**
    * Assumes that the `Plotly` javascript library is available
    */
  def newPlotJSFragment(divId: String, plot: Plot): String =
    templatedResource(
      resourceName = "newPlotFragment.js",
      "divId" -> divId,
      "plot"  -> plot.asJson.noSpaces,
    )

  private def templatedResource(
    resourceName: String,
    templated: (String, String)*,
  ): String = {
    val template = readToString(resourceName)

    applyTempatedTo(template, templated.toList)
  }

  private def readToString(resourceName: String): String = {
    var is: BufferedInputStream = null

    try {
      is = new BufferedInputStream(getClass.getResourceAsStream(resourceName))
      val bytes = new ByteArrayOutputStream()

      val buffer = new Array[Byte](1_000)

      var lengthSoFar = 0

      while ((lengthSoFar = is.read(buffer)) != -1) {
        bytes.write(buffer, 0, lengthSoFar)
      }

      new String(bytes.toByteArray, Charset.forName("UTF-8"))
    } finally {
      is.close()
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
