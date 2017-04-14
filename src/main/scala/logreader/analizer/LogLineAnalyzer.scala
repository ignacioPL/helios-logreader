package logreader.analizer

import java.time.LocalDateTime

import com.typesafe.scalalogging.LazyLogging
import logreader.fileReader.AsyncFileReader
import logreader.http.AsyncHttp
import logreader.model.MonitoredStatus

/**
  * Created by ignacioperez on 18/04/16.
  */
class LogLineAnalyzer(fileReader: AsyncFileReader, http: AsyncHttp) extends LazyLogging{

  val logLevel = """ERROR[^*]*""".r

  def start(): Unit = fileReader.getLog
      .filter( s => logLevel.findFirstIn(s).fold[Boolean](false)( _ => true))
      .map{ s =>
        logger.info(s"error found: $s")
        MonitoredStatus("ERROR","this",LocalDateTime.now(),s)
      }
      .foreach(http.doPost)
}

object LogLineAnalyzer{
  def apply(fileReader: AsyncFileReader, http: AsyncHttp): LogLineAnalyzer = new LogLineAnalyzer(fileReader, http)
}