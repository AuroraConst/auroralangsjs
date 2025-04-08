package docere.sjsast


case class IssueCoordinate (name:String, ref: Set[RefCoordinate]) extends SjsNode :

  def merge(i: IssueCoordinate): IssueCoordinate = 
    val result = ref |+| i.ref
    IssueCoordinate(name, result)

  override def merge(p: SjsNode): SjsNode =
     merge(p.asInstanceOf[IssueCoordinate])

object IssueCoordinate :
  def apply(i: GenAst.IssueCoordinate): IssueCoordinate = 
    val resultRef = i.refs.toList.map{r =>  RefCoordinate(r.$refText)}.toSet
    IssueCoordinate(i.name, resultRef)
