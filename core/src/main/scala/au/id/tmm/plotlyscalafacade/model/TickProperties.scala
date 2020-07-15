package au.id.tmm.plotlyscalafacade.model

import java.time.Duration

import au.id.tmm.plotlyscalafacade.model.utilities.{JSEnum, OneOrArrayOf, Range}
import io.circe.Encoder

final case class TickProperties(
  tickmode: TickProperties.TickMode,
  nticks: Int,
  tick0: AxisPosition,
  dtick: TickProperties.DTick,
  tickvals: DataSequence,
  tickText: DataSequence,
  ticks: TickProperties.DrawMode,
  ticklen: Number,
  tickwidth: Number,
  tickcolor: Color,
  showticklabels: Boolean,
  tickfont: Font,
  tickangle: Number,
  tickformat: String,
  tickformatstops: OneOrArrayOf[TickProperties.FormatStop],
  tickprefix: String,
  showtickprefix: TickProperties.ShowRule,
  ticksuffix: String,
  showticksuffix: TickProperties.ShowRule,
  separatethousands: Boolean,
  exponentformat: TickProperties.ExponentFormat,
  showexponent: TickProperties.ShowRule,
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
      case Numeric(n)                                 => Encoder[Number].apply(n)
      case CategoryOffset(n)                          => Encoder[Number].apply(n)
      case Logarithmic.Log(n)                         => Encoder[Number].apply(n)
      case Logarithmic.Linear(n)                      => Encoder[String].apply(s"L$n")
      case Logarithmic.PowersOf10PlusAllSmallDigits   => Encoder[String].apply("D1")
      case Logarithmic.PowersOf10PlusSmallDigits2And5 => Encoder[String].apply("D2")
      case Date.OfDuration(duration)                  => Encoder[Number].apply(duration.toMillis.toDouble)
      case Date.OfMonths(numMonths)                   => Encoder[String].apply(s"M$numMonths")
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
    enabled: Boolean,
    dtickrange: Range[Datum],
    value: String,
    name: String,
    templateitemname: String,
  )

}