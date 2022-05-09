package com.pranjutgogoi

import org.openqa.selenium.firefox.FirefoxBinary

import java.io.File
import scala.util.{Failure, Success, Try}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object TorBrowser {
  import Manager._
  import org.openqa.selenium.firefox.{ FirefoxDriver, FirefoxOptions, FirefoxProfile }

  def init = {
    System.setProperty("webdriver.gecko.driver", firefoxDriverUrl)
    val torPath = "/home/crd/.local/share/torbrowser/tbb/x86_64/tor-browser_en-US/Browser/start-tor-browser"
    val profilePath = "/home/crd/.local/share/torbrowser/tbb/x86_64/tor-browser_en-US/Browser/TorBrowser/Data/Browser/profile.default"

    val torProfileDir = new File(profilePath)
    val binary = new FirefoxBinary(new File(torPath))
    val torProfile = new FirefoxProfile(torProfileDir)
    torProfile.setPreference("webdriver.load.strategy", "unstable")

    val torOptions = new FirefoxOptions
    torOptions.setBinary(binary)
    torOptions.setProfile(torProfile)
    torOptions.setCapability(FirefoxOptions.FIREFOX_OPTIONS, torOptions)
    Try{
      Future(new FirefoxDriver(torOptions))
      Thread.sleep(15 * 1000)
    } match {
      case _ => println("Just starting might get failed")
    }



    val profile = new FirefoxProfile
    profile.setPreference("network.proxy.type", 1)
    profile.setPreference("network.proxy.socks", "127.0.0.1")
    profile.setPreference("network.proxy.socks_port", 9150)
    val options = new FirefoxOptions()
    options.setProfile(profile)
    options.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options)

    val driver = new FirefoxDriver(options)

    Try {
//      val url = s"https://duckduckgo.com/?q=zaloni+twitter&t=h_&ia=web"
      val url = s"https://www.crunchbase.com/organization/zaloni"
      driver.get(url)
      Thread.sleep(3 * 1000)
    } match {
      case Success(value) => driver.quit()
      case Failure(exception) =>
        exception.printStackTrace()
        driver.quit()
    }

    driver
  }
}
