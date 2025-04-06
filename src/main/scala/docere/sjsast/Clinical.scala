package docere.sjsast

case class Clinical(ngc:Set[NGC]) extends SjsNode :
  override val name = "Clinical" 

  def merge(c: Clinical): Clinical =
    Clinical(ngc merge ngc)
  override def merge(p: SjsNode): SjsNode = 
    merge(p.asInstanceOf[Clinical])



object Clinical :
  def apply(c: GenAst.Clinical): Clinical = ???
  //   val g = c.namedGroups.toList.map{ngc(_)}.toSet
  //   Clinical(g)


