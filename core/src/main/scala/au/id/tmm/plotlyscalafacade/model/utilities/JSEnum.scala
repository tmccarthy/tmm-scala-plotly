package au.id.tmm.plotlyscalafacade.model.utilities

import io.circe.Encoder

trait JSEnum {
  def asString: String
}

object JSEnum {
  def plotlyFacadeEncoderForJsEnum[T <: JSEnum]: Encoder[T] = Encoder[String].contramap(_.asString)
}
