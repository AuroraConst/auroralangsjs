package docere


import cats.kernel.BoundedSemilattice
import docere.ccnode.SjsAst.Issues
object catsgivens :
  given [T] :  BoundedSemilattice[Option[T]] = new BoundedSemilattice[Option[T]] {
    def empty: Option[T] = None
    def combine(x: Option[T], y: Option[T]): Option[T] = 
      if (x == empty) y
      else if (y == empty) x
      else x
  }


  import docere.ccnode.SjsAst
  import cats.syntax.semigroup._ // for |+|
  
  
  given BoundedSemilattice[SjsAst.Issues] = new BoundedSemilattice[SjsAst.Issues] {
    def empty: Issues = Issues(Set.empty)
    def combine(x: Issues, y: Issues): Issues = Issues(x.ics union  y.ics)
  }

  given  BoundedSemilattice[SjsAst.PCM] = new BoundedSemilattice[SjsAst.PCM] {
    def empty: SjsAst.PCM = SjsAst.PCM(None)
    def combine(x: SjsAst.PCM, y: SjsAst.PCM): SjsAst.PCM = 
      if (x == empty) y
      else if (y == empty) x
      else 
        val is = x.issues.get |+| y.issues.get
        SjsAst.PCM(Some(is))
  }





  
end catsgivens
