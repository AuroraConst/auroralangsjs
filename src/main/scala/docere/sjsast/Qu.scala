package docere.sjsast

case class Qu(name: String) extends SjsNode { 

  override def merge(p: SjsNode): SjsNode = p match {
    
    case qu: Qu => qu 
    case _ => InvalidSjsNode() 
  }
}

