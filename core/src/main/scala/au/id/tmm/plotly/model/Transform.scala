package au.id.tmm.plotly.model

import au.id.tmm.plotly.model.utilities.{JSEnum, OneOrArrayOf, OptArg}
import io.circe.Encoder

final case class Transform(
  `type`: OptArg[Transform.Type]                   = OptArg.Undefined,
  enabled: OptArg[Boolean]                         = OptArg.Undefined,
  target: OptArg[OneOrArrayOf[Datum]]              = OptArg.Undefined,
  operation: OptArg[String]                        = OptArg.Undefined,
  aggregations: OptArg[Seq[Transform.Aggregation]] = OptArg.Undefined,
  preservegaps: OptArg[Boolean]                    = OptArg.Undefined,
  groups: OptArg[OneOrArrayOf[Datum]]              = OptArg.Undefined,
  nameformat: OptArg[String]                       = OptArg.Undefined,
  styles: OptArg[Seq[Transform.Style]]             = OptArg.Undefined,
  value: OptArg[Datum]                             = OptArg.Undefined,
  order: OptArg[Transform.Order]                   = OptArg.Undefined,
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
    func: OptArg[Aggregation.Function]         = OptArg.Undefined,
    funcmode: OptArg[Aggregation.FunctionMode] = OptArg.Undefined,
    enabled: OptArg[Boolean]                   = OptArg.Undefined,
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
    target: OptArg[OneOrArrayOf[Datum]] = OptArg.Undefined,
    value: OptArg[Trace]                = OptArg.Undefined,
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
