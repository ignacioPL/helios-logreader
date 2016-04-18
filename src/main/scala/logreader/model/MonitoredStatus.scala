package logreader.model

import java.time.LocalDateTime

/**
  * Created by ignacioperez on 18/04/16.
  */
case class MonitoredStatus(status: String, origin: String, date: LocalDateTime, msg: String)
