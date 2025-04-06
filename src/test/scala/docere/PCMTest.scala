package docere
import testingutils.*
import SjsAst.*
import catsgivens.{given}

import cats.syntax.semigroup._ // for |+|

class PCMTest extends AuroraAsyncTest :
  val issues1a = testIssuesAurora(0)
  val issues1b = testIssuesAurora(1)
  "test files issues1a.aurora and issues1b.aurora" should {
    "exist" in {
      Future{
        checkFileAccess(issues1a) should be(true)
        checkFileAccess(issues1b) should be(true)
      }
  }}

  "PCM" should { "work" in {
    parse(issues1a).toFuture
    .map{ pcm =>
      pcm.elements.foreach{ node => 
      info(f"Asttype: ${node.$type}%16s ") }
      true should be(true)
    }
  }}





