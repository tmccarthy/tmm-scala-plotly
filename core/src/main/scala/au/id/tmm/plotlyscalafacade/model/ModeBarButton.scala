package au.id.tmm.plotlyscalafacade.model

import io.circe.Encoder

final case class ModeBarButton(
  name: Option[String] = None,
  title: Option[String] = None,
  icon: Option[ModeBarButton.Icon] = None,
  gravity: Option[String] = None,
  attr: Option[String] = None,
  `val`: Option[String] = None,
  toggle: Option[Boolean] = None,
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
    height: Option[Number] = None,
    width: Option[Number] = None,
    ascent: Option[Number] = None,
    descent: Option[Number] = None,
    name: Option[String] = None,
    path: Option[String] = None,
    svg: Option[String] = None,
    transform: Option[String] = None,
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
