package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{FalseOr, JSEnum, OneOrArrayOf}

final case class Scene(
  bgcolor: Color,
  camera: Scene.Camera,
  domain: Domain,
  aspectmode: Scene.AspectMode,
  aspectratio: Scene.Point,
  xaxis: SceneAxis,
  yaxis: SceneAxis,
  zaxis: SceneAxis,
  dragmode: FalseOr[Scene.DragMode],
  hovermode: FalseOr[Scene.HoverMode],
  annotations: OneOrArrayOf[Annotations],
  captureevents: Boolean,
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
