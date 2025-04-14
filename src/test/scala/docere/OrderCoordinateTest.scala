package docere.sjsast
import docere.testutils.*
import docere.contructors.*

class OrdersAndChildrenTest extends AuroraTest:

  "RefCoordinates" should {
    "merge" in {
      val refCoordsX1 = refs(Set("a", "b"))
      val refCoordsX2 = refs(Set("b", "c"))

      ppinfo(refCoordsX1.merge(refCoordsX2))
    }
  }

  "OrderCoordinates" should {
    "merge" in {
      val refCoordsX1 = refs(Set("a", "b"))
      val refCoordsX2 = refs(Set("b", "c"))
      val ocoordsX1 = ocoords(Set("x", "y"), refCoordsX1)
      val ocoordsX2 = ocoords(Set("y", "z"), refCoordsX2)

      import cats.syntax.show._
      val result = NGO("NGO", ocoordsX2.merge(ocoordsX1)).show

      info(result)
    }
  }
