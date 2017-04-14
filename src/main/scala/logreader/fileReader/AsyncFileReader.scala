package logreader.fileReader

import com.github.davidmoten.rx.FileObservable
import logreader.utils.LogReaderConfig
import rx.lang.scala.Observable
import rx.lang.scala.JavaConversions._

/**
  * Created by ignacioperez on 18/04/16.
  */
class AsyncFileReader(config: LogReaderConfig) {
  def getLog: Observable[String] = FileObservable.tailer().file(config.filePath).tailText()
}
