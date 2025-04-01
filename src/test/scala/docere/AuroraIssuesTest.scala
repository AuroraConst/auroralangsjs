package docere
import org.scalatest._
import wordspec._
import matchers._
import testingutils.*


import cats.kernel.BoundedSemilattice
import typings.langium.libSyntaxTreeMod.AstNode
import typings.langium.libSyntaxTreeMod.CstNode


case class TextAstNode(s:String,a:AstNode)
object GenAst:
  import typings.auroraLangium.distTypesSrcLanguageGeneratedAstMod as GenAstMod
  type PCM=GenAstMod.PCM
  type Issues=GenAstMod.Issues
  type IssueCoordinate=GenAstMod.IssueCoordinate
  type Orders=GenAstMod.Orders
  

object SjsAst:
  case class IdNode(id:String, sjsNode:SjsNode)

  sealed trait SjsNode(astNode:AstNode) :
    lazy val cstNode =  astNode.$cstNode.toOption
    lazy val name:String = ???
    lazy val text:String = astNode.$cstNode.toOption.map(_.text).getOrElse("")
    lazy val idNode:(String,SjsNode) = name -> this

  case class PCM(astNode:AstNode) extends SjsNode(astNode):
    val astPCM = astNode.asInstanceOf[GenAst.PCM]
    override lazy val name = astNode.$type

    private def sjsNode(n:AstNode):SjsNode = 
      n.$type match {
        case "Issues" => Issues(n.asInstanceOf[GenAst.Issues])
        case "Orders" => Orders(n.asInstanceOf[GenAst.Orders])
        case _ => throw IllegalArgumentException(s"Type ${n.$type}} not found")
      }

    lazy val elems:Map[String,SjsNode] = astPCM.elements.toList
      .map{ e => sjsNode(e).idNode}.toMap

    
    def issues:List[Issues] = astPCM.elements.toList.filter{ e=> e.$type == "Issues" }
      .map{_.asInstanceOf[GenAst.Issues]} //astNode.asInstanceOf[GenAst.PCM].issues.map(Issues(_)).toList
      .map{Issues(_)}

  case class Issues(astNode:GenAst.Issues) extends SjsNode(astNode):
    override lazy val name = astNode.asInstanceOf[GenAst.Issues].name.asInstanceOf[String]

  case class Orders(astNode:GenAst.Orders) extends SjsNode(astNode):
    override lazy val name = astNode.asInstanceOf[GenAst.Orders].name.asInstanceOf[String]  


  case class IssueCoordinate(astNode:AstNode) extends SjsNode(astNode):
    override lazy val name = astNode.asInstanceOf[GenAst.IssueCoordinate].name 


  

// given BoundedSemilattice[Option[PCM]]  = new BoundedSemilattice[Option[PCM]] {
//   def empty: Option[PCM] = None
//   def combine(x: Option[PCM], y: Option[PCM]): Option[PCM] =  
//     if(x.isEmpty) then y
//     else if(y.isEmpty) then x
//     else x
// }



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





 


