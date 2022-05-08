package com.pranjutgogoi

import org.openqa.selenium.firefox.{FirefoxDriver, FirefoxOptions}

import java.io.File
import scala.util.{Failure, Success, Try}

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

    val options = new FirefoxOptions
    options.setBinary(binary)
    options.setProfile(torProfile)
    options.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options)
    val driver = new FirefoxDriver(options)
    Try{
      driver.get("https://www.google.com")
      Thread.sleep(10 * 1000)
    } match {
      case Success(value) => driver.quit()
      case Failure(exception) => driver.quit()
    }

    driver
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
