package au.id.tmm.plotlyscalafacade

import com.github.ghik.silencer.silent

package object model {

  type Number = Double

  type NumberArray = Seq[Number]

  // string | number | Date | null;
  type Datum = Nothing

  type Partial[A] = A // TODO find some way of handling this

  type PlotSelectedData = Partial[PlotDatum]

  // TODO put this somewhere
  @silent("define classes/objects inside of package objects")
  trait JSEnum {
    def asString: String
  }

}
