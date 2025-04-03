package docere


import cats.kernel.BoundedSemilattice
import cats.syntax.semigroup._ // for |+|


object catsgivens :
  given [T] :  BoundedSemilattice[Option[T]] = new BoundedSemilattice[Option[T]] {
    def empty: Option[T] = None
    def combine(x: Option[T], y: Option[T]): Option[T] = 
      if (x == empty) y
      else if (y == empty) x
      else x |+| y
  }

  given [T] :  BoundedSemilattice[List[T]] = new BoundedSemilattice[List[T]] {
    def empty: List[T] = List.empty
    def combine (x: List[T], y:List[T]) :  List[T] =
      if(x == empty) y
       else if (y==empty) x
       else x ++ y
  }  


  
  given [T] : BoundedSemilattice[Set[T]] = new BoundedSemilattice[Set[T]] {
    def empty: Set[T] = Set.empty
    def combine (x: Set[T], y:Set[T]) :  Set[T] =
      if(x == empty) y
       else if (y==empty) x
       else x union y
  }

  given [T <: SjsAst.SjsNode] : BoundedSemilattice[Map[String,Set[T]]] = new BoundedSemilattice[Map[String,Set[T]]] {
    def empty: Map[String,Set[T]] = Map.empty
    def combine (x: Map[String,Set[T]], y:Map[String,Set[T]]) :  Map[String,Set[T]] =
      if(x == empty) y
       else if (y==empty) x
       else {
          val xkeys = x.keySet
          val ykeys = y.keySet
          val a = xkeys.map{k => k -> (x(k) |+| y.get(k).getOrElse(Set.empty)) }  //TODO: NEED TO CHECK if y(x) is empty, I like toOption.getOrElse(Map.empty)
          val b = ykeys diff xkeys map{k => k -> (x(k) |+| y(k)) }
          (a union b).toList.map{(k,v) => k -> v}.toMap
       }
  }

  given BoundedSemilattice[SjsAst.Issues] = new BoundedSemilattice[SjsAst.Issues] {
    def empty: SjsAst.Issues = SjsAst.Issues(Set.empty)
    def combine(x: SjsAst.Issues, y: SjsAst.Issues): SjsAst.Issues = SjsAst.Issues(x.ics |+|  y.ics)
  }

  type CIO = SjsAst.Clinical | SjsAst.Issues | SjsAst.Orders
  given BoundedSemilattice[CIO] = new BoundedSemilattice[CIO] {
    def empty = SjsAst.Clinical(Set.empty)
    def combine(x: CIO, y: CIO): CIO =  x match {
        case c:SjsAst.Clinical => SjsAst.Clinical(c.ngc |+| y.asInstanceOf[SjsAst.Clinical].ngc)
        case i:SjsAst.Issues   => SjsAst.Issues(i.ics |+| y.asInstanceOf[SjsAst.Issues].ics)
        case o:SjsAst.Orders   => 
          val x = o.ngo.map{(k,cc) => k -> cc.set}
          val x1 = y.asInstanceOf[SjsAst.Orders].ngo.map{(k,cc) => k -> cc.set}
          val r = x |+| x1
          val mapNGO = r.map{(k,v) => k -> SjsAst.NGO(k,v)}
          SjsAst.Orders(mapNGO) 
      }
  }

  

  given  BoundedSemilattice[SjsAst.PCM] = new BoundedSemilattice[SjsAst.PCM] {

    def empty: SjsAst.PCM = SjsAst.PCM(Map.empty)
    def combine(x: SjsAst.PCM, y: SjsAst.PCM): SjsAst.PCM = 
      if (x == empty) y
      else if (y == empty) x
      else 
        SjsAst.PCM(x.cio |+| y.cio)
  }

  //TODO THINK OF ADDING BoundedSemilattice[SjsAst.Orders] and BoundedSemilattice[NGO] is it worht it?






  
end catsgivens
