package docere.sjsast

case class Orders(ngo:Set[NGO], narrative:Set[Narrative]=Set.empty)  extends SjsNode :
  override val name = "Orders"

  def merge(o: Orders): Orders = 
    val x = combine(ngo,o.ngo)
    val narratives = narrative |+| o.narrative
    Orders(x, narratives)
  override def merge(o:SjsNode):SjsNode = merge(o.asInstanceOf[Orders])

object Orders :
  def apply(o:GenAst.Orders)  : Orders = 
    val ng = o.namedGroups.toList.map{NGO(_)}.toSet
    val narratives = o.narrative.toList.map{n =>  Narrative(n.name)}.toSet
    Orders(ng, narratives)