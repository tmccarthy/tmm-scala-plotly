package au.id.tmm.plotlyscalafacade

package object syntax {

  object blankOr      extends BlankOrSyntax
  object booleanOr    extends BooleanOrSyntax
  object falseOr      extends FalseOrSyntax
  object oneOrArrayOf extends OneOrArrayOfSyntax
  object optArg       extends OptArgSyntax

  object all
      extends AnyRef
      with BlankOrSyntax
      with BooleanOrSyntax
      with FalseOrSyntax
      with OneOrArrayOfSyntax
      with OptArgSyntax

}
