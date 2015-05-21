// SSSP impl in Pregel in GraphX
val sssp = initialGraph.pregel((Double.PositiveInfinity, List[VertexId]()), Int.MaxValue, EdgeDirection.Out)(
  // vertex program
  (id, dist, newDist) => if (dist._1 < newDist._1) dist else newDist, 

  // send message
  triplet => {
    if (triplet.srcAttr._1 < triplet.dstAttr._1 - triplet.attr ) {
      Iterator((triplet.dstId, (triplet.srcAttr._1 + triplet.attr , triplet.srcAttr._2 :+ triplet.dstId)))
    } else {
      Iterator.empty
    }
  },

  // merge message
  (a, b) => if (a._1 < b._1) a else b)

  println(sssp.vertices.collect.mkString("\n")
)
