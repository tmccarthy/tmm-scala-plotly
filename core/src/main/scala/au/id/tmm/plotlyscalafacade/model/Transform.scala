package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{Arg, JSEnum, OneOrArrayOf}
import io.circe.Encoder

final case class Transform(
  `type`: Arg[Transform.Type] = Arg.Undefined,
  enabled: Arg[Boolean] = Arg.Undefined,
  target: Arg[OneOrArrayOf[AxisPosition]] = Arg.Undefined,
  operation: Arg[String] = Arg.Undefined,
  aggregations: Arg[Seq[Transform.Aggregation]] = Arg.Undefined,
  preservegaps: Arg[Boolean] = Arg.Undefined,
  groups: Arg[OneOrArrayOf[AxisPosition]] = Arg.Undefined,
  nameformat: Arg[String] = Arg.Undefined,
  styles: Arg[Seq[Transform.Style]] = Arg.Undefined,
  value: Arg[Datum] = Arg.Undefined,
  order: Arg[Transform.Order] = Arg.Undefined,
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
    func: Arg[Aggregation.Function] = Arg.Undefined,
    funcmode: Arg[Aggregation.FunctionMode] = Arg.Undefined,
    enabled: Arg[Boolean] = Arg.Undefined,
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
