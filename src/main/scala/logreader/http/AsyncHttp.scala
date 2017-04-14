package logreader.http


import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging
import logreader.model.MonitoredStatus
import logreader.utils.LogReaderConfig
import play.api.libs.json.Json
import play.api.libs.ws.ahc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by ignacioperez on 18/04/16.
  */
trait AsyncHttp extends AutoCloseable{
  def doPost(ms: MonitoredStatus): Future[Unit]
}

class WsAsyncHttp(config: LogReaderConfig, materializer: ActorMaterializer) extends LazyLogging with AsyncHttp{

  private lazy val client = StandaloneAhcWSClient()(materializer)

  override def doPost(ms: MonitoredStatus): Future[Unit] = {
    logger.info(s"about to post to ${config.endpoint}")
    client.url(config.endpoint).post(Json.toJson(ms)).map(_ => ())
  }

  override def close(): Unit = client.close()
}
