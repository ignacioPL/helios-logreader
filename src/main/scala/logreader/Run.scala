package logreader

import com.typesafe.scalalogging.LazyLogging
import logreader.analizer.LogLineAnalyzer

/**
  * Created by ignacioperez on 18/04/16.
  */
object Run extends App with LazyLogging with Module{

  sys.addShutdownHook( logger.info("exiting") )

  logger.info("starting")

  LogLineAnalyzer(asyncFileReader,asyncHttp).start()

}
