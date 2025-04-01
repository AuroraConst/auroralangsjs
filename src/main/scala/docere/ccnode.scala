package docere

import typings.langium.libSyntaxTreeMod.AstNode

object ccnode :

  object SjsAst:
    case class IdNode[T](id:String, sjsNode:SjsNode, astNode:AstNode)

    extension[T <: SjsNode] (s:Set[T]) 
      def text = s.map{i => i.text}.foldLeft(""){(a,b) => a + separator+ b }
    
    lazy val separator = "\r\n"

    sealed trait SjsNode :
      def text:String 


    case class PCM(issues:Option[Issues]) extends SjsNode :
      override def text = issues.map{i => i.text + separator}.getOrElse("")

    case class PCM1(cio:Map[String,Clinical|Issues|Orders]) extends SjsNode :
      override def text = 
        val s = cio.keySet
          .map{
            cio(_)  
          }
          .map{x => x match {
            case c:Clinical => ???
            case o:Orders => ???
            case i:Issues => ???
            // l => l.{i => i.text + separator}}
          }
        }
        "fake"
        // cio.keySet.map {key =>
        //   val r = cio(key)//.map{i => i.text + separator}.getOrElse
        //   "fake"
      //  }
    
    case class Clinical(ngc:Set[NGC]) extends SjsNode :
      override def text = "Clinical:" + ngc.text

    case class Issues(ics:Set[IssueCoordinate])  extends SjsNode :
      override def text = "Issues:" + ics.text
    case class Orders(ngo:Set[NGO])  extends SjsNode :
      override def text = "Orders:" + ngo.text




    case class IssueCoordinate(id:String) extends SjsNode :
      override def text = id

    case class NGO(id:String, ocoords:Set[OrderCoordinate])   extends SjsNode :
      override def text = id
    case class NGC(id:String, ccoords:Set[ClinicalCoordinate])  extends SjsNode :
      override def text = id

    case class OrderCoordinate(id:String) extends SjsNode :
      override def text = id
    case class ClinicalCoordinate(id:String) extends SjsNode :
      override def text = id






    //TODO FIX THIS
    // def pcm1(p:GenAst.PCM) :PCM1 = 
    //   val i = p.elements.toList
    //     .map(x => x.$type -> x)
    //     .map{ case(t,o) =>
    //       t match {
    //         case "Issues" => t -> issues(o.asInstanceOf[GenAst.Issues])
    //         case "Orders" => t -> orders(o.asInstanceOf[GenAst.Orders])
    //         case "Clinical" => t -> clinical(o.asInstanceOf[GenAst.Clinical])
    //       }

    //     }
    //     .toMap
    //   PCM1(i)
    def pcm(p :GenAst.PCM):PCM = 
      val i = p.elements.toList.filter(x => x.$type == "Issues").headOption.map(x => issues(x.asInstanceOf[GenAst.Issues]))
      PCM(i)

    def clinical(c: GenAst.Clinical) : Clinical =
      val g = c.namedGroups.toList.map{ngc(_)}.toSet
      Clinical(g)

    def issues(i: GenAst.Issues): Issues = 
      val coordinates = i.coord.toList.map{icoord(_) }.toSet
      Issues(coordinates)

    def orders(o:GenAst.Orders)  : Orders = 
      val ng = o.namedGroups.toList.map{ngo(_)}.toSet
      Orders(ng )  

    def ngc(n: GenAst.NGC): NGC  =
      val x = n.coord.map{o =>  ccoord(o.asInstanceOf[GenAst.ClinicalCoordinate])}.toSet
      NGC(n.name,x)
    def ngo(n: GenAst.NGO): NGO = 
      val x = n.orders.map{o =>  ocoord(o.asInstanceOf[GenAst.OrderCoordinate])}.toSet
      // NGO(coordinates)  
      NGO(n.name,x)

    def ccoord(c: GenAst.ClinicalCoordinate) : ClinicalCoordinate =
      ClinicalCoordinate(c.name)
    def icoord(i: GenAst.IssueCoordinate): IssueCoordinate = 
      IssueCoordinate(i.name)
      
    def ocoord(i: GenAst.OrderCoordinate): OrderCoordinate = 
      OrderCoordinate(i.name)

     
end ccnode  
