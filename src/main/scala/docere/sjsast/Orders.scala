package docere.sjsast

case class Orders(ngo:Set[NGO])  extends SjsNode :
  override val name = "Orders"
  def merge(o: Orders): Orders = 
    val x = combine(ngo,o.ngo)
    Orders(x)
  override def merge(o:SjsNode):SjsNode = merge(o.asInstanceOf[Orders])

object Orders :
  def apply(o:GenAst.Orders)  : Orders = 
    val ng = o.namedGroups.toList.map{NGO.apply(_)}.toSet
    Orders( ng )  

    
