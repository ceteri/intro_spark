// PageRank in Pregel in GraphX
val ranks = g.pageRank(0.0001).vertices
ranks.take(10)

case class Rank(id: Long, rank: Double, station: String)
val rank_df = ranks.join(nodeRDD).map(r => Rank(r._1.toLong, r._2._1, r._2._2)).toDF()
rank_df.registerTempTable("ranks")

sql("SELECT * FROM ranks ORDER BY rank DESC LIMIT 10").show()
