package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{Arg, JSEnum}
import io.circe.Encoder

final case class Delta(
  reference: Arg[Number] = Arg.Undefined,
  position: Arg[Delta.Position] = Arg.Undefined,
  relative: Arg[Boolean] = Arg.Undefined,
  valueformat: Arg[String] = Arg.Undefined,
  increasing: Arg[Delta.Symbol] = Arg.Undefined,
  decreasing: Arg[Delta.Symbol] = Arg.Undefined,
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

  object Symbol {
    implicit val encoder: Encoder[Symbol] = Encoder.forProduct2("symbol", "color")(s => (s.symbol, s.color))
  }

  implicit val encoder: Encoder[Delta] = Encoder.forProduct6(
    "reference",
    "position",
    "relative",
    "valueformat",
    "increasing",
    "decreasing",
  )(d =>
    (
      d.reference,
      d.position,
      d.relative,
      d.valueformat,
      d.increasing,
      d.decreasing,
    ),
  )
}
