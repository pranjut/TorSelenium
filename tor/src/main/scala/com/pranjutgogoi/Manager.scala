package com.pranjutgogoi

import com.typesafe.config.ConfigFactory

object Manager {

  val config = ConfigFactory.load()
  val firefoxDriverUrl = config.getString("webdriver.gecko.driver")
  val extensionOnionDir = config.getString("extension.dir")
  val extensionOnionName = config.getString("extension.onion.name")
}
