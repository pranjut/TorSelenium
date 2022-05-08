package com.pranjutgogoi

import org.scalatest.funsuite.AnyFunSuite

class TorBrowserSpec  extends AnyFunSuite {

  test("Tor test") {
    TorBrowser.init
    Thread.sleep(20 * 1000)
  }

}
