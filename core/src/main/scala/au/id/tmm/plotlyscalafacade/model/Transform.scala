package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{JSEnum, OneOrArrayOf}

final case class Transform(
  `type`: Transform.Type,
  enabled: Boolean,
  target: OneOrArrayOf[AxisPosition],
  operation: String,
  aggregations: Seq[Transform.Aggregation],
  preservegaps: Boolean,
  groups: OneOrArrayOf[AxisPosition],
  nameformat: String,
  styles: Seq[Transform.Style],
  value: Datum,
  order: Transform.Order,
)

object Transform {

  sealed abstract class Type(val asString: String) extends JSEnum

  object Type {
    case object Aggregate extends Type("aggregate")
    case object Filter    extends Type("filter")
    case object Groupby   extends Type("groupby")
    case object Sort      extends Type("sort")
  }

  final case class Aggregation(
    target: String,
    func: Option[Aggregation.Function],
    funcmode: Option[Aggregation.FunctionMode],
    enabled: Option[Boolean],
  )

  object Aggregation {
    sealed abstract class Function(val asString: String) extends JSEnum

    object Function {
      case object Count  extends Function("count")
      case object Sum    extends Function("sum")
      case object Avg    extends Function("avg")
      case object Median extends Function("median")
      case object Mode   extends Function("mode")
      case object Rms    extends Function("rms")
      case object Stddev extends Function("stddev")
      case object Min    extends Function("min")
      case object Max    extends Function("max")
      case object First  extends Function("first")
      case object Last   extends Function("last")
    }

    sealed abstract class FunctionMode(val asString: String) extends JSEnum

    object FunctionMode {
      case object Sample     extends FunctionMode("sample")
      case object Population extends FunctionMode("population")
    }
  }

  final case class Style(
    target: OneOrArrayOf[AxisPosition],
    value: Partial[PlotData],
  )

  sealed abstract class Order(val asString: String) extends JSEnum

  object Order {
    case object Ascending  extends Order("ascending")
    case object Descending extends Order("descending")
  }

}