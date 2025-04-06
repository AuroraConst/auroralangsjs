package docere
import testingutils.*
import SjsAst.*
import catsgivens.{given}

import cats.syntax.semigroup._ // for |+|

class PCMConstructionTest extends AuroraTest :
  
  import docere.contructors.*
  "Ref" should { "work" in {
    val refCoordsX1 = refs(Set("a","b"))
    val refCoordsX2 = refs(Set("b","c"))
    val ocoordsX1 = ocoords(Set("x","y"),refCoordsX1)
    val ocoordsX2 = ocoords(Set("y","z"),refCoordsX2)
  }}

