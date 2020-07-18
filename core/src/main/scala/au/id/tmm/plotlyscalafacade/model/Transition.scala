package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum
import io.circe.Encoder

final case class Transition(
  duration: Option[Number] = None,
  easing: Option[Transition.Easing] = None,
  ordering: Option[Transition.Ordering] = None,
)

object Transition {
  sealed abstract class Easing(val asString: String) extends JSEnum

  object Easing {
    case object Linear       extends Easing("linear")
    case object Quad         extends Easing("quad")
    case object Cubic        extends Easing("cubic")
    case object Sin          extends Easing("sin")
    case object Exp          extends Easing("exp")
    case object Circle       extends Easing("circle")
    case object Elastic      extends Easing("elastic")
    case object Back         extends Easing("back")
    case object Bounce       extends Easing("bounce")
    case object LinearIn     extends Easing("linear-in")
    case object QuadIn       extends Easing("quad-in")
    case object CubicIn      extends Easing("cubic-in")
    case object SinIn        extends Easing("sin-in")
    case object ExpIn        extends Easing("exp-in")
    case object CircleIn     extends Easing("circle-in")
    case object ElasticIn    extends Easing("elastic-in")
    case object BackIn       extends Easing("back-in")
    case object BounceIn     extends Easing("bounce-in")
    case object LinearOut    extends Easing("linear-out")
    case object QuadOut      extends Easing("quad-out")
    case object CubicOut     extends Easing("cubic-out")
    case object SinOut       extends Easing("sin-out")
    case object ExpOut       extends Easing("exp-out")
    case object CircleOut    extends Easing("circle-out")
    case object ElasticOut   extends Easing("elastic-out")
    case object BackOut      extends Easing("back-out")
    case object BounceOut    extends Easing("bounce-out")
    case object LinearInOut  extends Easing("linear-in-out")
    case object QuadInOut    extends Easing("quad-in-out")
    case object CubicInOut   extends Easing("cubic-in-out")
    case object SinInOut     extends Easing("sin-in-out")
    case object ExpInOut     extends Easing("exp-in-out")
    case object CircleInOut  extends Easing("circle-in-out")
    case object ElasticInOut extends Easing("elastic-in-out")
    case object BackInOut    extends Easing("back-in-out")
    case object BounceInOut  extends Easing("bounce-in-out")
  }

  sealed abstract class Ordering(val asString: String) extends JSEnum

  object Ordering {
    case object LayoutFirst extends Ordering("layout first")
    case object TracesFirst extends Ordering("traces first")
  }

  implicit val encoder: Encoder[Transition] = Encoder.forProduct3(
    "duration",
    "easing",
    "ordering",
  )(t =>
    (
      t.duration,
      t.easing,
      t.ordering,
    ),
  )
}
