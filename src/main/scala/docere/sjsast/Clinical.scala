package docere.sjsast

case class Clinical(ngc:Set[NGC]) extends SjsNode :
  override val name = "Clinical" 

  def merge(c: Clinical): Clinical =
    Clinical(ngc merge c.ngc)
  override def merge(p: SjsNode): SjsNode = 
    merge(p.asInstanceOf[Clinical])



object Clinical :
  def apply(c: GenAst.Clinical): Clinical = 
    val g = c.namedGroups.toList.map{NGC(_)}.toSet
    Clinical(g)