package au.id.tmm.plotlyscalafacade.model

final case class Delta(
  reference: Number,
  position: Delta.Position,
  relative: Boolean,
  valueformat: String,
  increasing: Delta.Symbol,
  decreasing: Delta.Symbol,
)

object Delta {
  sealed abstract class Position(val asString: String) extends JSEnum

  object Position {
    case object Top    extends Position("top")
    case object Bottom extends Position("bottom")
    case object Left   extends Position("left")
    case object Right  extends Position("right")
  }

  final case class Symbol(
    symbol: String,
    color: Color,
  )
}
