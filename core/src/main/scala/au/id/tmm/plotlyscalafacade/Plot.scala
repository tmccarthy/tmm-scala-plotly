package au.id.tmm.plotlyscalafacade

import au.id.tmm.plotlyscalafacade.model.utilities.OptArg
import io.circe.Encoder

final case class Plot(
  data: Seq[model.Trace] = Seq.empty,
  layout: OptArg[model.Layout] = OptArg.Undefined,
  config: OptArg[model.Config] = OptArg.Undefined,
)

object Plot {
  implicit val encoder: Encoder[Plot] = Encoder.forProduct3(
    "data",
    "layout",
    "config",
  )(p =>
    (
      p.data,
      p.layout,
      p.config,
    ),
  )
}
