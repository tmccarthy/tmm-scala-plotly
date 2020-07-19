package au.id.tmm.plotlyscalafacade.model.utilities

import io.circe.{Encoder, Json}

private[model] class EncoderOps[A] private (encoder: Encoder[A]) {

  def mergeFieldsFrom[B : Encoder](f: A => B): Encoder[A] =
    Encoder { a =>
      val baseAsJson: Json = encoder.apply(a)

      val jsonWithExtraFields: OptArg[Json] = for {
        baseJsonObject    <- baseAsJson.asObject
        jsonObjectToMerge <- Encoder[B].apply(f(a)).asObject
      } yield Json.fromJsonObject(baseJsonObject deepMerge jsonObjectToMerge)

      jsonWithExtraFields getOrElse baseAsJson
    }

}

object EncoderOps {

  trait ToEncoderOps {
    implicit def toEncoderOps[A](encoder: Encoder[A]): EncoderOps[A] = new EncoderOps[A](encoder)
  }

}
