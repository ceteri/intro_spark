case class TripEx(id: String, reg: String, dur: Long, s0: String, s1: String, sta0: Long, sta1: Long)

val bike_trips_ex = bike_trips.map{ t =>
  var sta0 = station_map.get(t.s0).getOrElse(0L)
  var sta1 = station_map.get(t.s1).getOrElse(0L)

  TripEx(t.id, t.reg, t.dur, t.s0, t.s1, sta0, sta1)
}.toDF()

bike_trips_ex.take(2)
bike_trips_ex.saveAsParquetFile("bike.parquet")
