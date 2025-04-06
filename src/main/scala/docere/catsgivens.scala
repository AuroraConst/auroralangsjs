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

  //TODO NOT SURE ABOUT LIST as there can be duplicate elements
  given [T] :  BoundedSemilattice[List[T]] = new BoundedSemilattice[List[T]] {
    def empty: List[T] = List.empty
    def combine (x: List[T], y:List[T]) :  List[T] =
      if(x == empty) y
       else if (y==empty) x
       else x ++ y
  }  
  // given [T] : BoundedSemilattice[Set[T]] = new BoundedSemilattice[Set[T]] {
  //   def empty: Set[T] = Set.empty
  //   def combine (x: Set[T], y:Set[T]) :  Set[T] =
  //     if(x == empty) y
  //      else if (y==empty) x
  //      else x union y
  // }

  // given [T <: SjsAst.SjsNode] : BoundedSemilattice[Set[T]] = new BoundedSemilattice[Set[T]] {
  //   def empty: Set[T] = Set.empty
  //   def combine (x: Set[T], y:Set[T]) :  Set[T] =
  //     println("**********************called****8")
  //     if(x == empty) y
  //      else if (y==empty) x
  //      else (x.asMap |+| y.asMap).map{(k,v) => v}.toSet
  // }     


  //TODO test this now!~!!!!
  given  BoundedSemilattice[Map[String,SjsAst.SjsNode]] = new BoundedSemilattice[Map[String,SjsAst.SjsNode]] {
    def empty: Map[String,SjsAst.SjsNode] = Map.empty
    def combine (x: Map[String,SjsAst.SjsNode], y:Map[String,SjsAst.SjsNode]) :  Map[String,SjsAst.SjsNode] =
      if(x == empty) y
        else if (y==empty) x
        else {
          val keys = x.keySet union y.keySet

          val result = keys.map{k => 
            (x.get(k),y.get(k)) match {
              case (Some(x),Some(y)) => k -> x.merge(y)
              case (Some(x),None) =>    k -> x
              case (None,Some(y)) =>    k -> y
              case (None,None)    =>    k -> SjsAst.InvalidSjsNode()
            }
          }.toMap    

          
          result
      }
  }









  
end catsgivens
