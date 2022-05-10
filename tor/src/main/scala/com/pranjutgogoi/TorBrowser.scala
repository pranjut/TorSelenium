package com.pranjutgogoi

import org.openqa.selenium.firefox.FirefoxBinary

import java.io.File
import scala.util.{Failure, Success, Try}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object TorBrowser {

  import Manager._
  import org.openqa.selenium.firefox.{FirefoxDriver, FirefoxOptions, FirefoxProfile}

  System.setProperty("webdriver.gecko.driver", firefoxDriverUrl)

  def startTorBrowser = {
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
    Try {
      val driver = new FirefoxDriver(torOptions)
      Thread.sleep(3 * 1000)
      driver.quit()
    } match {
      case _ => println("Just starting might get failed")
    }
  }


  def init = {
    val profile = new FirefoxProfile
    val options = new FirefoxOptions()
    options.setProfile(profile)
    options.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options)

    val driver = new FirefoxDriver(options)


    driver.get("about:preferences#privacy")
    driver.findElementById("customRadio").click()
    driver.findElementById("contentBlockingBlockCookiesCheckbox").click()
    driver.findElementById("contentBlockingTrackingProtectionCheckbox").click()
    driver.findElementById("contentBlockingCryptominersCheckbox").click()
    driver.findElementById("contentBlockingFingerprintersCheckbox").click()
    driver.findElementByClassName("reload-tabs-button").click()


    val extensionDir = extensionOnionDir
    val extensionName = extensionOnionName
    driver.installExtension(new File(s"${extensionDir}/${extensionName}").toPath)
    //    val extensions = driver.get("about:addons")
    //    driver.findElementByName("extension").click()

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
