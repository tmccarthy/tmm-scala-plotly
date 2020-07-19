package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.OptArg
import io.circe.Encoder

final case class ModeBarButton(
  name: OptArg[String] = OptArg.Undefined,
  title: OptArg[String] = OptArg.Undefined,
  icon: OptArg[ModeBarButton.Icon] = OptArg.Undefined,
  gravity: OptArg[String] = OptArg.Undefined,
  attr: OptArg[String] = OptArg.Undefined,
  `val`: OptArg[String] = OptArg.Undefined,
  toggle: OptArg[Boolean] = OptArg.Undefined,
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
    height: OptArg[Number] = OptArg.Undefined,
    width: OptArg[Number] = OptArg.Undefined,
    ascent: OptArg[Number] = OptArg.Undefined,
    descent: OptArg[Number] = OptArg.Undefined,
    name: OptArg[String] = OptArg.Undefined,
    path: OptArg[String] = OptArg.Undefined,
    svg: OptArg[String] = OptArg.Undefined,
    transform: OptArg[String] = OptArg.Undefined,
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
