package logreader.http


import logreader.model.MonitoredStatus
import logreader.utils.Config
import org.slf4j.LoggerFactory
import play.api.libs.json.Json
import play.api.libs.ws.WSResponse
import play.api.libs.ws.ning.NingWSClient

import scala.concurrent.Future

/**
  * Created by ignacioperez on 18/04/16.
  */
class AsyncHttp {

  lazy val client = NingWSClient()
  lazy val logger = LoggerFactory.getLogger(this.getClass)
  val url = Config.getDestiny

  def doPost(ms: MonitoredStatus): Future[WSResponse]= {
    logger.info(s"about to post to $url")
    client.url(url).post(Json.toJson(ms))
  }

}
