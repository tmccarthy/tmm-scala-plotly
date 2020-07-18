package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{FalseOr, JSEnum, OneOrArrayOf}

final case class Scene(
  bgcolor: Option[Color],
  camera: Option[Scene.Camera],
  domain: Option[Domain],
  aspectmode: Option[Scene.AspectMode],
  aspectratio: Option[Scene.Point],
  xaxis: Option[SceneAxis],
  yaxis: Option[SceneAxis],
  zaxis: Option[SceneAxis],
  dragmode: Option[FalseOr[Scene.DragMode]],
  hovermode: Option[FalseOr[Scene.HoverMode]],
  annotations: Option[OneOrArrayOf[Annotations]],
  captureevents: Option[Boolean],
)

object Scene {
  final case class Camera(
    up: Point,
    center: Point,
    eye: Point,
  )

  final case class Point(
    x: Number,
    y: Number,
    z: Number,
  )

  sealed abstract class AspectMode(val asString: String) extends JSEnum

  object AspectMode {
    case object Auto   extends AspectMode("auto")
    case object Cube   extends AspectMode("cube")
    case object Data   extends AspectMode("data")
    case object Manual extends AspectMode("manual")
  }

  sealed abstract class DragMode(val asString: String)

  object DragMode {
    case object Orbit     extends DragMode("orbit")
    case object Turntable extends DragMode("turntable")
    case object Zoom      extends DragMode("zoom")
    case object Pan       extends DragMode("pan")
  }

  sealed abstract class HoverMode(val asString: String) extends JSEnum

  object HoverMode {
    case object Closest extends HoverMode("closest")
  }
}
