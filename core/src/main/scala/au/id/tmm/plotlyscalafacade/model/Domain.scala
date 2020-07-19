package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.OptArg
import io.circe.Encoder

final case class Domain(
  x: OptArg[Seq[Number]] = OptArg.Undefined,
  y: OptArg[Seq[Number]] = OptArg.Undefined,
  row: OptArg[Number] = OptArg.Undefined,
  column: OptArg[Number] = OptArg.Undefined,
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
