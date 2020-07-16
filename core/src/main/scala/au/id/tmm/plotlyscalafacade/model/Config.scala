package au.id.tmm.plotlyscalafacade.model

import java.net.URI
import java.util.Locale

import au.id.tmm.plotlyscalafacade.model.utilities.{BooleanOr, FalseOr, FlagList, JSEnum}
import cats.instances.int.catsKernelStdOrderForInt
import cats.kernel.Order
import io.circe.Encoder

final case class Config(
  toImageButtonOptions: Partial[Config.ToImageButtonOptions],
  staticPlot: Boolean,
  plotlyServerURL: URI,
  editable: Boolean,
  edits: Partial[Config.Edits],
  autosizable: Boolean,
  queueLength: Int,
  fillFrame: Boolean,
  frameMargins: Number,
  scrollZoom: Boolean,
  doubleClick: FalseOr[FlagList[Config.DoubleClickFlag]],
  showTips: Boolean,
  showAxisDragHandles: Boolean,
  showAxisRangeEntryBoxes: Boolean,
  showLink: Boolean,
  sendData: Boolean,
  linkText: String,
  showSources: Boolean,
  displayModeBar: BooleanOr[Config.DisplayModeBar],
  showSendToCloud: Boolean,
  showEditInChartStudio: Boolean,
  modeBarButtonsToRemove: Seq[Config.ModeBarDefaultButtons],
  modeBarButtonsToAdd: Config.ModeBarButtons,
  modeBarButtons: FalseOr[Config.ModeBarButtonGroups],
  displaylogo: Boolean,
  plotGlPixelRatio: Number,
  setBackground: Config.SetBackground,
  topojsonURL: URI,
  mapboxAccessToken: String,
  logging: Boolean,
  globalTransforms: Seq[Transform],
  locale: Locale,
  responsive: Boolean,
)

object Config {

  final case class ToImageButtonOptions(
    filename: String,
    scale: Number,
    format: ToImageButtonOptions.Format,
    height: Number,
    width: Number,
  )

  object ToImageButtonOptions {
    sealed abstract class Format(val asString: String) extends JSEnum

    object Format {
      case object Png  extends Format("png")
      case object Svg  extends Format("svg")
      case object Jpeg extends Format("jpeg")
      case object Webp extends Format("webp")
    }
  }

  final case class Edits(
    annotationPosition: Boolean,
    annotationTail: Boolean,
    annotationText: Boolean,
    axisTitleText: Boolean,
    colorbarPosition: Boolean,
    colorbarTitleText: Boolean,
    legendPosition: Boolean,
    legendText: Boolean,
    shapePosition: Boolean,
    titleText: Boolean,
  )

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
      case Opaque      => Encoder[String].apply("opaque")
      case Transparent => Encoder[String].apply("transparent")
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

  final case class ModeBarButtonGroups(
    groups: Seq[ModeBarButtons],
  )

}
