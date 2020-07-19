package au.id.tmm.plotlyscalafacade

package object syntax {

  object blankOr      extends BlankOrSyntax
  object booleanOr    extends BooleanOrSyntax
  object falseOr      extends FalseOrSyntax
  object oneOrArrayOf extends OneOrArrayOfSyntax
  object optArg       extends OptArgSyntax
  object datum        extends DatumSyntax
  object dataArray    extends DataArraySyntax

  object all
      extends AnyRef
      with BlankOrSyntax
      with BooleanOrSyntax
      with FalseOrSyntax
      with OneOrArrayOfSyntax
      with OptArgSyntax
      with DatumSyntax
      with DataArraySyntax

}
