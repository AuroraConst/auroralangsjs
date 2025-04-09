package docere.sjsast
// type CV = ClinicalCoordinate | ClinicalValue

case class NGC (name:String, ccoords:Set[ClinicalCoordinate], narrative:Set[Narrative]=Set.empty)  extends SjsNode :
  def merge(n:NGC):NGC = 
    val narratives = narrative |+| n.narrative
    NGC(name,ccoords merge n.ccoords, narratives)
  override def merge(p: SjsNode): SjsNode = merge(p.asInstanceOf[NGC])

object NGC:
  def apply(n: GenAst.NGC): NGC = 
    val narratives = n.narrative.toList.map{n =>  Narrative(n.name)}.toSet
    val cc = n.coord.toList.map{ c => ClinicalCoordinate(c.asInstanceOf[GenAst.ClinicalCoordinate])}.toSet
    NGC(n.name, cc, narratives)