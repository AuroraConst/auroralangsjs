package docere.sjsast

case class NGC (name:String, ccoords:Set[ClinicalCoordinate])  extends SjsNode {
  def merge(n:NGC):NGC = 
    NGC(name,ccoords merge n.ccoords)
  override def merge(p: SjsNode): SjsNode = merge(p.asInstanceOf[NGC])


} 

