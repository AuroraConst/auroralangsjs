package docere.sjsast
import docere.testutils.*
import docere.sjsast


class PCMTest extends AuroraAsyncTest :
  val issues1a = testIssuesAurora(0)
  val issues1b = testIssuesAurora(1)
  val issues1c = testIssuesAurora(2)
  "test files issues1a.aurora and issues1b.aurora" should {
    "exist" in {
      Future{
        checkFileAccess(issues1a) should be(true)
        checkFileAccess(issues1b) should be(true)
      }
  }}

  "PCM" should { "work" in {
    for {
     p1 <-  parse(issues1a).toFuture  
     p2 <-  parse(issues1b).toFuture
     p3 <- parse(issues1c).toFuture
    }
    yield {
      val result = sjsast.PCM(p1) merge sjsast.PCM(p2)
      result should be(sjsast.PCM(p3))
        ppinfo(result)
      pending
    }
  }}





