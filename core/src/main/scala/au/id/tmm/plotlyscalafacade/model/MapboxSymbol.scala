package au.id.tmm.plotlyscalafacade.model

final case class MapboxSymbol(
  icon: String,
  iconsize: Number,
  text: String,
  placement: MapboxSymbol.Placement,
  textfont: Partial[Font],
  textposition: MapboxSymbol.TextPosition,
)

object MapboxSymbol {

  sealed abstract class Placement(val asString: String) extends JSEnum

  object Placement {
    case object Point      extends Placement("point")
    case object Line       extends Placement("line")
    case object LineCenter extends Placement("line-center")
  }

  sealed abstract class TextPosition(val asString: String) extends JSEnum

  object TextPosition {
    case object TopLeft      extends Placement("top left")
    case object TopCenter    extends Placement("top center")
    case object TopRight     extends Placement("top right")
    case object MiddleCenter extends Placement("middle center")
    case object BottomLeft   extends Placement("bottom left")
    case object BottomCenter extends Placement("bottom center")
    case object BottomRight  extends Placement("bottom right")
  }

}
