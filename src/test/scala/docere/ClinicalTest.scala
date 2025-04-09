package docere.sjsast
import docere.testutils.*
import docere.sjsast

class ClinicalTest extends AuroraAsyncTest :

  // File existence test
  "test files clinicals1.aurora and clinicals2.aurora" should {
    "exist" in {
      val clinicals1 = testClinicalsAurora(0)
      val clinicals2 = testClinicalsAurora(1)
      val clinicals3 = testClinicalsAurora(2)
      info(clinicals1)

      Future {
        checkFileAccess(clinicals1) should be(true)
        checkFileAccess(clinicals2) should be(true)
      }
    }
  }

  // Main clinical PCM merge test
  "Clinical" should {
    "work" in {
      val clinicals1 = testClinicalsAurora(0)
      val clinicals2 = testClinicalsAurora(1)
      val clinicals3 = testClinicalsAurora(2)

      for {
        p1 <- parse(clinicals1).toFuture
        p2 <- parse(clinicals2).toFuture
        p3 <- parse(clinicals3).toFuture
      } yield {
        // val result = PCM(sjsast.PCM(p1) merge sjsast.PCM(p2))
        // val expected = sjsast.PCM(p3)

        val result = sjsast.PCM(p1) merge sjsast.PCM(p2)
        val expected = sjsast.PCM(p3)

        // TODO Use the ShowAurora
        // Pretty print both
        // ppinfo("=== RESULT ===")
        // ppinfo(result)

        // ppinfo("=== EXPECTED ===")
        // ppinfo(expected)

        // ppinfo("===SHOW===")
        // ppinfo(Clinical(clinicals1 merge clinical2).show)

        // var pc1 = sjsast.PCM
        // var pc2 = sjsast.PCM

        // sjsast.PCM(pc1.merge(pc2)).show
        // info(result)

        // ppinfo("===Result===")
        // result.show
        // ppinfo(result.show)

        // expected.show
        // ppinfo(expected.show)

        // result shouldBe expected
        // result.ccoord should contain theSameElementsAs expected.ccoord
        // result shouldEqual expected
        val clinicalResult = result.cio("Clinical").asInstanceOf[Clinical]
        val clinicalExpected = expected.cio("Clinical").asInstanceOf[Clinical]
        clinicalResult.ngc should contain theSameElementsAs clinicalExpected.ngc
        clinicalResult.narrative should contain theSameElementsAs clinicalExpected.narrative
      }
    }
  }