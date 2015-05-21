val f = sc.textFile("data/README.md")
val wc = f.flatMap(l => l.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)

wc.saveAsTextFile("wc_out")
