package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{FalseOr, JSEnum, OneOrArrayOf}
import io.circe.Encoder

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

  object Camera {
    implicit val encoder: Encoder[Camera] = Encoder.forProduct3("up", "center", "eye")(c => (c.up, c.center, c.eye))
  }

  final case class Point(
    x: Number,
    y: Number,
    z: Number,
  )

  object Point {
    implicit val encoder: Encoder[Point] = Encoder.forProduct3("x", "y", "z")(p => (p.x, p.y, p.z))
  }

  sealed abstract class AspectMode(val asString: String) extends JSEnum

  object AspectMode {
    case object Auto   extends AspectMode("auto")
    case object Cube   extends AspectMode("cube")
    case object Data   extends AspectMode("data")
    case object Manual extends AspectMode("manual")
  }

  sealed abstract class DragMode(val asString: String) extends JSEnum

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

  implicit val encoder: Encoder[Scene] = Encoder.forProduct12(
    "bgcolor",
    "camera",
    "domain",
    "aspectmode",
    "aspectratio",
    "xaxis",
    "yaxis",
    "zaxis",
    "dragmode",
    "hovermode",
    "annotations",
    "captureevents",
  )(s =>
    (
      s.bgcolor,
      s.camera,
      s.domain,
      s.aspectmode,
      s.aspectratio,
      s.xaxis,
      s.yaxis,
      s.zaxis,
      s.dragmode,
      s.hovermode,
      s.annotations,
      s.captureevents,
    ),
  )
}
