import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD

// build the node list
val sta0_rdd = bike_trips.map(r => (r(5).asInstanceOf[Long], r(3).asInstanceOf[String]))
val sta1_rdd = bike_trips.map(r => (r(6).asInstanceOf[Long], r(4).asInstanceOf[String]))
val nodeRDD = sta0_rdd.union(sta1_rdd).distinct()
nodeRDD.take(2)

// build the edge list
val edge_kv = bike_trips.map(r => ((r(5), r(6)), r(2))).groupByKey()
edge_kv.take(2)

def median(s: Seq[Long]) = {
  val (lower, upper) = s.sortWith(_ < _).splitAt(s.size / 2)
  if (s.size % 2 == 0) (lower.last + upper.head) / 2.0 else upper.head
}

val edgeRDD = edge_kv.map{ r =>
  val med = median(r._2.map(_.asInstanceOf[Long]).toSeq)
  Edge(r._1._1.asInstanceOf[Long], r._1._2.asInstanceOf[Long], med)
}
edgeRDD.take(2)

// compose the graph
val g: Graph[String, Double] = Graph(nodeRDD, edgeRDD)
