package docere.sjsast

case class NGC (name:String, ccoords:Set[ClinicalCoordinate])  extends SjsNode {
  override def merge(p: SjsNode): SjsNode = ???

} 

