import Dependencies.{sparkCore, sparkHive, sparkKafka, sparkMLLib, sparkSql, sparkStreaming}
import sbt._
//import com.sun.xml.internal.bind.v2.TODO

object Dependencies extends AutoPlugin {

  import Version._


  lazy val akkaHttpVersion = "10.2.0"

  lazy val akkahttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
  lazy val akkastream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
  lazy val akkatyped = "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion
  lazy val sprayJson = "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion

  object Util{
//    val jsonNative = ("org.json4s" %% "json4s-native" % "3.5.0").exclude("joda-time", "joda-time")
//    val jsonExt = ("org.json4s" %% "json4s-ext" % "3.5.0").exclude("joda-time", "joda-time")
    val joda = "joda-time" % "joda-time" % "2.9.2"
    val typesafeConfig = "com.typesafe" % "config" % "1.4.0"
    val log4jApiScala = "org.apache.logging.log4j" %% "log4j-api-scala" % "12.0"
    val log4jApi = "org.apache.logging.log4j" % "log4j-api" % "2.12.0"
    val log4jCore = "org.apache.logging.log4j" % "log4j-core" % "2.12.0"
    val scopt = "com.github.scopt" %% "scopt" % scoptVersion
    val allUtilDeps = Seq(joda, typesafeConfig, log4jApiScala, log4jApi, log4jCore, scopt)
  }

  object Play {
    val playJsonElastic4s = "com.sksamuel.elastic4s" %% "elastic4s-play-json" % "6.3.1"

    val version = SettingKey[String]("play.version")

    val json = "com.typesafe.play" %% "play-json" % jsonVersion exclude("ch.qos.logback", "logback-classic")
    val jsonDerivedCodecs = "org.julienrf" %% "play-json-derived-codecs" % "3.3"
    val jwtPlayJson = "com.pauldijou" %% "jwt-play-json" % "0.16.0"
    val jsonJodaTime = "com.typesafe.play" %% "play-json-joda" % jsonVersion exclude("ch.qos.logback", "logback-classic")
    val ws = "com.typesafe.play" %% "play-ws-standalone" % "2.1.2"
    val shaded = "com.typesafe.play" % "shaded-asynchttpclient" % "1.1.7"
    val ahc = "com.typesafe.play" %% "play-ahc-ws-standalone" % "2.1.2"
    val allPlayDeps = Seq(playJsonElastic4s, json, jsonDerivedCodecs, jwtPlayJson, jsonJodaTime, ws, shaded, ahc)
  }

  lazy val sparkCore = "org.apache.spark" %% "spark-core" % sparkVersion % "provided"
  lazy val sparkMLLib = "org.apache.spark" %% "spark-mllib" % sparkVersion % "provided"
  lazy val sparkStreaming = "org.apache.spark" %% "spark-streaming" % sparkVersion % "provided"
  lazy val sparkSql = "org.apache.spark" %% "spark-sql" % sparkVersion % "provided"
  lazy val sparkSqlKafka =  "org.apache.spark" %% "spark-sql-kafka-0-10" % sparkVersion % "provided"
  lazy val sparkHive = "org.apache.spark" %% "spark-hive" % sparkVersion % "provided"
  lazy val sparkKafka = "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion % "provided"

  lazy val allSparks = Seq(sparkCore, sparkMLLib, sparkStreaming, sparkSql, sparkHive, sparkKafka, sparkSqlKafka)

  lazy val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVer % Test
  lazy val hdfsTest = "org.apache.hadoop" % "hadoop-hdfs" % hdfsVersion % Test
  lazy val hdfsMiniClusterTest = "org.apache.hadoop" % "hadoop-minicluster" % hdfsVersion % Test
  lazy val mockito = "org.mockito" %% "mockito-scala" % mockitoVersion

  lazy val okhttp = "com.squareup.okhttp" % "okhttp" % "2.7.5"
  lazy val jbrowser = "com.machinepublishers" % "jbrowserdriver" % "1.1.1"

  lazy val selenium = "org.seleniumhq.selenium" % "selenium-java" % "3.141.59"
  lazy val htmlUnitDriver = "org.seleniumhq.selenium" % "htmlunit-driver" % "2.43.1"
  lazy val seleniumChrome = "org.seleniumhq.selenium" % "selenium-chrome-driver" % "3.141.59"
  lazy val seleniumFirefox = "org.seleniumhq.selenium" % "selenium-firefox-driver" % "3.141.59"
//  lazy val seleniumChrome = "org.seleniumhq.selenium" % "selenium-chrome-driver" % "4.0.0-alpha-7"

  lazy val jsoup = "org.jsoup" % "jsoup" % "1.13.1"

  lazy val testDeps = Seq(scalaTest, hdfsTest, hdfsMiniClusterTest, mockito)

  lazy val akkahttpDeps = Seq(akkatyped, akkahttp, akkastream, sprayJson)

  lazy val circeJson = Seq("io.circe" %% "circe-core", "io.circe" %% "circe-generic", "io.circe" %% "circe-parser").map(_ % circeVersion)

  lazy val circeAkkahttp = "de.heikoseeberger" %% "akka-http-circe" % "1.35.0"

  lazy val hadoopAWS = "org.apache.hadoop" % "hadoop-aws" % "3.2.0"
  lazy val hadoopCommon = "org.apache.hadoop" % "hadoop-common" % "3.2.0"

  lazy val guava = "com.google.guava" % "guava" % "30.0-jre"

//  lazy val sparkCloud = "org.apache.spark" %% "spark-hadoop-cloud" % "3.0.0-palantir.82"

  //  lazy val jacksoncore = "com.fasterxml.jackson.core" % "jackson-core" % "2.9.3"
//  lazy val jacksondatabind = "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.3"
//  lazy val jacksonmodule =  "com.fasterxml.jackson.module" % "jackson-module-scala" % "2.9.3"


  object Version {
    lazy val akkaVersion                     = "2.6.5"
    lazy val akkaHttpVersion                 = "10.1.11"
    lazy val logbackVersion                  = "1.2.3"
    lazy val scalaVer                        = "2.11.0"
    lazy val scalaParsersVer                 = "1.1.2"
    lazy val scalaTestVer                    = "3.1.0"
    lazy val clusterShardingVersion          = "2.6.5"
    lazy val AlpakkaKafkaVersion             = "2.0.3"
    lazy val playJsonVer                     = "2.9.0"
    lazy val akkaManagementVersion           = "1.0.8"
    lazy val akkaEnhancementsVersion         = "1.1.12"
    lazy val sparkVersion                    = "3.0.1"
    lazy val kafkaVersion                    = "1.1.0"
    lazy val AkkaPersistenceCassandraVersion = "1.0.0"
    lazy val codecVersion                    = "1.11"
    lazy val jacksonVersion                  = "2.11.1"
    lazy val hdfsVersion                     = "3.2.0"
    lazy val mockitoVersion                  = "1.15.0"
    lazy val scoptVersion                    = "4.0.0-RC2"
    lazy val jsonVersion                     = "2.6.10"
    lazy val circeVersion                    = "0.12.3"

  }

}
