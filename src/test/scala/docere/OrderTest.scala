package docere.sjsast
import docere.testutils.*
import docere.sjsast

class OrderTest extends AuroraAsyncTest :

  // File existence test
  "test files orders1.aurora and orders2.aurora" should {
    "exist" in {
      val orders1 = testOrdersAurora(0)
      val orders2 = testOrdersAurora(1)
      val orders3 = testOrdersAurora(2)
      info(orders1)
      Future {
        checkFileAccess(orders1) should be(true)
        checkFileAccess(orders2) should be(true)
      }
    }
  }

  // Main clinical PCM merge test
  "Orders" should {
    "work" in {
      val orders1 = testOrdersAurora(0)
      val orders2 = testOrdersAurora(1)
      val orders3 = testOrdersAurora(2)

      for {
        p1 <- parse(orders1).toFuture
        p2 <- parse(orders2).toFuture
        p3 <- parse(orders3).toFuture
      } yield {
        val result = sjsast.PCM(p1) merge sjsast.PCM(p2)
        val expected = sjsast.PCM(p3)

        val orderResult = result.cio("Orders").asInstanceOf[Orders]
        val orderExpected = expected.cio("Orders").asInstanceOf[Orders]
        orderResult.ngo should contain theSameElementsAs orderExpected.ngo
        orderResult.narrative should contain theSameElementsAs orderExpected.narrative
      }
    }
  }