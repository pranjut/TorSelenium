package com.pranjutgogoi

import scala.util.{ Failure, Success, Try }

object TorBrowser {
  import Manager._
  import org.openqa.selenium.firefox.{ FirefoxDriver, FirefoxOptions, FirefoxProfile }

  def init = {
    System.setProperty("webdriver.gecko.driver", firefoxDriverUrl)

    val profile = new FirefoxProfile
    profile.setPreference("network.proxy.type", 1)
    profile.setPreference("network.proxy.socks", "127.0.0.1")
    profile.setPreference("network.proxy.socks_port", 9150)
    val options = new FirefoxOptions()
    options.setProfile(profile)
    options.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options)

    val driver = new FirefoxDriver(options)

    Try {
      val url = s"https://duckduckgo.com/?q=zaloni+twitter&t=h_&ia=web"
      driver.get(url)
      Thread.sleep(10 * 1000)
    } match {
      case Success(value) => driver.quit()
      case Failure(exception) =>
        exception.printStackTrace()
        driver.quit()
    }

    driver
  }
}
