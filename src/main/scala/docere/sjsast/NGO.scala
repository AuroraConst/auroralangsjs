package docere.sjsast


case class NGO( name:String, orderCoordinates:Set[OrderCoordinate])   extends SjsNode:
  def merge(n:NGO):NGO = 
    NGO(name,combine(orderCoordinates,n.orderCoordinates))

  override def merge(p: SjsNode): SjsNode =
    merge(p.asInstanceOf[NGO])


object NGO :
  def apply(n: GenAst.NGO): NGO = 
    val ocoords = n.orders.toList
    .map{o =>  OrderCoordinate(o.asInstanceOf[GenAst.OrderCoordinate])}
    .toSet
    NGO(n.name,ocoords)    
