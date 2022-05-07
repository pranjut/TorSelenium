package com.pranjutgogoi

import org.scalatest.funsuite.AnyFunSuite

class TorBrowserSpec  extends AnyFunSuite {

  test("Tor test") {
    TorBrowser.init
    Thread.sleep(10 * 1000)
  }

}
