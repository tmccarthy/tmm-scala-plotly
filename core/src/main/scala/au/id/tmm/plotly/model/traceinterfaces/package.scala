package au.id.tmm.plotly.model

import au.id.tmm.plotly.model.utilities.OptArg

package object traceinterfaces {

  // TODO improve name
  private[traceinterfaces] def unsquash[A](as: Vector[OptArg[A]], nullValue: A): OptArg[Vector[A]] =
    if (as.exists(_.isDefined))
      OptArg.Of(as.map(_.toOption.getOrElse(nullValue)))
    else
      OptArg.Undefined

  private[traceinterfaces] def unsquash(data: Vector[OptArg[Datum]]): OptArg[Vector[Datum]] =
    unsquash(data, nullValue = Datum.OfNull)

}
