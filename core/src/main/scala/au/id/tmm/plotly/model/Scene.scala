package au.id.tmm.plotly.model

import au.id.tmm.plotly.model.utilities.{OptArg, FalseOr, JSEnum, OneOrArrayOf}
import io.circe.Encoder

final case class Scene(
  bgcolor: OptArg[Color] = OptArg.Undefined,
  camera: OptArg[Scene.Camera] = OptArg.Undefined,
  domain: OptArg[Domain] = OptArg.Undefined,
  aspectmode: OptArg[Scene.AspectMode] = OptArg.Undefined,
  aspectratio: OptArg[Scene.Point] = OptArg.Undefined,
  xaxis: OptArg[SceneAxis] = OptArg.Undefined,
  yaxis: OptArg[SceneAxis] = OptArg.Undefined,
  zaxis: OptArg[SceneAxis] = OptArg.Undefined,
  dragmode: OptArg[FalseOr[Scene.DragMode]] = OptArg.Undefined,
  hovermode: OptArg[FalseOr[Scene.HoverMode]] = OptArg.Undefined,
  annotations: OptArg[OneOrArrayOf[Annotations]] = OptArg.Undefined,
  captureevents: OptArg[Boolean] = OptArg.Undefined,
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
