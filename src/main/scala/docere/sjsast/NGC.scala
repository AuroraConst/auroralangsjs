package docere.sjsast
// type CV = ClinicalCoordinate | ClinicalValue

case class NGC (name:String, ccoords:Set[ClinicalCoordinate])  extends SjsNode :
  def merge(n:NGC):NGC = 
    NGC(name,ccoords merge n.ccoords)
  override def merge(p: SjsNode): SjsNode = merge(p.asInstanceOf[NGC])

object NGC:
  def apply(n: GenAst.NGC): NGC = 
    val cc = n.coord.toList
    .map{ c => ClinicalCoordinate(c.asInstanceOf[GenAst.ClinicalCoordinate])}
    .toSet
    NGC(n.name, cc)