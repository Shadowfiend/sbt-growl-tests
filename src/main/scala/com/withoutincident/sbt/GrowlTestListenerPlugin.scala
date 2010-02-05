package com.withoutincident.sbt {

import _root_.sbt._

trait GrowlTestListenerPlugin extends BasicScalaProject {
  override def testListeners:Seq[TestReportListener] = List(new GrowlTestListener) ++ super.testListeners
}


}
