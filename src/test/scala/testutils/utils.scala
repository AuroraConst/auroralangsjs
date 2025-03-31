package testingutils
  export com.axiom.testutils.FileUtils.*
  export com.axiom.testutils.Convertors.*
  export scala.scalajs.js.JSConverters.*
  export scala.concurrent.ExecutionContext.Implicits.global
  export scala.concurrent.Future
  export scala.util.Try
  export typings.auroraLangium.distTypesSrcLanguageGeneratedModuleMod as langGenAst
  
  //the following two imports were helped by exporting them from arith-utils from the typesript project
  export typings.auroraLangium.cliMod.AstUtils.{streamAllContents}
  export typings.auroraLangium.cliMod.GenAst as GenAst
  export typings.auroraLangium.cliMod.GenAstType as GenAstType
  export typings.auroraLangium.cliMod.parse

  // export typings.auroraLangium.distTypesSrcLanguageGeneratedAstMod.{Clinical, PCM, Orders}





  val fExtension = "aurora"
  val testFilesMath = List("math1", "math2", "math3").map{testFullPath}
  val testFilesAurora = List("aurora1","aurora2").map{testFullPath}
  
  private  def testFullPath(name: String) = testAuroraFiles / s"$name.$fExtension"
