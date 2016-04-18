package logreader.utils

import java.io.FileInputStream
import java.util.Properties
import scala.collection.JavaConverters._
/**
  * Created by ignacioperez on 18/04/16.
  */
object Config {

  lazy val properties = {
    val x = new Properties()
      x.load(new FileInputStream("config/application.properties"))
      x.asScala
  }

  def getDestiny: String = properties("helios.http.destiny")
  def getLocation: String = properties("helios.log.location")
}
