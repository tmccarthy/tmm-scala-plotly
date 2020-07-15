package au.id.tmm.plotlyscalafacade.model

// TODO isn't this a Datum?
sealed trait AxisPosition

object AxisPosition {

  final case class AsNumber(number: Number)                 extends AxisPosition
  final case class AsLog(logArgument: Number)               extends AxisPosition
  final case class AsDate(date: Date)                       extends AxisPosition
  final case class AsCategory(categoryIndex: CategoryIndex) extends AxisPosition

}
