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
