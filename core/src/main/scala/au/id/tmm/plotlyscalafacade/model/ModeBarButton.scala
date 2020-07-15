package au.id.tmm.plotlyscalafacade.model

import au.id.tmm.plotlyscalafacade.model
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
    final case class OfIcon(icon: model.Icon)   extends ModeBarButton.Icon

    implicit val encoder: Encoder[ModeBarButton.Icon] = {
      case OfString(asString) => Encoder[String].apply(asString)
      case OfIcon(icon)       => Encoder[model.Icon].apply(icon)
    }
  }

}
