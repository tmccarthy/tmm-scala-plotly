package au.id.tmm.plotlyscalafacade.model

final case class MapboxSymbol(
  icon: String,
  iconsize: Number,
  text: String,
  placement: MapboxSymbol.Placement,
  textfont: Partial[Font],
  textposition: TextPosition,
)

object MapboxSymbol {

  sealed abstract class Placement(val asString: String) extends JSEnum

  object Placement {
    case object Point      extends Placement("point")
    case object Line       extends Placement("line")
    case object LineCenter extends Placement("line-center")
  }

}
