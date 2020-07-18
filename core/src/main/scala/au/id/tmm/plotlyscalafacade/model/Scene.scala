package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{FalseOr, JSEnum, OneOrArrayOf}

final case class Scene(
  bgcolor: Option[Color] = None,
  camera: Option[Scene.Camera] = None,
  domain: Option[Domain] = None,
  aspectmode: Option[Scene.AspectMode] = None,
  aspectratio: Option[Scene.Point] = None,
  xaxis: Option[SceneAxis] = None,
  yaxis: Option[SceneAxis] = None,
  zaxis: Option[SceneAxis] = None,
  dragmode: Option[FalseOr[Scene.DragMode]] = None,
  hovermode: Option[FalseOr[Scene.HoverMode]] = None,
  annotations: Option[OneOrArrayOf[Annotations]] = None,
  captureevents: Option[Boolean] = None,
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
