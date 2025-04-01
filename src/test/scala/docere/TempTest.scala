package docere
import org.scalatest._
import wordspec._
import matchers._
import testingutils.*
import typings.langium.libSyntaxTreeMod.AstNode


class TempTest extends wordspec.AsyncWordSpec with should.Matchers :
  override implicit def executionContext = global
  
  
  val aurora1 = testFilesAurora(0)
  val aurora2 = testFilesAurora(1)
  
  "test files issues1b.aurora and issues1a.aurora" should {
    "exist" in {
      checkFileAccess(aurora1) should be(true)
      checkFileAccess(aurora2) should be(true)
    }
  }  

  import typings.auroraLangium.distTypesSrcLanguageGeneratedAstMod.*

  "process issues + issues"  should { "work" in {

    
    parse(aurora1).toFuture.map{ pcm =>
      pcm.elements.foreach{ node => 
      info(f"Asttype: ${node.$type}%16s ") }

      true should be(true)
    }
  }
}
  


  "streamAllContents:TreeSTream" should {
    "convert to scala List[T] and then can be traversed" in {
      // import typings.auroraLangium.distTypesSrcLanguageGeneratedAstMod.*
      // val c:Clinical = ???
      val result = Try {
        parse(aurora1).toFuture
      }.recover { error =>
        info(s"Error during parsing: ${error.getMessage}, ${error.getStackTrace.mkString("\n")}")
        fail(error.getMessage)
      }.get



      result.map {module =>
        val listOfElements = streamAllContents(module).toScalaList
        val first = listOfElements(1)
        info(s"first element: ${first.$type}")
        val x = first.asInstanceOf [IssueCoordinate]
        info(s"$x")
        

        
        //note I use the f"" string interpolator to format the output
        // listOfElements.foreach{ node => info(f"Asttype: ${node.$type}%16s ")}
        // listOfElements.foreach{ node => info(f"Asttype: ${node.$type}%16s text: ${node.$cstNode.toOption.get.text.stripLeading().trim}%15s")}
        // listOfElements.foreach{ node => info(f"Asttype: ${node.$type}%16s, text: ${node.$cstNode.toOption.get.text}%5s, textOffset: ${node.$cstNode.toOption.get.offset}%4s")}
        listOfElements.size shouldNot be(0) // remember   asynchronous tests must end in a Future[Assertion] 
      }
    }
  }

 


