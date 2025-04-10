package docere.sjsast

case class OrderCoordinate (name:String, refs:Set[RefCoordinate]=Set.empty, narrative:Set[Narrative]=Set.empty) extends SjsNode:
  def merge (oc:OrderCoordinate):OrderCoordinate = 
    val result = refs |+| oc.refs
    val narratives = narrative |+| oc.narrative
    OrderCoordinate(name,result, narratives)

  override def merge(p: SjsNode): SjsNode =
    merge(p.asInstanceOf[OrderCoordinate])

object OrderCoordinate :
  def apply(o: GenAst.OrderCoordinate): OrderCoordinate = 
    val x = o.refs.toList.map{r =>  RefCoordinate(r.$refText)}.toSet
    val narratives = o.narrative.toList.map{n =>  Narrative(n.name)}.toSet
    OrderCoordinate(o.name,x, narratives)

