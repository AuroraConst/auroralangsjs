package docere.sjsast

case class ClinicalValue (name :String, narrative:Set[NL_STATEMENT]=Set.empty, refs: Set[RefCoordinate] = Set.empty) extends SjsNode:

  def merge(cv:ClinicalValue):ClinicalValue =
    val narratives = narrative |+| cv.narrative
    val result = refs |+| cv.refs
    ClinicalValue(name, narratives, result)
  override def merge(p: SjsNode): SjsNode = 
    merge(p.asInstanceOf[ClinicalValue])

object ClinicalValue{
  def apply (c: GenAst.ClinicalValue): ClinicalValue = 
    val narratives = c.narrative.toList.map{n =>  NL_STATEMENT(n.name)}.toSet
    val x = c.refs.toList.map { r => RefCoordinate(r.$refText) }.toSet
    ClinicalValue(c.name, narratives, x)
}