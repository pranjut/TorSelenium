package com.pranjutgogoi

import com.typesafe.config.ConfigFactory

object Manager {

  val config = ConfigFactory.load()
  val firefoxDriverUrl = config.getString("webdriver.gecko.driver")
  lazy val extensionsDir = config.getString("extension.dir")
  val extensionOnionName = config.getString("extension.onion.name")
  lazy val extensionNoScript = config.getString("extension.no.script")
}
