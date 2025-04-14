package docere.sjsast

case class IssueCoordinate(name: String, refs: Set[RefCoordinate] = Set.empty) extends SjsNode:
  def merge(ic: IssueCoordinate): IssueCoordinate =
    val result = refs |+| ic.refs
    IssueCoordinate(name, result)

  override def merge(p: SjsNode): SjsNode =
    merge(p.asInstanceOf[IssueCoordinate])

object IssueCoordinate:
  def apply(i: GenAst.IssueCoordinate): IssueCoordinate =
    val x = i.refs.toList.map { r => RefCoordinate(r.$refText) }.toSet
    IssueCoordinate(i.name, x)
