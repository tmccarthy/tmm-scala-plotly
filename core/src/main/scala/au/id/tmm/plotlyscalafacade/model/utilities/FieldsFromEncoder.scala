package au.id.tmm.plotlyscalafacade.model.utilities

import io.circe.{Encoder, Json}

final class FieldsFromEncoder[A] private (encoder: Encoder[A]) extends Encoder[A] {

  def plusFieldsFrom[B : Encoder](f: A => B): FieldsFromEncoder[A] = new FieldsFromEncoder[A](Encoder { a =>
    val baseAsJson: Json = encoder.apply(a)

    val jsonWithExtraFields: Option[Json] = for {
      baseJsonObject <- baseAsJson.asObject
      jsonObjectToMerge <- Encoder[B].apply(f(a)).asObject
    } yield Json.fromJsonObject(baseJsonObject deepMerge jsonObjectToMerge)

    jsonWithExtraFields getOrElse baseAsJson
  })

  override def apply(a: A): Json = encoder.apply(a)

}

object FieldsFromEncoder {

  def using[A](encoder: Encoder[A]): FieldsFromEncoder[A] = new FieldsFromEncoder[A](encoder)

}