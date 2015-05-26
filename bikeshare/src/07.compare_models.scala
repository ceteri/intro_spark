// compare models
case class TripReport(predicted: Int, observed: Int, model: String, frequency: Double)

val part0 = cm_nb.map(x => TripReport(x._1._1.toInt, x._1._2.toInt, "0.NB", x._2))
val part1 = cm_dt.map(x => TripReport(x._1._1.toInt, x._1._2.toInt, "1.DT", x._2))
val part2 = cm_rf.map(x => TripReport(x._1._1.toInt, x._1._2.toInt, "2.RF", x._2))

val cm_df = part0.union(part1).union(part2).toDF()
cm_df.sort("predicted", "observed", "model").show()
