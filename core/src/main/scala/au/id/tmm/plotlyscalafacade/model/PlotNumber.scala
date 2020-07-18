package au.id.tmm.plotlyscalafacade.model

import io.circe.Encoder

final case class PlotNumber(
  valueformat: Option[String] = None,
  font: Option[Font] = None,
  prefix: Option[String] = None,
  suffix: Option[String] = None,
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
