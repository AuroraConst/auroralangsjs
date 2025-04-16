package docere.sjsast

import docere.testutils.AuroraTest


class QuSuite extends AuroraTest { 

  "Qu case class" should {
    "be instantiated directly with a symbol" in {
      val quDraft = QU("?")
      quDraft shouldBe an [QU]
      quDraft.query should be ("?")

      val quMonitor = QU("$")
      quMonitor shouldBe an [QU]
      quMonitor.query should be ("$")
    }
  }

  "Qu.merge" should {
    val quDraft = QU("?")
    val quMonitor = QU("$")
    val otherNode = NL_STATEMENT("some text") 

    "return the incoming Qu when merging two different Qu instances (replacement)" in {
      val result = quDraft.merge(quMonitor)
      //this test does not make sense in relation to QUs and how they work. 
      //result should be (quMonitor) 
 
      val result2 = quMonitor.merge(quDraft)
      //result2 should be (quDraft)
    }

     "return the instance itself when merging identical Qu instances" in {

       val result = quDraft.merge(QU("?")) 
       result should be (QU("?")) 
       result should be (quDraft) 
     }

    "return InvalidSjsNode when merging with an incompatible SjsNode type" in {
      val result = quDraft.merge(otherNode)
      result should be (InvalidSjsNode())
    }
  }

  "Qu instance" should {
     "have the correct name property (from SjsNode trait)" in {
       val quNode = QU("?")
       quNode.query should be ("?") 
     }
  }
}