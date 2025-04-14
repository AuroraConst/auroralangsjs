package docere.sjsast

case class QU(val query: String) extends SjsNode { 
  val name = "QU"

  override def merge(p: SjsNode) = p match {
    
    case qu: QU => qu 
    case _ => InvalidSjsNode() 
  }
}


// Can drop the apply method entirely because Scala case classes already provide an apply with String:
// object Narrative:
//   def apply(n: String): Narrative =
//     Narrative(n)