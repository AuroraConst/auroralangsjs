package docere.sjsast

case class PCM(cio:Map[String,Orders]) extends SjsNode :
  override val name = "PCM"

  def merge[PCM](p:PCM):PCM = 
    // val c = cio |+| p.cio
    // PCM( c)
    ???

  override def merge(p: SjsNode): SjsNode = merge(p.asInstanceOf)

    // PCM(this.cio |+| p.cio)


object PCM :      
  def apply(p:GenAst.PCM) :PCM = 
    val i = p.elements.toList
      .map(x => x.$type -> x)
      .map{(t,o) =>
        t match {
          // case "Issues" => t -> issues(o.asInstanceOf[GenAst.Issues])
          case "Orders" => t -> Orders.apply(o.asInstanceOf[GenAst.Orders])
          // case "Clinical" => t -> ???
        }

      }.toMap
    PCM(i)

