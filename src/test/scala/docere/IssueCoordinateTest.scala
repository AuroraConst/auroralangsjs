package docere.sjsast
import docere.testutils.*
import docere.contructors.*

class IssueCoordinateTest extends AuroraTest:

  "RefCoordinates" should {
    "merge" in {
      val refCoordsX1 = refs(Set("a", "b"))
      val refCoordsX2 = refs(Set("b", "c"))

      ppinfo(refCoordsX1.merge(refCoordsX2))
    }
  }

  "IssueCoordinates" should {
    "merge" in {
      val refCoordsX1 = refs(Set("a", "b"))
      val refCoordsX2 = refs(Set("b", "c"))
      val icoordsX1 = icoords(Set("chf"), refCoordsX1)
      val icoordsX2 = icoords(Set("chf"), refCoordsX2)

      import cats.syntax.show._
      val result = Issues(icoordsX2.merge(icoordsX1)).show

      info(result)
    }
  }
