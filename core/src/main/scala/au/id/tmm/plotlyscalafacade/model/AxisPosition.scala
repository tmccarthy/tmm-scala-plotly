package au.id.tmm.plotlyscalafacade.model

import io.circe.Encoder
import io.circe.syntax.EncoderOps

sealed trait AxisPosition

object AxisPosition {

  final case class AsNumber(number: Number)                 extends AxisPosition
  final case class AsLog(logArgument: Number)               extends AxisPosition
  final case class AsDate(date: Date)                       extends AxisPosition
  final case class AsCategory(categoryIndex: CategoryIndex) extends AxisPosition

  implicit val encoder: Encoder[AxisPosition] = {
    case AsNumber(number)          => number.asJson
    case AsLog(logArgument)        => logArgument.asJson
    case AsDate(date)              => date.asJson
    case AsCategory(categoryIndex) => categoryIndex.asJson
  }

}
