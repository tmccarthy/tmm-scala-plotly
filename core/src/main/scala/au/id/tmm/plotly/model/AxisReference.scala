package au.id.tmm.plotly.model

import au.id.tmm.plotly.model.utilities.JSEnum
import io.circe.KeyEncoder

object AxisReference {

  sealed abstract class XAxisReference(val asString: String) extends JSEnum

  case object X  extends XAxisReference("x")
  case object X2 extends XAxisReference("x2")
  case object X3 extends XAxisReference("x3")
  case object X4 extends XAxisReference("x4")
  case object X5 extends XAxisReference("x5")
  case object X6 extends XAxisReference("x6")
  case object X7 extends XAxisReference("x7")
  case object X8 extends XAxisReference("x8")
  case object X9 extends XAxisReference("x9")

  object XAxisReference {
    implicit val keyEncoder: KeyEncoder[XAxisReference] = {
      case X  => "xaxis"
      case X2 => "xaxis2"
      case X3 => "xaxis3"
      case X4 => "xaxis4"
      case X5 => "xaxis5"
      case X6 => "xaxis6"
      case X7 => "xaxis7"
      case X8 => "xaxis8"
      case X9 => "xaxis9"
    }
  }

  sealed abstract class YAxisReference(val asString: String) extends JSEnum

  case object Y  extends YAxisReference("y")
  case object Y2 extends YAxisReference("y2")
  case object Y3 extends YAxisReference("y3")
  case object Y4 extends YAxisReference("y4")
  case object Y5 extends YAxisReference("y5")
  case object Y6 extends YAxisReference("y6")
  case object Y7 extends YAxisReference("y7")
  case object Y8 extends YAxisReference("y8")
  case object Y9 extends YAxisReference("y9")

  object YAxisReference {
    implicit val keyEncoder: KeyEncoder[YAxisReference] = {
      case Y  => "yaxis"
      case Y2 => "yaxis2"
      case Y3 => "yaxis3"
      case Y4 => "yaxis4"
      case Y5 => "yaxis5"
      case Y6 => "yaxis6"
      case Y7 => "yaxis7"
      case Y8 => "yaxis8"
      case Y9 => "yaxis9"
    }
  }

}
