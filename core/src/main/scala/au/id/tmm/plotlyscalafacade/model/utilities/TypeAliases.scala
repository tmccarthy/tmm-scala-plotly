package au.id.tmm.plotlyscalafacade.model.utilities

import java.time.LocalDateTime

import au.id.tmm.plotlyscalafacade.model.PlotDatum

private[model] trait TypeAliases {

  type Number = Double

  type NumberArray = Seq[Number]

  type Date = LocalDateTime // TODO is this right?

  type CategoryIndex = Int

  type Datum = Nothing // TODO string | number | Date | null;
  type TypedArray = Nothing // TODO Int8Array | Uint8Array | Int16Array | Uint16Array | Int32Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array;

  type Partial[A] = A // TODO find some way of handling this

  type PlotSelectedData = Partial[PlotDatum]

}
