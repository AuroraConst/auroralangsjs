package docere.sjsast
import docere.testutils.*
import docere.sjsast
import docere.testutils.AuroraTest


class QuMerge extends AuroraTest { 

  // File existence test
  "test files qu1.aurora and qu22.aurora" should {
    "exist" in {
      val qu1 = testQUAurora(0)
      val qu2 = testQUAurora(1)
      val qu3 = testQUAurora(2)
      info(qu1)
      Future {
        checkFileAccess(qu1) should be(true)
        checkFileAccess(qu2) should be(true)
      }
    }
  }


  "QUmerge" should {
    "work" in {
      val qu1 = testQUAurora(0)
      val qu2 = testQUAurora(1)
      val qu3 = testQUAurora(2)

      for {
        p1 <- parse(qu1).toFuture
        p2 <- parse(qu2).toFuture
        p3 <- parse(qu3).toFuture
      } yield {
        val result = sjsast.PCM(p1) merge sjsast.PCM(p2)
        val expected = sjsast.PCM(p3)

        val quResult = result.cio("QU").asInstanceOf[QU]
        val quExpected = expected.cio("QU").asInstanceOf[QU]
        //orderResult.ngo should contain theSameElementsAs orderExpected.ngo
        // orderResult.narrative should contain theSameElementsAs orderExpected.narrative
        quResult should be (quExpected)
      }
    }
}
}