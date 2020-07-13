package au.id.tmm.plotlyscalafacade.model

final case class Legend(
  bgcolor: Color,
  bordercolor: Color,
  font: Partial[Font],
  traceorder: Legend.TraceOrder,
  x: Number,
  y: Number,
  borderwidth: Number,
  orientation: Legend.Orientation,
  tracegroupgap: Number,
  xanchor: Anchor.X,
  yanchor: Anchor.Y,
) extends Label

object Legend {
  sealed abstract class TraceOrder(val asString: String) extends JSEnum

  object TraceOrder {
    case object Grouped  extends TraceOrder("grouped")
    case object Normal   extends TraceOrder("normal")
    case object Reversed extends TraceOrder("reversed")
  }

  sealed abstract class Orientation(val asString: String) extends JSEnum

  object Orientation {
    case object Vertical   extends Orientation("v")
    case object Horizontal extends Orientation("h")
  }
}
