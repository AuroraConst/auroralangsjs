package docere
import org.scalatest._
import wordspec._
import matchers._
import testingutils.*


import typings.langium.libSyntaxTreeMod.AstNode




  // case class Issues(astNode:GenAst.Issues) extends SjsNode(astNode):
  //   override lazy val name = astNode.asInstanceOf[GenAst.Issues].name.asInstanceOf[String]

  // case class Orders(astNode:GenAst.Orders) extends SjsNode(astNode):
  //   override lazy val name = astNode.asInstanceOf[GenAst.Orders].name.asInstanceOf[String]  


  // case class IssueCoordinate(astNode:AstNode) extends SjsNode(astNode):
  //   override lazy val name = astNode.asInstanceOf[GenAst.IssueCoordinate].name 


  




class AuroraIssuesTest extends wordspec.AsyncWordSpec with should.Matchers :
  override implicit def executionContext = global
  
  
  val issues1a = testIssuesAurora(0)
  val issues1b = testIssuesAurora(1)

  
  "test files issues1a.aurora and issues1b.aurora" should {
    "exist" in {
      Future{
        checkFileAccess(issues1a) should be(true)
        checkFileAccess(issues1b) should be(true)
      }
    }
  }





 


