import sbt._

class TestPluginProject(info:ProjectInfo) extends PluginProject(info) {
  override def disableCrossPaths = true

  val junit = "junit" % "junit" % "4.5" % "test"
  val specs = "org.scala-tools.testing" % "specs" % "1.6.1" % "test"

  val nexusRepo = "nexus" at "https://nexus.griddynamics.net/nexus/content/groups/public"
}
