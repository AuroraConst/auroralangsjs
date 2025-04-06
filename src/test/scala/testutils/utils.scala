package testingutils

  import com.axiom.AuroraAsyncTest
  export com.axiom.testutils.FileUtils.*
  export com.axiom.testutils.Convertors.*
  export scala.scalajs.js.JSConverters.*
  export scala.concurrent.ExecutionContext.Implicits.global
  export scala.concurrent.Future
  export scala.util.Try
  export com.axiom.{AuroraAsyncTest,AuroraTest}
  
  //the following two imports were helped by exporting them from arith-utils from the typesript project
  export typings.auroraLangium.cliMod.AstUtils.{streamAllContents}
  export typings.auroraLangium.cliMod.GenAst as GenAst
  export typings.auroraLangium.cliMod.PCM
  export typings.auroraLangium.cliMod.parse


  val fExtension = "aurora"
  val testFilesMath = List("math1", "math2", "math3").map{testFullPath}
  val testFilesAurora = List("aurora1","aurora2").map{testFullPath}
  val testIssuesAurora = List("issues1a","issues1b").map{testIssuesPath}
  
  private  def testFullPath(name: String) = testAuroraFiles / s"$name.$fExtension"
  private  def testIssuesPath(name:String) = testIssueFiles / s"$name.$fExtension"
