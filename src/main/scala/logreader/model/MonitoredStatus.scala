package logreader.model

import java.time.{LocalDateTime, ZoneOffset}

/**
  * Created by ignacioperez on 18/04/16.
  */
case class MonitoredStatus(status: String, origin: String, date: LocalDateTime, msg: String){
  def toJsonRep: String =
    s"{ 'status':'$status','origin':'$origin','date':${date.toEpochSecond(ZoneOffset.UTC)}, 'msg':'$msg'}"
}