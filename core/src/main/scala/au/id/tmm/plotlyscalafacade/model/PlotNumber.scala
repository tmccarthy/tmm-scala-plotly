package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.Arg
import io.circe.Encoder

final case class PlotNumber(
  valueformat: Arg[String] = Arg.Undefined,
  font: Arg[Font] = Arg.Undefined,
  prefix: Arg[String] = Arg.Undefined,
  suffix: Arg[String] = Arg.Undefined,
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
