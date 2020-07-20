package au.id.tmm.plotly.model

import io.circe.Encoder

final case class Color(asString: String)

object Color {
  implicit val encoder: Encoder[Color] = Encoder[String].contramap(_.asString)
}
