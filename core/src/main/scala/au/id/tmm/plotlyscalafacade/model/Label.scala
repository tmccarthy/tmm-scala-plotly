package au.id.tmm.plotlyscalafacade.model

abstract class Label private[model] () extends Product {

  def bgcolor: Color
  def bordercolor: Color
  def font: Partial[Font]

}

object Label {

  final case class Simple(
    bgcolor: Color,
    bordercolor: Color,
    font: Partial[Font],
  ) extends Label

}
