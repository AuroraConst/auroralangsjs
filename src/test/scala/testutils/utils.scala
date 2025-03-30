package testingutils
  export com.axiom.testutils.FileUtils.*
  export com.axiom.testutils.Convertors.*
  export scala.scalajs.js.JSConverters.*
  export scala.concurrent.ExecutionContext.Implicits.global
  export scala.concurrent.Future
  export scala.util.Try
  export typings.auroraLangium.distTypesSrcCliCliUtilMod.{parse}
  export typings.auroraLangium.distTypesSrcLanguageGeneratedModuleMod as langGenAst
  
  //the following two imports were helped by exporting them from arith-utils from the typesript project
  export typings.auroraLangium.distTypesSrcCliCliUtilMod.AstUtils as langAstUtils
  export typings.auroraLangium.distTypesSrcCliCliUtilMod.CstUtils as langCstUtils


  val fExtension = "arith"
  val testFiles = List("math1", "math2", "math3").map{testFullPath}
  
  private  def testFullPath(name: String) = testAuroraFiles / s"$name.$fExtension"
