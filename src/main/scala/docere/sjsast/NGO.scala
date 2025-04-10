package docere.sjsast


case class NGO( name:String, orderCoordinates:Set[OrderCoordinate], narrative:Set[Narrative]=Set.empty)   extends SjsNode:
  def merge(n:NGO):NGO = 
    val narratives = narrative |+| n.narrative
    NGO(name,combine(orderCoordinates,n.orderCoordinates), narratives)

  override def merge(p: SjsNode): SjsNode =
    merge(p.asInstanceOf[NGO])


object NGO :
  def apply(n: GenAst.NGO): NGO = 
    val ocoords = n.orders.toList
    .map{o =>  OrderCoordinate(o.asInstanceOf[GenAst.OrderCoordinate])}
    .toSet
    val narratives = n.narrative.toList.map{n =>  Narrative(n.name)}.toSet
    NGO(n.name,ocoords, narratives)    