package au.id.tmm.plotly.model

import au.id.tmm.plotly.model.utilities.JSEnum

sealed abstract class Calendar(val asString: String) extends JSEnum

object Calendar {
  case object Gregorian  extends Calendar("gregorian")
  case object Chinese    extends Calendar("chinese")
  case object Coptic     extends Calendar("coptic")
  case object Discworld  extends Calendar("discworld")
  case object Ethiopian  extends Calendar("ethiopian")
  case object Hebrew     extends Calendar("hebrew")
  case object Islamic    extends Calendar("islamic")
  case object Julian     extends Calendar("julian")
  case object Mayan      extends Calendar("mayan")
  case object Nanakshahi extends Calendar("nanakshahi")
  case object Nepali     extends Calendar("nepali")
  case object Persian    extends Calendar("persian")
  case object Jalali     extends Calendar("jalali")
  case object Taiwan     extends Calendar("taiwan")
  case object Thai       extends Calendar("thai")
  case object Ummalqura  extends Calendar("ummalqura")
}
