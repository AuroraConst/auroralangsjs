package docere.sjsast

import docere.testutils.AuroraTest


class QuSuite extends AuroraTest { 

  "Qu case class" should {
    "be instantiated directly with a symbol" in {
      val quDraft = Qu("?")
      quDraft shouldBe an [Qu]
      quDraft.name should be ("?")

      val quMonitor = Qu("$")
      quMonitor shouldBe an [Qu]
      quMonitor.name should be ("$")
    }
  }

  "Qu.merge" should {
    val quDraft = Qu("?")
    val quMonitor = Qu("$")
    val otherNode = Narrative("some text") 

    "return the incoming Qu when merging two different Qu instances (replacement)" in {
      val result = quDraft.merge(quMonitor) 
      result should be (quMonitor) 
 
      val result2 = quMonitor.merge(quDraft)
      result2 should be (quDraft)
    }

     "return the instance itself when merging identical Qu instances" in {

       val result = quDraft.merge(Qu("?")) 
       result should be (Qu("?")) 
       result should be (quDraft) 
     }

    "return InvalidSjsNode when merging with an incompatible SjsNode type" in {
      val result = quDraft.merge(otherNode)
      result should be (InvalidSjsNode())
    }
  }

  "Qu instance" should {
     "have the correct name property (from SjsNode trait)" in {
       val quNode = Qu("?")
       quNode.name should be ("?") 
     }
  }
}