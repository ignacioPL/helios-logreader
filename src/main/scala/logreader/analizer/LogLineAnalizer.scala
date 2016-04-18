package logreader.analizer

import java.time.LocalDateTime

import logreader.fileReader.AsyncFileReader
import logreader.http.AsyncHttp
import logreader.model.MonitoredStatus

/**
  * Created by ignacioperez on 18/04/16.
  */
class LogLineAnalizer(fileReader: AsyncFileReader, http: AsyncHttp) {

  val logLevel = """ERROR[^*]*""".r

  def start():Unit = fileReader.getLog
      .filter( s => logLevel.findFirstIn(s).fold[Boolean](false)( _ => true))
      .map( s => MonitoredStatus("ERROR","this",LocalDateTime.now(),s))
      .foreach(ms => http.doPost(ms))

}
