package docere
import testingutils.*


class OrderCoordinateTest extends AuroraTest :
  
  "test files issues1b.aurora and issues1a.aurora" should {
    "exist" in {
      import contructors.*
      import SjsAst.*
      val refCoordsX1 = refs(Set("a","b"))
      val refCoordsX2 = refs(Set("b","c"))
      val ocoordsX1 = ocoords(Set("x","y"),refCoordsX1)
      val ocoordsX2 = ocoords(Set("y"),refCoordsX2)

      import catsgivens.given


      val result = combine(ocoordsX1,ocoordsX2)  

      val ngo1 = Set ( NGO("NGO1",ocoordsX1) )
      val ngo2 = Set ( NGO("NGO2",ocoordsX2) )
      val ngoresult = combine(ngo1,ngo2)

      val s = pprint.apply(ngoresult)
      info(s"${s}")




      // val y = ocoordsX2.asMap
      // val z = x |+| y

    }
  }  




