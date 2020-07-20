package au.id.tmm.plotly

import java.net.URI
import java.util.Locale

import au.id.tmm.plotly.model.utilities.JSEnum
import cats.Show
import io.circe.Encoder
import io.circe.syntax.EncoderOps

package object model extends utilities.TypeAliases with utilities.EncoderOps.ToEncoderOps {

  private[model] implicit def plotlyFacadeEncoderForJsEnum[T <: JSEnum]: Encoder[T] =
    JSEnum.plotlyFacadeEncoderForJsEnum[T]

  private[model] implicit def plotlyFacadeShowForJsEnum[T <: JSEnum]: Show[T] =
    JSEnum.plotlyFacadeShowForJsEnum[T]

  private[model] implicit val plotlyFacadeEncoderForURI: Encoder[URI] = _.toString.asJson

  private[model] implicit val plotlyFacadeEncoderForLocale: Encoder[Locale] = _.toString.asJson

}
