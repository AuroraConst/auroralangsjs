package docere
import org.scalatest._
import wordspec._
import matchers._
import testingutils.*
import docere.ccnode.SjsAst



class PCMTest extends wordspec.AsyncWordSpec with should.Matchers :
  override implicit def executionContext = global
  
  
  val issues1a = testIssuesAurora(0)
  val issues1b = testIssuesAurora(1)

  "test files issues1a.aurora and issues1b.aurora" should {
    "exist" in {
      Future{
        checkFileAccess(issues1a) should be(true)
        checkFileAccess(issues1b) should be(true)
      }
    }
  }

  "PCM" should { "work" in {
    parse(issues1a).toFuture
    .map{ pcm =>
      pcm.elements.foreach{ node => 
      info(f"Asttype: ${node.$type}%16s ") }

      true should be(true)
    }

      
  }

  "PCM+PCM" should { "work" in {
    import cats.syntax.semigroup._ // for |+|
    import catsgivens.given
    for{
      pcm1 <- parse(issues1a).toFuture
      pcm2 <- parse(issues1b).toFuture
    }
    yield {
      val result = SjsAst.pcm(pcm1) |+| SjsAst.pcm(pcm2)
      println("**************************")
      println(SjsAst.pcm(pcm1))
      println(SjsAst.pcm(pcm2))
      println(s"$result")
      println(s"${result.text}")
      true should be(true)
    }
  }
  }
}
