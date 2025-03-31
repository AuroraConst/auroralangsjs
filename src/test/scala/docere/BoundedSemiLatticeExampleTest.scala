package docere

import org.scalatest.* 
import wordspec.*, matchers.*, should.*

class BoundedSemiLatticeExampleTest extends  AnyWordSpec with Matchers {
  "Bounded Semi Lattice" should { "work this" in {
    import cats.syntax.semigroup._ // for |+|
    import BoundedSemiLatticeExample.given
    val set1 = Set(1, 2, 3)
    val set2 = Set(3, 4, 5)

    val combinedResult = set1 |+| set2
    combinedResult should be (Set(1, 2, 3, 4, 5))



    


    
     
  }
  }
}
  
