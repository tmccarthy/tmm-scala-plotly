package au.id.tmm.plotlyscalafacade.model

import io.circe.Encoder

final case class ModeBarButton(
  name: String,
  title: String,
  icon: ModeBarButton.Icon,
  gravity: Option[String],
  attr: Option[String],
  `val`: Option[String],
  toggle: Option[Boolean],
)

object ModeBarButton {

  sealed trait Icon

  object Icon {
    final case class OfString(asString: String) extends ModeBarButton.Icon
    final case class OfIcon(icon: IconDetails)  extends ModeBarButton.Icon

    implicit val encoder: Encoder[ModeBarButton.Icon] = {
      case OfString(asString) => Encoder[String].apply(asString)
      case OfIcon(icon)       => Encoder[IconDetails].apply(icon)
    }
  }

  final case class IconDetails(
    height: Option[Number],
    width: Option[Number],
    ascent: Option[Number],
    descent: Option[Number],
    name: Option[String],
    path: Option[String],
    svg: Option[String],
    transform: Option[String],
  )

  object IconDetails {
    implicit val encoder: Encoder[IconDetails] = Encoder.forProduct8(
      "height",
      "width",
      "ascent",
      "descent",
      "name",
      "path",
      "svg",
      "transform",
    )(i =>
      (
        i.height,
        i.width,
        i.ascent,
        i.descent,
        i.name,
        i.path,
        i.svg,
        i.transform,
      ),
    )
  }

  implicit val encoder: Encoder[ModeBarButton] = Encoder.forProduct7(
    "name",
    "title",
    "icon",
    "gravity",
    "attr",
    "val",
    "toggle",
  )(b =>
    (
      b.name,
      b.title,
      b.icon,
      b.gravity,
      b.attr,
      b.`val`,
      b.toggle,
    ),
  )

}
