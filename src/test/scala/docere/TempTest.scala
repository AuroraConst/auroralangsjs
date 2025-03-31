package docere

import org.scalatest._
import wordspec._
import matchers._
import testingutils.*




class TempTest extends wordspec.AsyncWordSpec with should.Matchers :
  override implicit def executionContext = global
  
  
  val testpath = testFilesAurora(0)
  
  "testpath" should {
    "should exist" in {
      checkFileAccess(testpath) should be(true)

      val result = Try {
        parse(testpath).toFuture
      }.recover { error =>
        info(s"Error during parsing: ${error.getMessage}, ${error.getStackTrace.mkString("\n")}")
        fail(error.getMessage)
      }.get

      result.map{ x => x.elements.size should be (1) }
    

    }
  }  



  "streamAllContents:TreeSTream" should {
    "convert to scala List[T] and then can be traversed" in {
       fail("not implemented")
      // import testingutils.langAstUtils.*
      // ast.map {module =>
      //   val listOfElements = streamAllContents(module).toScalaList
      //   //note I use the f"" string interpolator to format the output
      //   listOfElements.foreach{ node => info(f"Asttype: ${node.$type}%16s, text: ${node.$cstNode.toOption.get.text}%5s, textOffset: ${node.$cstNode.toOption.get.offset}%4s")}
      //   listOfElements.size shouldNot be(0) // remember   asynchronous tests must end in a Future[Assertion] 
      // }
    }
  }

 


