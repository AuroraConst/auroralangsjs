package docere
 /**
  * has identity, idempotency and associative and commutative properties
  * 
  * while commutative operations are typically valued in parallel computing, distributed systems and concurrency,
  * in Functional programming, commutative operations can simplify reasoning about code and
  * enable more optimizations such as reordering of expressions
   */
object BoundedSemiLatticeExample :
  import cats.kernel.BoundedSemilattice
  

    // Define a bounded semilattice for set union
  given  setUnionBoundedSemilattice[A]: BoundedSemilattice[Set[A]] = new BoundedSemilattice[Set[A]] {
    def empty: Set[A] = Set.empty
    def combine(x: Set[A], y: Set[A]): Set[A] = x union y
  }

end BoundedSemiLatticeExample



  
  

  
  

