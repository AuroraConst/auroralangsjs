package docere.sjsast
import docere.testutils.*
import docere.contructors.*


class OrdersAndChildrenTest extends AuroraTest :
  import cats.Show
  given Show[OrderCoordinate] =
    Show.show{
      (rc: OrderCoordinate) => 
        val result = rc.refs.map{_.name}.mkString(",") 
        val name = rc.name
        s"$name($result)"
    }

  "RefCoordinates" should {
    "merge" in {
      val refCoordsX1 = refs(Set("a","b"))
      val refCoordsX2 = refs(Set("b","c"))

      ppinfo(refCoordsX1.merge(refCoordsX2))
  }}

  "OrderCoordinates" should {
    "merge" in {
      val refCoordsX1 = refs(Set("a","b"))
      val refCoordsX2 = refs(Set("b","c"))
      val ocoordsX1 = ocoords(Set("x","y"),refCoordsX1)
      val ocoordsX2 = ocoords(Set("y","z"),refCoordsX2)


      import cats.syntax.show._ 
      val result = ocoordsX2.map{_.show}
      result.foreach(x => info(x))
  }}



      

