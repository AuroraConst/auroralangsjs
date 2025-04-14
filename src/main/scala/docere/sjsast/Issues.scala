package docere.sjsast

case class Issues(ics: Set[IssueCoordinate]) extends SjsNode:
  override val name = "Issues"

  def merge(i: Issues): Issues =
    val x = ics merge i.ics
    Issues(x)

  override def merge(i: SjsNode): SjsNode =
    merge(i.asInstanceOf[Issues])

object Issues:
  def apply(i: GenAst.Issues): Issues =
    val coords = i.coord.toList.map { IssueCoordinate(_) }.toSet
    Issues(coords)
