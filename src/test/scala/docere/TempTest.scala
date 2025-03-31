
import org.scalatest._
import wordspec._
import matchers._
import testingutils.*
import typings.langium.libSyntaxTreeMod.AstNode


class TempTest extends wordspec.AsyncWordSpec with should.Matchers :
  override implicit def executionContext = global
  
  
  val aurora1 = testFilesAurora(0)
  val aurora2 = testFilesAurora(1)

  
  "test files aurora1.aurora and aurora2.aurora" should {
    "exist" in {
      checkFileAccess(aurora1) should be(true)
      checkFileAccess(aurora2) should be(true)
      
      
      val result = Try {
        parse(aurora1).toFuture
      }.recover { error =>
        info(s"Error during parsing: ${error.getMessage}, ${error.getStackTrace.mkString("\n")}")
        fail(error.getMessage)
      }.get

      result.map{ x => x.elements.size should be (2) }
    

    }
  }  


  sealed trait SjsAstType(astNode: AstNode)  
  
  // case class PCM(astNode:AstNode) extends SjsAstType(astNode)
  // case class Issue(astNode:AstNode) extends SjsAstType(astNode)
  // case class IssueCoordate(astNode:AstNode) extends SjsAstType(astNode)
  // case class Order(astNode:AstNode) extends SjsAstType(astNode)
  // case class OrderCoordate(astNode:AstNode) extends SjsAstType(astNode)
  // case class NGO(astNode:AstNode) extends SjsAstType(astNode)
  // case class Invalid(astNode:AstNode) extends SjsAstType(astNode)

  // def f(astNode:AstNode): SjsAstType = astNode.$type match {
  //   case "PCM" => PCM(astNode)
  //   case "Issue" => Issue(astNode)
  //   case "IssueCoordate" => IssueCoordate(astNode)
  //   case "Order" => Order(astNode)
  //   case "OrderCoordate" => OrderCoordate(astNode)
  //   case "NGO" => NGO(astNode)
  //   case _ => Invalid(astNode)
  // }


  "peek into type ifor"  should { "show information" in {
    

    Future(true should be (true))
    // Future(info(s"${PCM}")). map {
    //   x => x should be(())
    // }

  }
  }
  


  "streamAllContents:TreeSTream" should {
    "convert to scala List[T] and then can be traversed" in {
      import GenAstType.reflection.getAllTypes
      val result = Try {
        parse(aurora1).toFuture
      }.recover { error =>
        info(s"Error during parsing: ${error.getMessage}, ${error.getStackTrace.mkString("\n")}")
        fail(error.getMessage)
      }.get



      result.map {module =>
        val listOfElements = streamAllContents(module).toScalaList
        //note I use the f"" string interpolator to format the output
        listOfElements.foreach{ node => info(f"Asttype: ${node.$type}%16s ")}
        // listOfElements.foreach{ node => info(f"Asttype: ${node.$type}%16s text: ${node.$cstNode.toOption.get.text.stripLeading().trim}%15s")}
        // listOfElements.foreach{ node => info(f"Asttype: ${node.$type}%16s, text: ${node.$cstNode.toOption.get.text}%5s, textOffset: ${node.$cstNode.toOption.get.offset}%4s")}
        listOfElements.size shouldNot be(0) // remember   asynchronous tests must end in a Future[Assertion] 
      }
    }
  }

 


