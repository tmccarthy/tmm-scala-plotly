package au.id.tmm.plotlyscalafacade.model

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
