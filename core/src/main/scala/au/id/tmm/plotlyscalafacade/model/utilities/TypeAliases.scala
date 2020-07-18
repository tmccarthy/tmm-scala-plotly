package au.id.tmm.plotlyscalafacade.model.utilities

import java.time.LocalDateTime

private[model] trait TypeAliases {

  type Number = Double

  type NumberArray = Seq[Number]

  type Date = LocalDateTime // TODO is this right? NO should be Instant

  type CategoryIndex = Int

}
