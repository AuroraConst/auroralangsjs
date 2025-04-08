package docere.sjsast
import docere.testutils.*

class PCMConstructionTest extends AuroraTest :
  
  import docere.contructors.*
  "Ref" should { "work" in {
    val refCoordsX1 = refs(Set("a","b"))
    val refCoordsX2 = refs(Set("b","c"))
    val ocoordsX1 = ocoords(Set("x","y"),refCoordsX1)
    val ocoordsX2 = ocoords(Set("y","z"),refCoordsX2)

    val ngoX1 = NGO("ngoX1",ocoordsX1)
    val ngoY2 = NGO("ngoX2",ocoordsX2)

    val ordersX = Orders(Set(ngoX1))
    val ordersY = Orders(Set(ngoY2))

    val cioX = Set(ordersX).asMap.map{(k,v) => k ->v.asInstanceOf[CIO]}

    val PCMX = docere.sjsast.PCM(cioX)
    info(PCMX.show)

    pending 
  }}

