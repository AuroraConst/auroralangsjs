package docere.sjsast

case class Issues(ics: Set[IssueCoordinate], narrative:Set[Narrative]=Set.empty) extends SjsNode:
  override val name = "Issues"

  def merge(i: Issues): Issues =
    val narratives = narrative |+| i.narrative
    val x = ics merge i.ics
    Issues(x, narratives)

  override def merge(i: SjsNode): SjsNode =
    merge(i.asInstanceOf[Issues])

object Issues:
  def apply(i: GenAst.Issues): Issues =
    val coords = i.coord.toList.map { IssueCoordinate(_) }.toSet
    val narratives = i.narrative.toList.map{n =>  Narrative(n.name)}.toSet
    Issues(coords, narratives)
