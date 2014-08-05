package jinesra.vkMessageHistory

import org.rogach.scallop._
import scala.xml._
import ch.qos.logback.classic.Level
import org.slf4j.LoggerFactory

object Script {
  def main(args: Array[String]) {
    object P extends ScallopConf(args) {
      val token = opt[String]("token", required = true,
        descr = "Access token you received from vk.com")
      val debug = toggle("debug", default = Some(false))
      verify
    }

    import org.slf4j.{ LoggerFactory }
    import ch.qos.logback.classic.{ Level, Logger }

    val root = (LoggerFactory getLogger ("ROOT")).asInstanceOf[ch.qos.logback.classic.Logger]
    if (P.debug.apply)
      root.setLevel(Level.DEBUG)
    else
      root.setLevel(Level.INFO)
      
    val client = new VkRestClient(P.token.apply)
    
    System.exit(0)
  }
}