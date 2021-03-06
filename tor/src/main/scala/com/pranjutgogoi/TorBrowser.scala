package com.pranjutgogoi

import org.openqa.selenium.By
import org.openqa.selenium.By.ById
import org.openqa.selenium.firefox.{FirefoxBinary, ProfilesIni}
import org.openqa.selenium.remote.RemoteWebDriver

import java.io.File
import java.net.URL
import scala.util.{Failure, Success, Try}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.jdk.CollectionConverters._

object TorBrowser {

  import Manager._
  import org.openqa.selenium.firefox.{ FirefoxDriver, FirefoxOptions, FirefoxProfile }

    System.setProperty("webdriver.gecko.driver", firefoxDriverUrl)

  def startTorBrowser = {
    val torPath = "/home/crd/.local/share/torbrowser/tbb/x86_64/tor-browser_en-US/Browser/firefox"
    val profilePath = "/home/crd/.local/share/torbrowser/tbb/x86_64/tor-browser_en-US/Browser/TorBrowser/Data/Browser/profile.default"

    val torProfileDir = new File(profilePath)
    val binary = new FirefoxBinary(new File(torPath))
    val torProfile = new FirefoxProfile(torProfileDir)

    torProfile.setPreference("network.proxy.type", 1)
    torProfile.setPreference("network.proxy.socks", "127.0.0.1")
    torProfile.setPreference("network.proxy.socks_port", "9150")
    torProfile.setPreference("extensions.torbutton.inserted_button", true)
    torProfile.setPreference("extensions.torbutton.launch_warning", false)
    torProfile.setPreference("extensions.torbutton.loglevel", 2)
    torProfile.setPreference("extensions.torbutton.logmethod", 0)
    torProfile.setPreference("extensions.torlauncher.prompt_at_startup", true)
    torProfile.setPreference("extensions.torlauncher.start_tor", true)
    torProfile.setPreference("extensions.torbutton.prompted_language", true)

    val torOptions = new FirefoxOptions
    torOptions.setBinary(binary)
    torOptions.setProfile(torProfile)

    torOptions.setCapability(FirefoxOptions.FIREFOX_OPTIONS, torOptions)
    Try {
      val driver = new FirefoxDriver(torOptions)
      import org.openqa.selenium.support.ui.WebDriverWait
      import java.util.concurrent.TimeUnit
      driver.manage.timeouts.implicitlyWait(0, TimeUnit.SECONDS)
      var wait = new WebDriverWait(driver, 10)

      driver.get("https://whatismyipaddress.com/")
      Thread.sleep(10 * 1000)
//      driver.get("https://whatismyipaddress.com/")
//      Thread.sleep(10 * 1000)
//      driver.get("https://whatismyipaddress.com/")
//      Thread.sleep(3 * 1000)
      driver.quit()
    } match {
      case Failure(ex) => ex.printStackTrace()
      case _ =>
        println("Just starting might get failed")
    }
  }

  def init = {
    //    val profile = new FirefoxProfile(new File("/home/crd/.mozilla/firefox/7it7g9uu.default-release"))
    val profile = new FirefoxProfile(new File("/Users/bi82jb/Library/Application Support/Firefox/Profiles/02szyx88.default-release"))
    //    val profile = new FirefoxProfile()

    //    import org.openqa.selenium.firefox.FirefoxProfile
    //    val profileIni = new ProfilesIni
    //    val profile = profileIni.getProfile("default")
    val options = new FirefoxOptions()
    options.setProfile(profile)
    options.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options)
    //    options.addArguments("user-data-dir=/home/crd/.mozilla/firefox/7it7g9uu.default-release")

    val driver = new FirefoxDriver(options)

    driver.get("about:preferences#privacy")
    //    driver.findElementById("customRadio").click()
    //    driver.findElementById("contentBlockingBlockCookiesCheckbox").click()
    //    driver.findElementById("contentBlockingTrackingProtectionCheckbox").click()
    //    driver.findElementById("contentBlockingCryptominersCheckbox").click()
    //    driver.findElementById("contentBlockingFingerprintersCheckbox").click()
    //    driver.findElementByClassName("reload-tabs-button").click()
    //
    //    driver.findElementById("connectionSettings").click()
    //    driver.switchTo().frame(1)
    //    driver.findElementById("networkProxyType").findElements(By.tagName("radio")).asScala.toList
    //      .filter(_.getAttribute("label").equals("Manual proxy configuration")).headOption.foreach(_.click())
    //    driver.findElementById("networkProxySOCKS").sendKeys("127.0.0.1")
    //    driver.findElementById("networkProxySOCKS_Port").sendKeys("9150")
    //
    //    import org.openqa.selenium.Keys
    //    driver.switchTo.activeElement.sendKeys(Keys.ENTER)
    Thread.sleep(10 * 1000)
    Try {
      //      val url = s"https://duckduckgo.com/?q=zaloni+twitter&t=h_&ia=web"
      val url = s"https://www.crunchbase.com/organization/zaloni"
      driver.get(url)
      Thread.sleep(2 * 60 * 1000)
    } match {
      case Success(value) => driver.quit()
      case Failure(exception) =>
        exception.printStackTrace()
        driver.quit()
    }

    driver
  }

  //  def init4 = {
  //  System.setProperty("webdriver.gecko.driver", "/Users/bi82jb/personal_workspace/gecko/testing/geckodriver/target/debug/geckodriver")
  //    val firefoxOptions = new FirefoxOptions
  //    val driver = new RemoteWebDriver(new URL("http://www.example.com"), firefoxOptions)
  //    driver.get("http://www.google.com")
  //  }

}
