import sbt._

class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
  val a = "com.withoutincident" % "sbt-growl-test" % "0.2.0-SNAPSHOT"
}
