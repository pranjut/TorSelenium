package com.pranjutgogoi

import org.openqa.selenium.firefox.{FirefoxDriver, FirefoxOptions}

import java.io.File
import scala.util.{Failure, Success, Try}
import java.util.concurrent.TimeUnit

object FirefoxBrowser {
  import Manager._
  System.setProperty("webdriver.gecko.driver", firefoxDriverUrl)

  def init: FirefoxDriver = {
    val options = new FirefoxOptions()
    implicit val driver: FirefoxDriver = new FirefoxDriver(options)
    driver.get("about:preferences#privacy")
    driver.findElementById("customRadio").click()
    driver.findElementById("contentBlockingBlockCookiesCheckbox").click()
    driver.findElementById("contentBlockingTrackingProtectionCheckbox").click()
    driver.findElementById("contentBlockingCryptominersCheckbox").click()
    driver.findElementById("contentBlockingFingerprintersCheckbox").click()
    driver.findElementByClassName("reload-tabs-button").click()
    //  driver.findElementById("doNotTrackRadioGroup").click()
    driver.get("https://www.google.com")
    driver
  }
}

object TorBrowser {
  import Manager._
  import org.openqa.selenium.firefox.{ FirefoxBinary, FirefoxDriver, FirefoxOptions, FirefoxProfile }
  //  /Users/personal_workspace/minions/drivers/geckodriver
  //  WEBDRIVER_GECKO_DRIVER=System.setProperty("webdriver.firefox.marionette", ".\\geckodriver.exe")

  // /Users/personal_workspace/TorBrowser/drivers/geckodriver
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
    val tor = new FirefoxDriver(torOptions)

    val profile = new FirefoxProfile
    profile.setPreference("network.proxy.type", 1)
    profile.setPreference("network.proxy.socks", "127.0.0.1")
    profile.setPreference("network.proxy.socks_port", 9150)
    val options = new FirefoxOptions()
    options.setProfile(profile)
    options.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options)

    val orgFirefox = new FirefoxDriver(options)

//    val driver = new FirefoxDriver(options)
//    Try{
//      Thread.sleep(10 * 1000)
//      driver.manage.timeouts.implicitlyWait(30, TimeUnit.SECONDS)
//      driver.get("https://www.google.com")
//      Thread.sleep(10 * 1000)
//    } match {
//      case Success(value) => driver.quit()
//      case Failure(exception) => driver.quit()
//    }
//
//    driver
  }
}

/*
System.setProperty("webdriver.firefox.marionette", ".\\geckodriver.exe");
        String torPath = "C:\\Users\\HP\\Desktop\\Tor Browser\\Browser\\firefox.exe";
        String profilePath = "C:\\Users\\HP\\Desktop\\Tor Browser\\Browser\\TorBrowser\\Data\\Browser\\profile.default";

        File torProfileDir = new File(profilePath);
        FirefoxBinary binary = new FirefoxBinary(new File(torPath));
        FirefoxProfile torProfile = new FirefoxProfile(torProfileDir);

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(binary);
        options.setProfile(torProfile);
        options.setCapability(FirefoxOptions.FIREFOX_OPTIONS,options);
        WebDriver driver = new FirefoxDriver(options);

 */
