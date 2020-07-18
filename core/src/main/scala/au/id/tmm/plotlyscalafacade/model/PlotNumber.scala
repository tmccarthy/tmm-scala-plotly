package au.id.tmm.plotlyscalafacade.model

import io.circe.Encoder

final case class PlotNumber(
  valueformat: String,
  font: Partial[Font],
  prefix: String,
  suffix: String,
)

object PlotNumber {
  implicit val encoder: Encoder[PlotNumber] = Encoder.forProduct4(
    "valueformat",
    "font",
    "prefix",
    "suffix",
  )(n =>
    (
      n.valueformat,
      n.font,
      n.prefix,
      n.suffix,
    ),
  )
}
