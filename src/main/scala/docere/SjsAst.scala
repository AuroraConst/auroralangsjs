package docere

import cats.syntax.semigroup._ // for |+|
import scala.scalajs.js
import js.JSConverters._

object SjsAst:
  extension[T <: SjsNode] (s:Set[T]) 
    def text:String = s.map{i => i.text}.foldLeft(""){_ + separator + _ } 

  extension[T <: SjsNode] (s:List[T]) //TODO: rough idea of converting  List of repeating elemnts and colidating into a set
    def toMapOfSet(m:List[SjsAst.IdSet[T]]):Map[String,Set[T]] = 
      m.foldLeft(Map[String,Set[T]]())  {(acc,e) => 
      acc + (e.name -> (acc(e.name) |+| e.set))
    }

    def text:String = s.map{i => i.text}.foldLeft(""){_ + separator + _ } + separator


  extension[T <: SjsNode] (m:Map[String,Set[T]])
    def text:String =  
      m.keySet.map{k =>  (k -> m(k).text)}.foldLeft(""){(acc,e) => 
        acc + e._1  + e._2 + separator
      }



  lazy val separator = "\r\n"


  sealed trait TestText :
    def text:String


  sealed trait SjsNode extends TestText:
    val name:String

  sealed trait IdSet[T <: SjsNode] extends SjsNode :
    val id:String
    val set:Set[T]
    def text:String = id + separator + set.text + separator

  type CIO = SjsAst.Clinical | SjsAst.Issues | SjsAst.Orders
  case class PCM(cio:Map[String,CIO]) extends SjsNode :
    override val name = "PCM"
    override def text = 
      cio.keySet
        .map{
          cio(_)  match {
            case c:Clinical => c.text
            case i:Issues   => i.text
            case o:Orders   => o.text
          }
        }
        .foldLeft(""){_ + _}
  object PCM :      
    def apply(p:GenAst.PCM) :PCM = 
      val i = p.elements.toList
        .map(x => x.$type -> x)
        .map{(t,o) =>
          t match {
            case "Issues" => t -> issues(o.asInstanceOf[GenAst.Issues])
            case "Orders" => t -> Orders.apply(o.asInstanceOf[GenAst.Orders])
            case "Clinical" => t -> clinical(o.asInstanceOf[GenAst.Clinical])
          }

        }.toMap.asInstanceOf[Map[String, CIO]]
      PCM(i)

    def apply(cio:Map[String,Clinical|Issues|Orders]) = new PCM(cio)

  
  case class Clinical(ngc:Set[NGC]) extends SjsNode :
    override val name = "Clinical"
    override def text = "$name:" + ngc.text

  case class Issues(ics:Set[IssueCoordinate])  extends SjsNode :  
    override val name = "Issues"
    override def text = s"$name:" + ics.text
  
  
  case class Orders(ngo:Map[String,NGO])  extends SjsNode :
    override val name = "Orders"
    override def text = s"$name:" + separator +  ngo.map {(k,v) => k -> v.set}.text
  
  object Orders :
    def apply(o:GenAst.Orders)  : Orders = 
      val ng = o.namedGroups.toList.map{NGO.apply(_)}.toSet
        .map{x => x.id -> x}.toMap
      Orders( ng )  



  case class IssueCoordinate(id:String) extends SjsNode :
    override val name = id
    override def text = id

  case class NGO( id:String, set:Set[SjsAst.OrderCoordinate])   extends SjsNode with IdSet[OrderCoordinate ] :
    override val name = "NamedGroupOrder"
    override def text = id + separator + set.text 
  object NGO :
    def apply(n: GenAst.NGO): NGO = 
      val ocoords = n.orders.toList
      .map{o =>  SjsAst.OrderCoordinate(o.asInstanceOf[GenAst.OrderCoordinate])}
      .toSet
      NGO(n.name,ocoords)

  case class NGC(id:String, ccoords:Set[ClinicalCoordinate])  extends SjsNode :
    override val name = id
    override def text = id +ccoords.text

  //TODO: finish texting of OrderCoordinate
  case class OrderCoordinate(id:String, refs:Map[String,RefCoordinate]=Map.empty) extends SjsNode :

    private def toIdMap = ???
    override val name = id 
    override def text =   id 
  object OrderCoordinate :
    def apply(o: GenAst.OrderCoordinate): OrderCoordinate = 
      val x = o.refs.toList.map{r =>  r.$refText -> RefCoordinate(r.$refText)}.toMap
      OrderCoordinate(o.name,x)


  case class RefCoordinate(id:String) extends SjsNode :
    override val name = id
    override def text = "(RefCoordinate finish implementation)"

  object RefCoordinate:
    def apply[T](langref:GenAst.LangiumReference[T]): RefCoordinate = 
      RefCoordinate(langref.$refText)


      

  case class ClinicalCoordinate(id:String) extends SjsNode :
    override val name = id
    override def text = id


  //TODO MOVE THESE  to appropriate companion object apply for these conversion functions
  def clinical(c: GenAst.Clinical) : Clinical =
    val g = c.namedGroups.toList.map{ngc(_)}.toSet
    Clinical(g)

  def issues(i: GenAst.Issues): Issues = 
    val coordinates = i.coord.toList.map{icoord(_) }.toSet
    Issues(coordinates)

  def ngc(n: GenAst.NGC): NGC  =
    val x = n.coord.map{o =>  ccoord(o.asInstanceOf[GenAst.ClinicalCoordinate])}.toSet
    NGC(n.name,x)

  def ccoord(c: GenAst.ClinicalCoordinate) : ClinicalCoordinate =
    ClinicalCoordinate(c.name)
  def icoord(i: GenAst.IssueCoordinate): IssueCoordinate = 
    IssueCoordinate(i.name)
    

    
