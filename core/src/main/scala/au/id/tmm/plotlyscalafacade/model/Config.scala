package au.id.tmm.plotlyscalafacade.model

final case class Config(
  // TODO fill this in
)

object Config {

  final case class Edits(
    annotationPosition: Boolean,
    annotationTail: Boolean,
    annotationText: Boolean,
    axisTitleText: Boolean,
    colorbarPosition: Boolean,
    colorbarTitleText: Boolean,
    legendPosition: Boolean,
    legendText: Boolean,
    shapePosition: Boolean,
    titleText: Boolean,
  )

}
