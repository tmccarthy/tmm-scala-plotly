package au.id.tmm.plotly.model

import io.circe.Encoder
import io.circe.syntax.EncoderOps

// TODO replace this with Datum
sealed trait AxisPosition

object AxisPosition {

  final case class AsNumber(number: Datum.OfNumber)         extends AxisPosition
  final case class AsLog(logArgument: Datum.OfNumber)       extends AxisPosition
  final case class AsDate(date: Datum.OfDateTime)           extends AxisPosition
  final case class AsCategory(categoryIndex: CategoryIndex) extends AxisPosition

  implicit val encoder: Encoder[AxisPosition] = {
    case AsNumber(number)          => number.asJson
    case AsLog(logArgument)        => logArgument.asJson
    case AsDate(date)              => date.asJson
    case AsCategory(categoryIndex) => categoryIndex.asJson
  }

}
