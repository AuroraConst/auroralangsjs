package docere.sjsast

case class ClinicalCoordinate (name :String, narrative:Set[Narrative]=Set.empty) extends SjsNode:

  def merge(cc:ClinicalCoordinate):ClinicalCoordinate = 
    val narratives = narrative |+| cc.narrative
    ClinicalCoordinate(name, narratives)
  override def merge(p: SjsNode): SjsNode = 
    merge(p.asInstanceOf[ClinicalCoordinate])

object ClinicalCoordinate{
  def apply (c: GenAst.ClinicalCoordinate): ClinicalCoordinate = 
    val narratives = c.narrative.toList.map{n =>  Narrative(n.name)}.toSet
    ClinicalCoordinate(c.name, narratives)
}