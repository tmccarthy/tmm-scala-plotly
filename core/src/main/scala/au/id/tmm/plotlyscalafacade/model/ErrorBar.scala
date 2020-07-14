package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum

sealed trait ErrorBar {
  def visible: Boolean
  def symmetric: Boolean
  def color: Color
  def thickness: Number
  def width: Number
  def opacity: Number

  def `type`: ErrorBar.Type
}

object ErrorBar {

  sealed abstract class Type(val asString: String) extends JSEnum

  object Type {
    case object Constant extends Type("constant")
    case object Percent  extends Type("percent")
    case object Data     extends Type("data")
  }

  final case class Constant(
    visible: Boolean,
    symmetric: Boolean,
    color: Color,
    thickness: Number,
    width: Number,
    opacity: Number,
    value: Number,
    valueminus: Option[Number],
  ) extends ErrorBar {
    def `type`: ErrorBar.Type.Constant.type = ErrorBar.Type.Constant
  }

  final case class Percent(
    visible: Boolean,
    symmetric: Boolean,
    color: Color,
    thickness: Number,
    width: Number,
    opacity: Number,
    value: Number,
    valueminus: Option[Number],
  ) extends ErrorBar {
    def `type`: ErrorBar.Type.Percent.type = ErrorBar.Type.Percent
  }

  final case class Data(
    visible: Boolean,
    symmetric: Boolean,
    color: Color,
    thickness: Number,
    width: Number,
    opacity: Number,
    array: Seq[Datum],
    arrayminus: Option[Seq[Datum]],
  ) extends ErrorBar {
    def `type`: ErrorBar.Type.Data.type = ErrorBar.Type.Data
  }

}
