package au.id.tmm.plotly.model

import io.circe.Encoder

final case class Color(asString: String)

object Color {

  val transparent: Color = rgba(0, 0, 0, 0f)

  def rgb(
    r: Int,
    g: Int,
    b: Int,
  ): Color = Color(s"rgb(${fixRGBVal(r)},${fixRGBVal(g)},${fixRGBVal(b)})")

  def rgba(
    r: Int,
    g: Int,
    b: Int,
    a: Float,
  ): Color = Color(s"rgb(${fixRGBVal(r)},${fixRGBVal(g)},${fixRGBVal(b)},${fixAlpha(a)})")

  private def fixRGBVal(rgbVal: Int): Int = if (rgbVal < 0) 0 else if (rgbVal > 255) 255 else rgbVal
  private def fixAlpha(alpha: Float): Float = if (alpha < 0f) 0f else if (alpha > 1f) 1f else alpha

  implicit val encoder: Encoder[Color] = Encoder[String].contramap(_.asString)
}
