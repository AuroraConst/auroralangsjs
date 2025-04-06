package docere.sjsast

case class OrderCoordinate (name:String, refs:Set[RefCoordinate]=Set.empty) extends SjsNode:
  def merge (oc:OrderCoordinate):OrderCoordinate = 
    val result = refs |+| oc.refs
    OrderCoordinate(name,result)

  override def merge(p: SjsNode): SjsNode =
    merge(p.asInstanceOf[OrderCoordinate])

object OrderCoordinate :
  def apply(o: GenAst.OrderCoordinate): OrderCoordinate = 
    val x = o.refs.toList.map{r =>  r.$refText -> RefCoordinate(r.$refText)}.toMap
    OrderCoordinate(o.name)

