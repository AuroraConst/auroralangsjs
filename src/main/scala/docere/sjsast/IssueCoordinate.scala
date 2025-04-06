package docere.sjsast


case class IssueCoordinate (name:String) extends SjsNode :

  def merge(i: IssueCoordinate): IssueCoordinate = i

  override def merge(p: SjsNode): SjsNode = ???


object IssueCoordinate :
  def apply(i: GenAst.IssueCoordinate): IssueCoordinate = 
    IssueCoordinate(i.name)
