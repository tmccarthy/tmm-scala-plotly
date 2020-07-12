package au.id.tmm.plotlyscalafacade.model

final case class Coordinates(
  topLeft: Coordinates.Point,
  topRight: Coordinates.Point,
  bottomRight: Coordinates.Point,
  bottomLeft: Coordinates.Point,
)

object Coordinates {
  final case class Point(longitude: Number, latitude: Number)
}
