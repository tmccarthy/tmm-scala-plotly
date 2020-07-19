package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.OptArg
import io.circe.Encoder

final case class PlotNumber(
  valueformat: OptArg[String] = OptArg.Undefined,
  font: OptArg[Font] = OptArg.Undefined,
  prefix: OptArg[String] = OptArg.Undefined,
  suffix: OptArg[String] = OptArg.Undefined,
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
