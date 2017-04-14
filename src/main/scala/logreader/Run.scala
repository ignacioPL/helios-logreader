package logreader

import com.typesafe.scalalogging.LazyLogging
import logreader.analizer.LogLineAnalizer

/**
  * Created by ignacioperez on 18/04/16.
  */
object Run extends LazyLogging with Module{

  def main(args: Array[String]): Unit = {

    sys.addShutdownHook( logger.info("exiting") )

    logger.info("starting")

    LogLineAnalizer(asyncFileReader,asyncHttp).start()
  }
}
