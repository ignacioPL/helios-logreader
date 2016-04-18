package logreader

import logreader.analizer.LogLineAnalizer
import logreader.fileReader.AsyncFileReader
import logreader.http.AsyncHttp

/**
  * Created by ignacioperez on 18/04/16.
  */
object Run {
  def main(args: Array[String]): Unit = {

    val http = new AsyncHttp
    val fileReader = new AsyncFileReader

    val analizer = new LogLineAnalizer(fileReader,http)

    analizer.start()
  }
}
