package au.id.tmm.plotlyscalafacade.model

final case class Scene(
  // TODO fill this in
)

object Scene {
  final case class Camera(
    up: Partial[Point],
    center: Partial[Point],
    eye: Partial[Point],
  )
}
