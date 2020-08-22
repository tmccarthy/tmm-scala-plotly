package au.id.tmm.plotly

import java.net.URI
import java.util.Locale

import au.id.tmm.plotly.model.utilities.JSEnum
import cats.Show
import io.circe.Encoder
import io.circe.syntax.EncoderOps

package object model extends utilities.TypeAliases with utilities.EncoderOps.ToEncoderOps {

  private[model] implicit def tmmPlotlyEncoderForJsEnum[T <: JSEnum]: Encoder[T] =
    JSEnum.tmmPlotlyEncoderForJsEnum[T]

  private[model] implicit def tmmPlotlyShowForJsEnum[T <: JSEnum]: Show[T] =
    JSEnum.tmmPlotlyShowForJsEnum[T]

  private[model] implicit val tmmPlotlyEncoderForURI: Encoder[URI] = _.toString.asJson

  private[model] implicit val tmmPlotlyEncoderForLocale: Encoder[Locale] = _.toString.asJson

}
