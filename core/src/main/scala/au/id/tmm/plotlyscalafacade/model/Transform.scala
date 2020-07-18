package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{JSEnum, OneOrArrayOf}
import io.circe.Encoder

final case class Transform(
  `type`: Option[Transform.Type] = None,
  enabled: Option[Boolean] = None,
  target: Option[OneOrArrayOf[AxisPosition]] = None,
  operation: Option[String] = None,
  aggregations: Option[Seq[Transform.Aggregation]] = None,
  preservegaps: Option[Boolean] = None,
  groups: Option[OneOrArrayOf[AxisPosition]] = None,
  nameformat: Option[String] = None,
  styles: Option[Seq[Transform.Style]] = None,
  value: Option[Datum] = None,
  order: Option[Transform.Order] = None,
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
    func: Option[Aggregation.Function] = None,
    funcmode: Option[Aggregation.FunctionMode] = None,
    enabled: Option[Boolean] = None,
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

    implicit val encoder: Encoder[Aggregation] = Encoder.forProduct4(
      "target",
      "func",
      "funcmode",
      "enabled",
    )(a =>
      (
        a.target,
        a.func,
        a.funcmode,
        a.enabled,
      ),
    )
  }

  final case class Style(
    target: OneOrArrayOf[AxisPosition],
    value: Trace,
  )

  object Style {
    implicit val encoder: Encoder[Style] = Encoder.forProduct2("target", "value")(s => (s.target, s.value))
  }

  sealed abstract class Order(val asString: String) extends JSEnum

  object Order {
    case object Ascending  extends Order("ascending")
    case object Descending extends Order("descending")
  }

  implicit val encoder: Encoder[Transform] = Encoder.forProduct11(
    "type",
    "enabled",
    "target",
    "operation",
    "aggregations",
    "preservegaps",
    "groups",
    "nameformat",
    "styles",
    "value",
    "order",
  )(t =>
    (
      t.`type`,
      t.enabled,
      t.target,
      t.operation,
      t.aggregations,
      t.preservegaps,
      t.groups,
      t.nameformat,
      t.styles,
      t.value,
      t.order,
    ),
  )

}
