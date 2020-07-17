package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum

import scala.collection.immutable.ArraySeq

sealed trait ColorScale

object ColorScale {

  final case class OfPallet(pallet: Pallet) extends ColorScale
  final case class OfMapping(mapping: Seq[(Number, Color)])

  object OfMapping {
    def apply(first: (Number, Color), second: (Number, Color), tail: (Number, Color)*): OfMapping =
      new OfMapping(ArraySeq(first, second).appendedAll(tail))
  }

  sealed abstract class Pallet(val asString: String) extends JSEnum

  object Pallet {
    case object Greys     extends Pallet("Greys")
    case object YlGnBu    extends Pallet("YlGnBu")
    case object Greens    extends Pallet("Greens")
    case object YlOrRd    extends Pallet("YlOrRd")
    case object BlueRed   extends Pallet("Bluered")
    case object RdBu      extends Pallet("RdBu")
    case object Reds      extends Pallet("Reds")
    case object Blues     extends Pallet("Blues")
    case object Picnic    extends Pallet("Picnic")
    case object Rainbow   extends Pallet("Rainbow")
    case object Portland  extends Pallet("Portland")
    case object Jet       extends Pallet("Jet")
    case object Hot       extends Pallet("Hot")
    case object BlackBody extends Pallet("Blackbody")
    case object Earth     extends Pallet("Earth")
    case object Electric  extends Pallet("Electric")
    case object Viridis   extends Pallet("Viridis")
    case object Cividis   extends Pallet("Cividis")
  }

}
