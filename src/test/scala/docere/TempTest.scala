package docere

import org.scalatest._
import wordspec._
import matchers._
import testingutils.*




class TempTest extends wordspec.AsyncWordSpec with should.Matchers :
  override implicit def executionContext = global
  val ast = Try{parse(testFiles(0)).toFuture}.recover(e => {
        info(s"error: $e")
        Future.failed(e)
      }).get

  "CstUtils" should {
    "get offset and range information" in {
      import testingutils.langCstUtils.*
      fail("not implemented")
    }
  }  



  "streamAllContents:TreeSTream" should {
    "convert to scala List[T] and then can be traversed" in {
      import testingutils.langAstUtils.*
      ast.map {module =>
        val listOfElements = streamAllContents(module).toScalaList
        //note I use the f"" string interpolator to format the output
        listOfElements.foreach{ node => info(f"Asttype: ${node.$type}%16s, text: ${node.$cstNode.toOption.get.text}%5s, textOffset: ${node.$cstNode.toOption.get.offset}%4s")}
        listOfElements.size shouldNot be(0) // remember   asynchronous tests must end in a Future[Assertion] 
      }
    }
  }

 


