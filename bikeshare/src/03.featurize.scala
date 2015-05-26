// this relationship could be used to produce classifiers
bike_df.groupBy("reg").agg(bike_df("reg"), avg(bike_df("dur"))).show()

import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.Vectors

val station_map = bike_trips.map(_.s0).union(bike_trips.map(_.s1)).
  distinct().zipWithUniqueId().collectAsMap()

val label_map = Map("Registered" -> 0.0, "Casual" -> 1.0)

var l_bike = bike_trips.map{ t =>
  var s0 = station_map.get(t.s0).getOrElse(0L).toDouble
  var s1 = station_map.get(t.s1).getOrElse(0L).toDouble

  LabeledPoint(label_map(t.reg), Vectors.dense(t.dur, s0, s1))
}

val splits = l_bike.randomSplit(Array(0.6, 0.4), seed = 11L)
val train_set = splits(0).cache()
val test_set = splits(1)
val n = test_set.count()
