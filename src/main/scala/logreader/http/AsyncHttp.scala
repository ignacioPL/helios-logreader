package logreader.http


import logreader.model.MonitoredStatus
import play.api.libs.ws.WSResponse
import play.api.libs.ws.ning.NingWSClient

import scala.concurrent.Future

/**
  * Created by ignacioperez on 18/04/16.
  */
class AsyncHttp {

  lazy val client = NingWSClient()

  def doPost(ms: MonitoredStatus): Future[WSResponse]= {
    val jsonContent = ms.toString
    client.url("")
      .withHeaders(("Content-Type", "application/json"),("Content-Length", "" + jsonContent.length))
      .post(jsonContent)
  }

}
