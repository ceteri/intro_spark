// note the "id" values for the top two stations on the earlier leaderboard
sql("SELECT * FROM ranks WHERE station LIKE 'Columbus Circle%' ").show()
sql("SELECT * FROM ranks WHERE station LIKE '8th%' ").show()

// initialize for SSSP
val sourceId: VertexId = 190
val initialGraph : Graph[(Double, List[VertexId]), Double] = g.mapVertices((id, _) => if (id == sourceId) (0.0, List[VertexId](sourceId)) else (Double.PositiveInfinity, List[VertexId]()))
