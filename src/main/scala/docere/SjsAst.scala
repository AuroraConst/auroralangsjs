package docere

import cats.syntax.semigroup._ // for |+|
import scala.scalajs.js
import js.JSConverters._

object SjsAst:
  import catsgivens.{given}
  import cats.syntax.semigroup._ // for |+|
  def combine[T <:SjsNode](x:Set[T], y:Set[T]): Set[T] = 
    val combined = x.asMap |+| y.asMap
    combined.map{(k,v) => v.asInstanceOf[T]}.toSet


  // extension[T <: SjsNode] (s:List[T]) //TODO: rough idea of converting  List of repeating elemnts and colidating into a set
  //   def toMapOfSet(m:List[SjsAst.NameSet[T]]):Map[String,Set[T]] = 
  //     m.foldLeft(Map[String,Set[T]]())  {(acc,e) => 
  //     acc + (e.name -> (acc(e.name) |+| e.set))
  //   }


  extension [T<:SjsNode](s:Set[T]) 
    private def kv(o:T) = o.name -> o
    def asMap:Map[String,SjsNode] =  s.map{kv}.toMap



  sealed trait SjsNode :
    val name:String
    def merge(p:SjsNode):SjsNode 

  case class InvalidSjsNode() extends SjsNode :
    override val name = "InvalidSjsNode"
    override def merge(p: SjsNode): SjsNode = this

    
  trait Name  :
    val name:String
  
  // @deprecated("use Name trait instead")  
  // trait NameSet[T <: SjsNode] extends SjsNode  with Name:
  //   val set:Set[T]
    


  // case class PCM(cio:Map[String,Orders]) extends SjsNode :
  //  override val name = "PCM"
  //   def merge[PCM](p:PCM):PCM = ???
  //     // PCM(this.cio |+| p.cio)


  // object PCM :      
  //   def apply(p:GenAst.PCM) :PCM = 
  //     val i = p.elements.toList
  //       .map(x => x.$type -> x)
  //       .map{(t,o) =>
  //         t match {
  //           // case "Issues" => t -> issues(o.asInstanceOf[GenAst.Issues])
  //           case "Orders" => t -> Orders.apply(o.asInstanceOf[GenAst.Orders])
  //           // case "Clinical" => t -> ???
  //         }

  //       }.toMap
  //     SjsAst.PCM(i)


  
  // case class Clinical(ngc:Set[NGC]) extends SjsNode :
  //   override val name = "Clinical"

  // case class Issues(ics:Set[IssueCoordinate])  extends SjsNode :  
  //   override val name = "Issues"
  // object Issues : 
  //   def apply(i:GenAst.Issues): Issues = 
  //     val coords = i.coord.toList.map{icoord(_) }.toSet
  //     Issues(coords)  
  
  case class Orders(ngo:Set[NGO])  extends SjsNode :
    override val name = "Orders"
    def merge(o: Orders): Orders = 
      val x = combine(ngo,o.ngo)
      Orders(x)
    override def merge(o:SjsNode):SjsNode = ???
  
  object Orders :
    def apply(o:GenAst.Orders)  : Orders = 
      val ng = o.namedGroups.toList.map{NGO.apply(_)}.toSet
      Orders( ng )  



  // case class IssueCoordinate (name:String) extends SjsNode with Name 

  case class NGO( name:String, orderCoordinates:Set[SjsAst.OrderCoordinate])   extends SjsNode with Name :
    import catsgivens.{given}
    def merge(n:NGO):NGO = 
      NGO(name,combine(orderCoordinates,n.orderCoordinates))

    override def merge(p: SjsNode): SjsNode =
      merge(p.asInstanceOf[NGO])

  object NGO :
    def apply(n: GenAst.NGO): NGO = 
      val ocoords = n.orders.toList
      .map{o =>  SjsAst.OrderCoordinate(o.asInstanceOf[GenAst.OrderCoordinate])}
      .toSet
      NGO(n.name,ocoords)

  // case class NGC (name:String, ccoords:Set[ClinicalCoordinate])  extends SjsNode 

  case class OrderCoordinate (name:String, refs:Set[RefCoordinate]=Set.empty) extends SjsNode with Name {
    import catsgivens.{given}
    import cats.syntax.semigroup._ // for |+|

    def merge (oc:OrderCoordinate):OrderCoordinate = 
      val x = refs.asMap |+| oc.refs.asMap
      val y = x.map{(k,v) =>v.asInstanceOf[RefCoordinate]}.toSet
      OrderCoordinate(name,y)

    override def merge(p: SjsNode): SjsNode =
      merge(p.asInstanceOf[OrderCoordinate])
  }

  object OrderCoordinate :
    def apply(o: GenAst.OrderCoordinate): OrderCoordinate = 
      val x = o.refs.toList.map{r =>  r.$refText -> RefCoordinate(r.$refText)}.toMap
      OrderCoordinate(o.name)


  case class RefCoordinate (name:String) extends SjsNode with Name {
    override def merge(p: SjsNode): SjsNode = this

  } 

  object RefCoordinate:
    def apply[T](langref:GenAst.LangiumReference[T]): RefCoordinate = 
      RefCoordinate(langref.$refText)


      

  // case class ClinicalCoordinate (name :String) extends SjsNode  with Name


  //TODO MOVE THESE  to appropriate companion object apply for these conversion functions
  // def clinical(c: GenAst.Clinical) : Clinical =
  //   val g = c.namedGroups.toList.map{ngc(_)}.toSet
  //   Clinical(g)

  // def issues(i: GenAst.Issues): Issues = 
  //   val coordinates = i.coord.toList.map{icoord(_) }.toSet
  //   Issues(coordinates)

  // def ngc(n: GenAst.NGC): NGC  =
  //   val x = n.coord.map{o =>  ccoord(o.asInstanceOf[GenAst.ClinicalCoordinate])}.toSet
  //   NGC(n.name,x)

  // def ccoord(c: GenAst.ClinicalCoordinate) : ClinicalCoordinate =
  //   ClinicalCoordinate(c.name)
  // def icoord(i: GenAst.IssueCoordinate): IssueCoordinate = 
  //   IssueCoordinate(i.name)
    

    
