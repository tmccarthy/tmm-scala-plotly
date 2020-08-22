package au.id.tmm.plotly.model.utilities

import cats.Show
import io.circe.Encoder

trait JSEnum {
  def asString: String
}

object JSEnum {
  def tmmPlotlyEncoderForJsEnum[T <: JSEnum]: Encoder[T] = Encoder[String].contramap(_.asString)

  def tmmPlotlyShowForJsEnum[T <: JSEnum]: Show[T] = _.asString
}
