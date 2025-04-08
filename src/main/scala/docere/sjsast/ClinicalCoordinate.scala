package docere.sjsast

case class ClinicalCoordinate (name :String) extends SjsNode:

  def merge(cc:ClinicalCoordinate):ClinicalCoordinate = 
    this
  override def merge(p: SjsNode): SjsNode = merge(p.asInstanceOf[ClinicalCoordinate])


object ClinicalCoordinate{
  def apply (c: GenAst.ClinicalCoordinate): ClinicalCoordinate = 
    ClinicalCoordinate(c.name)
}