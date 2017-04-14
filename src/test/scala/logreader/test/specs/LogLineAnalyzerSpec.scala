package logreader.test.specs

import logreader.analizer.LogLineAnalyzer
import logreader.fileReader.AsyncFileReader
import logreader.http.AsyncHttp
import logreader.test.BaseSpec
import rx.lang.scala.Observable

import scala.concurrent.Future

/**
  * Created by ignacio on 4/14/17.
  */
class LogLineAnalyzerSpec extends BaseSpec{

  behavior of "Log Line Analizer"

  val okLogStream = Observable.just("INFO - log line ok", "DEBUG - log line of debug", "WARN - is just a warning","TRACE - all the lines")
  val koLogStream = Observable.just("INFO - log line ok",
                                    "DEBUG - log line of debug",
                                    "ERROR - 1st error",
                                    "WARN - is just a warning",
                                    "ERROR - other error",
                                    "TRACE - all the lines")

  it should "no post in log without errors" in {

    val asyncHttp = mock[AsyncHttp]
    val asyncFileReader = mock[AsyncFileReader]

    (asyncFileReader.getLog _).expects().returns(okLogStream)

    val logLineAnalyzer = LogLineAnalyzer(asyncFileReader,asyncHttp)

    logLineAnalyzer.start()
  }

  it should "post in log with errors" in {
    val asyncHttp = mock[AsyncHttp]
    val asyncFileReader = mock[AsyncFileReader]

    (asyncFileReader.getLog _).expects().returns(koLogStream)
    (asyncHttp.doPost _).expects(*).returns(Future.successful(())).twice()

    val logLineAnalyzer = LogLineAnalyzer(asyncFileReader,asyncHttp)

    logLineAnalyzer.start()
  }

}
