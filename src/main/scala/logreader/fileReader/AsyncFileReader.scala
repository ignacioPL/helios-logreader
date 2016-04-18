package logreader.fileReader

import com.github.davidmoten.rx.FileObservable
import logreader.utils.Config
import rx.lang.scala.Observable
import rx.lang.scala.JavaConversions._

/**
  * Created by ignacioperez on 18/04/16.
  */
class AsyncFileReader {

  def getLog: Observable[String] = FileObservable.tailer().file(Config.getLocation).tailText()
}
