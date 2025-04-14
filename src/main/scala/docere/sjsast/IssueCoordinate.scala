package docere.sjsast

case class IssueCoordinate(name: String, refs: Set[RefCoordinate] = Set.empty, narrative:Set[Narrative]=Set.empty) extends SjsNode:
  def merge(ic: IssueCoordinate): IssueCoordinate =
    val result = refs |+| ic.refs
    val narratives = narrative |+| ic.narrative
    IssueCoordinate(name, result, narratives)

  override def merge(p: SjsNode): SjsNode =
    merge(p.asInstanceOf[IssueCoordinate])

object IssueCoordinate:
  def apply(i: GenAst.IssueCoordinate): IssueCoordinate =
    val x = i.refs.toList.map { r => RefCoordinate(r.$refText) }.toSet
    val narratives = i.narrative.toList.map{n =>  Narrative(n.name)}.toSet
    IssueCoordinate(i.name, x, narratives)
