package logreader.model

import java.time.LocalDateTime

import play.api.libs.json.Json

/**
  * Created by ignacioperez on 18/04/16.
  */
case class MonitoredStatus(status: String, origin: String, date: LocalDateTime, msg: String)

object MonitoredStatus{
  implicit val format = Json.format[MonitoredStatus]
}