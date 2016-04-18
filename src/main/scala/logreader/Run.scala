package logreader

import logreader.analizer.LogLineAnalizer
import logreader.fileReader.AsyncFileReader
import logreader.http.AsyncHttp
import org.slf4j.LoggerFactory

/**
  * Created by ignacioperez on 18/04/16.
  */
object Run {

  lazy val logger = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {

    logger.info("starting")

    val http = new AsyncHttp
    val fileReader = new AsyncFileReader

    val analizer = new LogLineAnalizer(fileReader,http)

    analizer.start()
  }
}
