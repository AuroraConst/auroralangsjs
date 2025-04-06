package docere.sjsast

case class Clinical(ngc:Set[NGC]) extends SjsNode :
  override val name = "Clinical" 

  override def merge(p: SjsNode): SjsNode = ???



object Clinical :
  def apply(c: GenAst.Clinical): Clinical = ???
  //   val g = c.namedGroups.toList.map{ngc(_)}.toSet
  //   Clinical(g)




  // def ngc(n: GenAst.NGC): NGC  =
  //   val x = n.coord.map{o =>  ccoord(o.asInstanceOf[GenAst.ClinicalCoordinate])}.toSet
  //   NGC(n.name,x)

  // def ccoord(c: GenAst.ClinicalCoordinate) : ClinicalCoordinate =
  //   ClinicalCoordinate(c.name)
  // def icoord(i: GenAst.IssueCoordinate): IssueCoordinate = 
  //   IssueCoordinate(i.name)
