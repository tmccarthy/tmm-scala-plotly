package au.id.tmm.plotlyscalafacade.model.utilities

import java.time.LocalDateTime

import au.id.tmm.plotlyscalafacade.model.PlotDatum

private[model] trait TypeAliases {

  type Number = Double

  type NumberArray = Seq[Number]

  type Date = LocalDateTime // TODO is this right?

  type CategoryIndex = Int

  type Datum = Nothing // TODO string | number | Date | null;

  // TODO this will need a rename and some thought
  // TODO the two-dimensional and three-dimensional versions of this only work in some contexts. May want some way to constrain this
  type DataSequence = Nothing // TODO Datum[] | Datum[][] | Datum[][][] | TypedArray

  type Partial[A] = A // TODO find some way of handling this

  type PlotSelectedData = Partial[PlotDatum]

}
