package au.id.tmm.plotlyscalafacade

import au.id.tmm.plotlyscalafacade.model.utilities.JSEnum
import io.circe.Encoder

package object model extends utilities.TypeAliases {

  private[model] implicit def plotlyFacadeEncoderForJsEnum[T <: JSEnum]: Encoder[T] =
    JSEnum.plotlyFacadeEncoderForJsEnum[T]

}
