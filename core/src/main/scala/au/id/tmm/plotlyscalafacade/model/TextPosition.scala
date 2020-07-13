package au.id.tmm.plotlyscalafacade.model

sealed abstract class TextPosition(val asString: String) extends JSEnum

object TextPosition {
  case object TopLeft      extends TextPosition("top left")
  case object TopCenter    extends TextPosition("top center")
  case object TopRight     extends TextPosition("top right")
  case object MiddleCenter extends TextPosition("middle center")
  case object BottomLeft   extends TextPosition("bottom left")
  case object BottomCenter extends TextPosition("bottom center")
  case object BottomRight  extends TextPosition("bottom right")
}
