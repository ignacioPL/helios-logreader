package logreader

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.softwaremill.macwire._
import com.typesafe.config.ConfigFactory
import logreader.fileReader.{AsyncFileReader, AsyncFileReaderImpl}
import logreader.http.{AsyncHttp, WsAsyncHttp}
import logreader.utils.LogReaderConfig
import net.ceedubs.ficus.Ficus._
import net.ceedubs.ficus.readers.ArbitraryTypeReader._
/**
  * Created by ignacio on 4/14/17.
  */
trait Module {

  lazy val config = ConfigFactory.load().as[LogReaderConfig]("helios")
  implicit lazy val system = ActorSystem()
  lazy val materializer: ActorMaterializer = ActorMaterializer()
  lazy val asyncHttp: AsyncHttp = wire[WsAsyncHttp]
  lazy val asyncFileReader: AsyncFileReader = wire[AsyncFileReaderImpl]

  sys.addShutdownHook(asyncHttp.close())

}
