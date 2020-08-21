package au.id.tmm.plotly.model

import au.id.tmm.plotly.model.utilities.OptArg

package object traceinterfaces {

  /**
    * Returns a defined vector if any provided elements are defined.
    */
  private[traceinterfaces] def spread[A](as: Vector[OptArg[A]], nullValue: A): OptArg[Vector[A]] =
    if (as.exists(_.isDefined))
      OptArg.Of(as.map(_.toOption.getOrElse(nullValue)))
    else
      OptArg.Undefined

  private[traceinterfaces] def spread(data: Vector[OptArg[Datum]]): OptArg[Vector[Datum]] =
    spread(data, nullValue = Datum.OfNull)

}
