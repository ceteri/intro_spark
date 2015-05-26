// load bikeshare data set
// www.capitalbikeshare.com/system-data
val raw_trips = sc.textFile("2014-Q1-Trips-History-Data2.csv")
raw_trips.take(4)

def convertDur(dur: String): Long = {
  val dur_regex = """(\d+)h\s(\d+)m\s(\d+)s""".r
  val dur_regex(hour, minute, second) = dur
  (hour.toLong * 3600L) + (minute.toLong * 60L) + second.toLong
}

case class Trip(id: String, dur: Long, s0: String, s1: String, reg: String)

val bike_trips = raw_trips.map(_.split(",")).filter(_(0) != "Duration").
  map(r => Trip(r(5), convertDur(r(0)), r(2), r(4), r(6)))

bike_trips.cache()
