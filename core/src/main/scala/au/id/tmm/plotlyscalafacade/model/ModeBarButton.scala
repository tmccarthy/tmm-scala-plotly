package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model.utilities.Arg
import io.circe.Encoder

final case class ModeBarButton(
  name: Arg[String] = Arg.Undefined,
  title: Arg[String] = Arg.Undefined,
  icon: Arg[ModeBarButton.Icon] = Arg.Undefined,
  gravity: Arg[String] = Arg.Undefined,
  attr: Arg[String] = Arg.Undefined,
  `val`: Arg[String] = Arg.Undefined,
  toggle: Arg[Boolean] = Arg.Undefined,
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
    height: Arg[Number] = Arg.Undefined,
    width: Arg[Number] = Arg.Undefined,
    ascent: Arg[Number] = Arg.Undefined,
    descent: Arg[Number] = Arg.Undefined,
    name: Arg[String] = Arg.Undefined,
    path: Arg[String] = Arg.Undefined,
    svg: Arg[String] = Arg.Undefined,
    transform: Arg[String] = Arg.Undefined,
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
