package com.pranjutgogoi

import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite

import java.io.File
import scala.sys.process._
import scala.util.{ Failure, Success, Try }

class TorBrowserSpec extends AnyFunSuite with BeforeAndAfterAll {

  val path = new File(".").getCanonicalPath
  println(path)
  override def beforeAll(): Unit = {
    super.beforeAll()
//    Try(s"docker-compose -f ${path}/socks_proxy/docker-compose.yml up -d".!!) match {
//      case Success(_) =>
//      case Failure(ex) =>
//        s"docker-compose -f ${path}/../socks_proxy/docker-compose.yml up -d".!!
//    }
//    Thread.sleep(1 * 1000)
  }

  override def afterAll(): Unit = {
    super.afterAll()
//    Try(s"docker-compose -f ${path}/socks_proxy/docker-compose.yml down".!!) match {
//      case Success(_) =>
//      case Failure(ex) =>
//        s"docker-compose -f ${path}/../socks_proxy/docker-compose.yml down".!!
//    }
//    Thread.sleep(15 * 1000)
  }

  test("Tor test") {
    TorBrowser.init
    assert(true)
  }

}
