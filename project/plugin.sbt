addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.2.4")

addSbtPlugin("com.typesafe.sbt" % "sbt-web" % "1.4.3")

addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.8.2")

// https://github.com/jrudolph/sbt-dependency-graph/
// Run `sbt dependencyTree` to see ASCII representation of dependencies
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.9.0")

// Static code analysis tools
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")

// Multi-JVM Plugin. Useful for running tests on clustered things
addSbtPlugin("com.typesafe.sbt" % "sbt-multi-jvm" % "0.4.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.7.3")

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.6.1")

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.2")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.10.0-RC1")

// Deploy fat JARs. Restart processes: https://github.com/sbt/sbt-assembly
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.6")

// sbt plugin for dockerization: https://github.com/sbt/sbt-native-packager
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.24")

// sbt plugin to build Docker Images: https://github.com/marcuslonnberg/sbt-docker
addSbtPlugin("se.marcuslonnberg" % "sbt-docker" % "1.5.0")
