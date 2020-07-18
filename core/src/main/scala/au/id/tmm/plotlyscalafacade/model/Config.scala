package au.id.tmm.plotlyscalafacade.model

import java.net.URI
import java.util.Locale

import au.id.tmm.plotlyscalafacade.model.utilities.{Arg, BooleanOr, FalseOr, FlagList, JSEnum}
import cats.instances.int.catsKernelStdOrderForInt
import cats.kernel.Order
import io.circe.syntax._
import io.circe.{Encoder, Json}

final case class Config(
  toImageButtonOptions: Arg[Config.ToImageButtonOptions] = Arg.Undefined,
  staticPlot: Arg[Boolean] = Arg.Undefined,
  plotlyServerURL: Arg[URI] = Arg.Undefined,
  editable: Arg[Boolean] = Arg.Undefined,
  edits: Arg[Config.Edits] = Arg.Undefined,
  autosizable: Arg[Boolean] = Arg.Undefined,
  queueLength: Arg[Int] = Arg.Undefined,
  fillFrame: Arg[Boolean] = Arg.Undefined,
  frameMargins: Arg[Number] = Arg.Undefined,
  scrollZoom: Arg[Boolean] = Arg.Undefined,
  doubleClick: Arg[FalseOr[FlagList[Config.DoubleClickFlag]]] = Arg.Undefined,
  showTips: Arg[Boolean] = Arg.Undefined,
  showAxisDragHandles: Arg[Boolean] = Arg.Undefined,
  showAxisRangeEntryBoxes: Arg[Boolean] = Arg.Undefined,
  showLink: Arg[Boolean] = Arg.Undefined,
  sendData: Arg[Boolean] = Arg.Undefined,
  linkText: Arg[String] = Arg.Undefined,
  showSources: Arg[Boolean] = Arg.Undefined,
  displayModeBar: Arg[BooleanOr[Config.DisplayModeBar]] = Arg.Undefined,
  showSendToCloud: Arg[Boolean] = Arg.Undefined,
  showEditInChartStudio: Arg[Boolean] = Arg.Undefined,
  modeBarButtonsToRemove: Arg[Seq[Config.ModeBarDefaultButtons]] = Arg.Undefined,
  modeBarButtonsToAdd: Arg[Config.ModeBarButtons] = Arg.Undefined,
  modeBarButtons: Arg[FalseOr[Config.ModeBarButtonGroups]] = Arg.Undefined,
  displaylogo: Arg[Boolean] = Arg.Undefined,
  plotGlPixelRatio: Arg[Number] = Arg.Undefined,
  setBackground: Arg[Config.SetBackground] = Arg.Undefined,
  topojsonURL: Arg[URI] = Arg.Undefined,
  mapboxAccessToken: Arg[String] = Arg.Undefined,
  logging: Arg[Boolean] = Arg.Undefined,
  globalTransforms: Arg[Seq[Transform]] = Arg.Undefined,
  locale: Arg[Locale] = Arg.Undefined,
  responsive: Arg[Boolean] = Arg.Undefined,
)

object Config {

  final case class ToImageButtonOptions(
    filename: Arg[String] = Arg.Undefined,
    scale: Arg[Number] = Arg.Undefined,
    format: Arg[ToImageButtonOptions.Format] = Arg.Undefined,
    height: Arg[Number] = Arg.Undefined,
    width: Arg[Number] = Arg.Undefined,
  )

  object ToImageButtonOptions {
    sealed abstract class Format(val asString: String) extends JSEnum

    object Format {
      case object Png  extends Format("png")
      case object Svg  extends Format("svg")
      case object Jpeg extends Format("jpeg")
      case object Webp extends Format("webp")
    }

    implicit val encoder: Encoder[ToImageButtonOptions] = Encoder.forProduct5(
      "filename",
      "scale",
      "format",
      "height",
      "width",
    )(o =>
      (
        o.filename,
        o.scale,
        o.format,
        o.height,
        o.width,
      ),
    )
  }

  final case class Edits(
    annotationPosition: Arg[Boolean] = Arg.Undefined,
    annotationTail: Arg[Boolean] = Arg.Undefined,
    annotationText: Arg[Boolean] = Arg.Undefined,
    axisTitleText: Arg[Boolean] = Arg.Undefined,
    colorbarPosition: Arg[Boolean] = Arg.Undefined,
    colorbarTitleText: Arg[Boolean] = Arg.Undefined,
    legendPosition: Arg[Boolean] = Arg.Undefined,
    legendText: Arg[Boolean] = Arg.Undefined,
    shapePosition: Arg[Boolean] = Arg.Undefined,
    titleText: Arg[Boolean] = Arg.Undefined,
  )

  object Edits {
    implicit val encoder: Encoder[Edits] = Encoder.forProduct10(
      "annotationPosition",
      "annotationTail",
      "annotationText",
      "axisTitleText",
      "colorbarPosition",
      "colorbarTitleText",
      "legendPosition",
      "legendText",
      "shapePosition",
      "titleText",
    )(e =>
      (
        e.annotationPosition,
        e.annotationTail,
        e.annotationText,
        e.axisTitleText,
        e.colorbarPosition,
        e.colorbarTitleText,
        e.legendPosition,
        e.legendText,
        e.shapePosition,
        e.titleText,
      ),
    )
  }

  sealed abstract class DoubleClickFlag(val asString: String) extends JSEnum

  object DoubleClickFlag {
    case object Reset    extends DoubleClickFlag("reset")
    case object Autosize extends DoubleClickFlag("autosize")

