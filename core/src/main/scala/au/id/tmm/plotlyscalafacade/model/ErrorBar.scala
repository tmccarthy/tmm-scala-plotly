package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.{OptArg, JSEnum}
import io.circe.syntax.KeyOps
import io.circe.{Encoder, Json}

sealed trait ErrorBar {
  def visible: OptArg[Boolean]
  def symmetric: OptArg[Boolean]
  def color: OptArg[Color]
  def thickness: OptArg[Number]
  def width: OptArg[Number]
  def opacity: OptArg[Number]

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
    visible: OptArg[Boolean] = OptArg.Undefined,
    symmetric: OptArg[Boolean] = OptArg.Undefined,
    color: OptArg[Color] = OptArg.Undefined,
    thickness: OptArg[Number] = OptArg.Undefined,
    width: OptArg[Number] = OptArg.Undefined,
    opacity: OptArg[Number] = OptArg.Undefined,
    value: OptArg[Number] = OptArg.Undefined,
    valueminus: OptArg[Number] = OptArg.Undefined,
  ) extends ErrorBar {
    def `type`: ErrorBar.Type.Constant.type = ErrorBar.Type.Constant
  }

  final case class Percent(
    visible: OptArg[Boolean] = OptArg.Undefined,
    symmetric: OptArg[Boolean] = OptArg.Undefined,
    color: OptArg[Color] = OptArg.Undefined,
    thickness: OptArg[Number] = OptArg.Undefined,
    width: OptArg[Number] = OptArg.Undefined,
    opacity: OptArg[Number] = OptArg.Undefined,
    value: OptArg[Number] = OptArg.Undefined,
    valueminus: OptArg[Number] = OptArg.Undefined,
  ) extends ErrorBar {
    def `type`: ErrorBar.Type.Percent.type = ErrorBar.Type.Percent
  }

  final case class Data(
    visible: OptArg[Boolean] = OptArg.Undefined,
    symmetric: OptArg[Boolean] = OptArg.Undefined,
    color: OptArg[Color] = OptArg.Undefined,
    thickness: OptArg[Number] = OptArg.Undefined,
    width: OptArg[Number] = OptArg.Undefined,
    opacity: OptArg[Number] = OptArg.Undefined,
    array: OptArg[Seq[Datum]] = OptArg.Undefined,
    arrayminus: OptArg[Seq[Datum]] = OptArg.Undefined,
  ) extends ErrorBar {
    def `type`: ErrorBar.Type.Data.type = ErrorBar.Type.Data
  }

  implicit val encoder: Encoder[ErrorBar] = {
    case c: Constant =>
      Json.obj(
        "visible" := c.visible,
        "symmetric" := c.symmetric,
        "color" := c.color,
        "thickness" := c.thickness,
        "width" := c.width,
        "opacity" := c.opacity,
        "value" := c.value,
        "valueminus" := c.valueminus,
      )
    case p: Percent =>
      Json.obj(
        "visible" := p.visible,
        "symmetric" := p.symmetric,
        "color" := p.color,
        "thickness" := p.thickness,
        "width" := p.width,
        "opacity" := p.opacity,
        "value" := p.value,
        "valueminus" := p.valueminus,
      )
    case d: Data =>
      Json.obj(
        "visible" := d.visible,
        "symmetric" := d.symmetric,
        "color" := d.color,
        "thickness" := d.thickness,
        "width" := d.width,
        "opacity" := d.opacity,
        "array" := d.array,
        "arrayminus" := d.arrayminus,
      )
  }

}
