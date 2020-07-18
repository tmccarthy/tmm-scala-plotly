package au.id.tmm.plotlyscalafacade.model

import io.circe.Encoder

final case class Domain(
  x: Option[NumberArray] = None,
  y: Option[NumberArray] = None,
  row: Option[Number] = None,
  column: Option[Number] = None,
)

object Domain {
  implicit val encoder: Encoder[Domain] = Encoder.forProduct4(
    "x",
    "y",
    "row",
    "column",
  )(d =>
    (
      d.x,
      d.y,
      d.row,
      d.column,
    ),
  )
}