    implicit val order: Order[DoubleClickFlag] = Order.by[DoubleClickFlag, Int] {
      case Reset    => 0
      case Autosize => 1
    }
  }

  sealed abstract class DisplayModeBar(val asString: String) extends JSEnum

  object DisplayModeBar {
    case object Hover extends DisplayModeBar("hover")
  }

  sealed trait SetBackground

  object SetBackground {
    case object Opaque      extends SetBackground
    case object Transparent extends SetBackground

    /**
      * Not supported
      */
    sealed trait Fn extends SetBackground

    implicit val encoder: Encoder[SetBackground] = {
      case Opaque      => "opaque".asJson
      case Transparent => "transparent".asJson
    }
  }

  sealed abstract class ModeBarDefaultButtons(val asString: String) extends JSEnum

  object ModeBarDefaultButtons {
    case object Lasso2d               extends ModeBarDefaultButtons("lasso2d")
    case object Select2d              extends ModeBarDefaultButtons("select2d")
    case object SendDataToCloud       extends ModeBarDefaultButtons("sendDataToCloud")
    case object Zoom2d                extends ModeBarDefaultButtons("zoom2d")
    case object Pan2d                 extends ModeBarDefaultButtons("pan2d")
    case object ZoomIn2d              extends ModeBarDefaultButtons("zoomIn2d")
    case object ZoomOut2d             extends ModeBarDefaultButtons("zoomOut2d")
    case object AutoScale2d           extends ModeBarDefaultButtons("autoScale2d")
    case object ResetScale2d          extends ModeBarDefaultButtons("resetScale2d")
    case object HoverClosestCartesian extends ModeBarDefaultButtons("hoverClosestCartesian")
    case object HoverCompareCartesian extends ModeBarDefaultButtons("hoverCompareCartesian")
    case object Zoom3d                extends ModeBarDefaultButtons("zoom3d")
    case object Pan3d                 extends ModeBarDefaultButtons("pan3d")
    case object OrbitRotation         extends ModeBarDefaultButtons("orbitRotation")
    case object TableRotation         extends ModeBarDefaultButtons("tableRotation")
    case object ResetCameraDefault3d  extends ModeBarDefaultButtons("resetCameraDefault3d")
    case object ResetCameraLastSave3d extends ModeBarDefaultButtons("resetCameraLastSave3d")
    case object HoverClosest3d        extends ModeBarDefaultButtons("hoverClosest3d")
    case object ZoomInGeo             extends ModeBarDefaultButtons("zoomInGeo")
    case object ZoomOutGeo            extends ModeBarDefaultButtons("zoomOutGeo")
    case object ResetGeo              extends ModeBarDefaultButtons("resetGeo")
    case object HoverClosestGeo       extends ModeBarDefaultButtons("hoverClosestGeo")
    case object HoverClosestGl2d      extends ModeBarDefaultButtons("hoverClosestGl2d")
    case object HoverClosestPie       extends ModeBarDefaultButtons("hoverClosestPie")
    case object ToggleHover           extends ModeBarDefaultButtons("toggleHover")
    case object ToImage               extends ModeBarDefaultButtons("toImage")
    case object ResetViews            extends ModeBarDefaultButtons("resetViews")
    case object ToggleSpikelines      extends ModeBarDefaultButtons("toggleSpikelines")
  }

  final case class ModeBarButtons(
    defaultButtons: Seq[ModeBarDefaultButtons],
    buttons: Seq[ModeBarButton],
  )

  object ModeBarButtons {
    implicit val encoder: Encoder[ModeBarButtons] =
      b => (b.buttons.map(_.asJson) ++ b.defaultButtons.map(_.asJson)).asJson
  }

  final case class ModeBarButtonGroups(
    groups: Seq[ModeBarButtons],
  )

  object ModeBarButtonGroups {
    implicit val encoder: Encoder[ModeBarButtonGroups] = Encoder[Seq[ModeBarButtons]].contramap(_.groups)
  }

  implicit val encoder: Encoder[Config] = c =>
    Json.obj(
      "toImageButtonOptions" := c.toImageButtonOptions,
      "staticPlot" := c.staticPlot,
      "plotlyServerURL" := c.plotlyServerURL,
      "editable" := c.editable,
      "edits" := c.edits,
      "autosizable" := c.autosizable,
      "queueLength" := c.queueLength,
      "fillFrame" := c.fillFrame,
      "frameMargins" := c.frameMargins,
      "scrollZoom" := c.scrollZoom,
      "doubleClick" := c.doubleClick,
      "showTips" := c.showTips,
      "showAxisDragHandles" := c.showAxisDragHandles,
      "showAxisRangeEntryBoxes" := c.showAxisRangeEntryBoxes,
      "showLink" := c.showLink,
      "sendData" := c.sendData,
      "linkText" := c.linkText,
      "showSources" := c.showSources,
      "displayModeBar" := c.displayModeBar,
      "showSendToCloud" := c.showSendToCloud,
      "showEditInChartStudio" := c.showEditInChartStudio,
      "modeBarButtonsToRemove" := c.modeBarButtonsToRemove,
      "modeBarButtonsToAdd" := c.modeBarButtonsToAdd,
      "modeBarButtons" := c.modeBarButtons,
      "displaylogo" := c.displaylogo,
      "plotGlPixelRatio" := c.plotGlPixelRatio,
      "setBackground" := c.setBackground,
      "topojsonURL" := c.topojsonURL,
      "mapboxAccessToken" := c.mapboxAccessToken,
      "logging" := c.logging,
      "globalTransforms" := c.globalTransforms,
      "locale" := c.locale,
      "responsive" := c.responsive,
    )
}
