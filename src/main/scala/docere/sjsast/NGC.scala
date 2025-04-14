package docere.sjsast
// type CV = ClinicalCoordinate | ClinicalValue

case class NGC (name:String, ccoords:Set[ClinicalCoordinate], narrative:Set[Narrative]=Set.empty,refs: Set[RefCoordinate] = Set.empty )  extends SjsNode :
  def merge(n:NGC):NGC = 
    val narratives = narrative |+| n.narrative
    val result = refs |+| n.refs
    NGC(name,ccoords merge n.ccoords, narratives, refs)
  override def merge(p: SjsNode): SjsNode = merge(p.asInstanceOf[NGC])

object NGC:
  def apply(n: GenAst.NGC): NGC = 
    val narratives = n.narrative.toList.map{n =>  Narrative(n.name)}.toSet
    val cc = n.coord.toList.map{ c => ClinicalCoordinate(c.asInstanceOf[GenAst.ClinicalCoordinate])}.toSet
    val x = n.refs.toList.map { r => RefCoordinate(r.$refText) }.toSet
    NGC(n.name, cc, narratives, x)