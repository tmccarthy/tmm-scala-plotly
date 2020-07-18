package au.id.tmm.plotlyscalafacade

import io.circe.Encoder

// TODO should this sit in the model package?
final case class Plot(
  data: Seq[model.Trace] = Seq.empty,
  layout: Option[model.Layout] = None,
  config: Option[model.Config] = None,
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
