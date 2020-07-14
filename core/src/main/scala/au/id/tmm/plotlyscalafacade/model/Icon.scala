package au.id.tmm.plotlyscalafacade.model

import io.circe.Encoder

final case class Icon(
  height: Option[Number],
  width: Option[Number],
  ascent: Option[Number],
  descent: Option[Number],
  name: Option[String],
  path: Option[String],
  svg: Option[String],
  transform: Option[String],
)

object Icon {
  implicit val encoder: Encoder[Icon] = Encoder.forProduct8(
    "height",
    "width",
    "ascent",
    "descent",
    "name",
    "path",
    "svg",
    "transform",
  )(i =>
    (
      i.height,
      i.width,
      i.ascent,
      i.descent,
      i.name,
      i.path,
      i.svg,
      i.transform,
    ),
  )
}
