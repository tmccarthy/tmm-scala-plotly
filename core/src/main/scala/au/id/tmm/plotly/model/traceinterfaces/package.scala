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

  private[traceinterfaces] def spread2[A1, A2, B](a1: OptArg[A1], a2: OptArg[A2]): OptArg[(OptArg[A1], OptArg[A2])] =
    if (a1.isDefined || a2.isDefined) OptArg.Of((a1, a2)) else OptArg.Undefined

  private[traceinterfaces] def merge[A](left: OptArg[A], right: OptArg[A])(mergeFn: (A, A) => A): OptArg[A] =
    (left, right) match {
      case (OptArg.Undefined, OptArg.Undefined) => OptArg.Undefined
      case (l, OptArg.Undefined)                => l
      case (OptArg.Undefined, r)                => r
      case (OptArg.Of(l), OptArg.Of(r))         => OptArg.Of(mergeFn(l, r))
    }

}
