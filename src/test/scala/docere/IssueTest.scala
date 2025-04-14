package docere.sjsast
import docere.testutils.*
import docere.sjsast

class IssueTest extends AuroraAsyncTest :

  // File existence test
  "test files issues1a.aurora and issues1b.aurora" should {
    "exist" in {
      val issues1 = testIssuesAurora(0)
      val issues2 = testIssuesAurora(1)
      val issues3 = testIssuesAurora(2)
      info(issues1)

      Future {
        checkFileAccess(issues1) should be(true)
        checkFileAccess(issues2) should be(true)
      }
    }
  }

  // Main Issues Merge Test
  "Issue" should {
    "work" in {
      val issues1 = testIssuesAurora(0)
      val issues2 = testIssuesAurora(1)
      val issues3 = testIssuesAurora(2)

      for {
        p1 <- parse(issues1).toFuture
        p2 <- parse(issues2).toFuture
        p3 <- parse(issues3).toFuture
      } yield {

        val result = sjsast.PCM(p1) merge sjsast.PCM(p2)
        val expected = sjsast.PCM(p3)
        val issueResult = result.cio("Issues").asInstanceOf[Issues]
        val issueExpected = expected.cio("Issues").asInstanceOf[Issues]
        issueResult.ics should contain theSameElementsAs issueExpected.ics
        issueResult.narrative should contain theSameElementsAs issueExpected.narrative
      }
    }
  }