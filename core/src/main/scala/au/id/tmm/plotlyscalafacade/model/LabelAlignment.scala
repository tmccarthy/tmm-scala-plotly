package au.id.tmm.plotlyscalafacade.model

object LabelAlignment {

  sealed abstract class Horizontal(val asString: String) extends JSEnum

  object Horizontal {
    case object Left  extends Horizontal("left")
    case object Right extends Horizontal("right")
    case object Auto  extends Horizontal("auto")
  }

  sealed abstract class Vertical(val asString: String) extends JSEnum

  object Vertical {
    case object Top  extends Vertical("top")
    case object Middle extends Vertical("middle")
    case object Bottom  extends Vertical("bottom")
  }

}

