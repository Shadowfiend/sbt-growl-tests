import sbt._
import com.withoutincident.sbt._

class TestPluginProject(info:ProjectInfo) extends PluginProject(info) with GrowlTestListenerPlugin {
  //override def testListeners:Seq[TestReportListener] = Nil

  val junit = "junit" % "junit" % "4.5" % "test"
  val specs = "org.scala-tools.testing" % "specs" % "1.6.1" % "test"

  val nexusRepo = "nexus" at "https://nexus.griddynamics.net/nexus/content/groups/public"
}
