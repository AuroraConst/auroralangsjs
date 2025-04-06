package com.axiom

import testingutils.*


class AuroraParserTest extends AuroraAsyncTest {

  // override implicit def executionContext = global

  "Test files" should {
    "exist" in {
      info(s"Platform: ${platform}")
      
      // info(s"checkFiles: $checkFiles")    
      val checkFiles =  testFilesMath
          .map{fn => 
            info(s"filename: $fn")
            checkFileAccess(fn)}
          .map{ _ == true }

      Future(checkFiles) map { l =>
        l.forall(_ == true) should be (true) }
      }
  }


}

