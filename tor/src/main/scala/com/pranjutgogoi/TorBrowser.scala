package com.pranjutgogoi

import org.openqa.selenium.firefox.{FirefoxDriver, FirefoxOptions}

import java.io.File



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
  import org.openqa.selenium.firefox.{FirefoxBinary, FirefoxDriver, FirefoxOptions, FirefoxProfile}
//  /Users/bi82jb/personal_workspace/minions/drivers/geckodriver
//  WEBDRIVER_GECKO_DRIVER=System.setProperty("webdriver.firefox.marionette", ".\\geckodriver.exe")

  // /Users/bi82jb/personal_workspace/TorBrowser/drivers/geckodriver
  def init = {
    System.setProperty("webdriver.gecko.driver", firefoxDriverUrl)
    val torPath = "/home/crd/.local/share/torbrowser/tbb/x86_64/tor-browser_en-US/start-tor-browser.desktop"
    val profilePath = "/home/crd/.local/share/torbrowser/tbb/x86_64/tor-browser_en-US/Browser/TorBrowser/Data/Browser/profile.default"

    val torProfileDir = new Nothing(profilePath)
    val binary = new FirefoxBinary(new File(torPath))
    val torProfile = new FirefoxProfile(torProfileDir)

    val options = new FirefoxOptions
    options.setBinary(binary)
    options.setProfile(torProfile)
    options.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options)
    val driver = new FirefoxDriver(options)
    driver.get("https://www.google.com")
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
