package au.id.tmm.plotlyscalafacade.model

import java.time.Duration

import au.id.tmm.plotlyscalafacade.model.utilities.{JSEnum, OneOrArrayOf}
import io.circe.Encoder
import io.circe.syntax.EncoderOps

// TODO encode this using FieldsFromEncoder
final case class TickProperties(
  tickmode: Option[TickProperties.TickMode],
  nticks: Option[Int],
  tick0: Option[AxisPosition],
  dtick: Option[TickProperties.DTick],
  tickvals: Option[DataArray],
  tickText: Option[DataArray],
  ticks: Option[TickProperties.DrawMode],
  ticklen: Option[Number],
  tickwidth: Option[Number],
  tickcolor: Option[Color],
  showticklabels: Option[Boolean],
  tickfont: Option[Font],
  tickangle: Option[Number],
  tickformat: Option[String],
  tickformatstops: Option[OneOrArrayOf[TickProperties.FormatStop]],
  tickprefix: Option[String],
  showtickprefix: Option[TickProperties.ShowRule],
  ticksuffix: Option[String],
  showticksuffix: Option[TickProperties.ShowRule],
  separatethousands: Option[Boolean],
  exponentformat: Option[TickProperties.ExponentFormat],
  showexponent: Option[TickProperties.ShowRule],
)

object TickProperties {

  sealed abstract class Mirror(val asString: String) extends JSEnum

  object Mirror {
    case object Ticks    extends Mirror("ticks")
    case object All      extends Mirror("all")
    case object AllTicks extends Mirror("allticks")
  }

  sealed abstract class TickMode(val asString: String) extends JSEnum

  object TickMode {
    case object Auto   extends TickMode("auto")
    case object Linear extends TickMode("linear")
    case object Array  extends TickMode("array")
  }

  sealed trait DTick

  object DTick {

    final case class Numeric(n: Number)               extends DTick
    final case class CategoryOffset(n: CategoryIndex) extends DTick

    sealed trait Logarithmic extends DTick

    object Logarithmic {
      final case class Log(n: Number)                  extends Logarithmic
      final case class Linear(n: Number)               extends Logarithmic
      final case object PowersOf10PlusAllSmallDigits   extends Logarithmic
      final case object PowersOf10PlusSmallDigits2And5 extends Logarithmic
    }

    sealed trait Date extends DTick

    object Date {
      final case class OfDuration(duration: Duration) extends Date
      final case class OfMonths(numMonths: Int)       extends Date
    }

    implicit val encoder: Encoder[DTick] = {
      case Numeric(n)                                 => n.asJson
      case CategoryOffset(n)                          => n.asJson
      case Logarithmic.Log(n)                         => n.asJson
      case Logarithmic.Linear(n)                      => s"L$n".asJson
      case Logarithmic.PowersOf10PlusAllSmallDigits   => "D1".asJson
      case Logarithmic.PowersOf10PlusSmallDigits2And5 => "D2".asJson
      case Date.OfDuration(duration)                  => duration.toMillis.toDouble.asJson
      case Date.OfMonths(numMonths)                   => s"M$numMonths".asJson
    }

  }

  sealed abstract class DrawMode(val asString: String) extends JSEnum

  object TickDrawMode {
    case object NotDrawn extends DrawMode("")
    case object Outside  extends DrawMode("outside")
    case object Inside   extends DrawMode("inside")
  }

  sealed abstract class ExponentFormat(val asString: String) extends JSEnum

  object ExponentFormat {
    case object None     extends ExponentFormat("none")
    case object SmallE   extends ExponentFormat("e")
    case object CapitalE extends ExponentFormat("E")
    case object Power    extends ExponentFormat("power")
    case object SI       extends ExponentFormat("SI")
    case object B        extends ExponentFormat("B")
  }

  sealed abstract class ShowRule(val asString: String) extends JSEnum

  object ShowRule {
    case object ShowAll   extends ShowRule("all")
    case object ShowFirst extends ShowRule("first")
    case object ShowLast  extends ShowRule("last")
    case object ShowNone  extends ShowRule("none")
  }

  final case class FormatStop(
    enabled: Option[Boolean],
    dtickrange: Option[Range[Datum]],
    value: Option[String],
    name: Option[String],
    templateitemname: Option[String],
  )

  object FormatStop {
    implicit val encoder: Encoder[FormatStop] = Encoder.forProduct5(
      "enabled",
      "dtickrange",
      "value",
      "name",
      "templateitemname",
    )(f =>
      (
        f.enabled,
        f.dtickrange,
        f.value,
        f.name,
        f.templateitemname,
      ),
    )
  }

  implicit val encoder: Encoder[TickProperties] = Encoder.forProduct22(
    "tickmode",
    "nticks",
    "tick0",
    "dtick",
    "tickvals",
    "tickText",
    "ticks",
    "ticklen",
    "tickwidth",
    "tickcolor",
    "showticklabels",
    "tickfont",
    "tickangle",
    "tickformat",
    "tickformatstops",
    "tickprefix",
    "showtickprefix",
    "ticksuffix",
    "showticksuffix",
    "separatethousands",
    "exponentformat",
    "showexponent",
  )(p =>
    (
      p.tickmode,
      p.nticks,
      p.tick0,
      p.dtick,
      p.tickvals,
      p.tickText,
      p.ticks,
      p.ticklen,
      p.tickwidth,
      p.tickcolor,
      p.showticklabels,
      p.tickfont,
      p.tickangle,
      p.tickformat,
      p.tickformatstops,
      p.tickprefix,
      p.showtickprefix,
      p.ticksuffix,
      p.showticksuffix,
      p.separatethousands,
      p.exponentformat,
      p.showexponent,
    ),
  )

}
