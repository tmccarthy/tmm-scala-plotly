package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.Arg
import io.circe.Encoder

final case class Domain(
  x: Arg[NumberArray] = Arg.Undefined,
  y: Arg[NumberArray] = Arg.Undefined,
  row: Arg[Number] = Arg.Undefined,
  column: Arg[Number] = Arg.Undefined,
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
