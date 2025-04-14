package docere.sjsast

case class ClinicalCoordinate (name :String, narrative:Set[Narrative]=Set.empty, refs: Set[RefCoordinate] = Set.empty,qu: Set[QU] = Set.empty) extends SjsNode:

  def merge(cc:ClinicalCoordinate):ClinicalCoordinate = 
    val narratives = narrative |+| cc.narrative
    val result = refs |+| cc.refs
    val qumerge = qu |+| cc.qu
    ClinicalCoordinate(name, narratives, result, qumerge)
  override def merge(p: SjsNode): SjsNode = 
    merge(p.asInstanceOf[ClinicalCoordinate])

object ClinicalCoordinate{
  def apply (c: GenAst.ClinicalCoordinate): ClinicalCoordinate = 
    val narratives = c.narrative.toList.map{n =>  Narrative(n.name)}.toSet
    val x = c.refs.toList.map { r => RefCoordinate(r.$refText) }.toSet
    val qus = c.qu.toList.map{p =>  QU(p.query)}.toSet
    ClinicalCoordinate(c.name, narratives, x, qus)
}