import Dependencies._

ThisBuild / scalaVersion := "2.13.8"

name := """TorBrowser"""

organization := "com.pranjutgogoi"

ThisBuild / useCoursier := false

val topDir = file(".")

lazy val root = (project in file("."))
  .settings(
    name := "Minions"
  ).aggregate(tor)

lazy val tor = (project in file("tor"))
  .settings(assemblySettings)
  .settings(libraryDependencies ++= Dependencies.Util.allUtilDeps
    ++ Seq(htmlUnitDriver, seleniumChrome, seleniumFirefox, jsoup) ++ testDeps)
  .settings( assembly / assemblyMergeStrategy := {
    case PathList("META-INF","services",xs @ _*) => MergeStrategy.filterDistinctLines
    case PathList("META-INF",xs @ _*) => MergeStrategy.discard
    case "application.conf" => MergeStrategy.concat
    case "reference.conf" => MergeStrategy.concat
    case _ => MergeStrategy.first
  })

val domain = "minions"

lazy val assemblySettings = Seq(
  assembly / assemblyOption := (assembly / assemblyOption).value,
  assembly / assemblyOutputPath := baseDirectory.value / "target" / "output" / s"${domain}-${name.value}.jar"
)
