val rm = sc.textFile("data/README.md")
val rm_wc = rm.flatMap(l => l.split(" ")).filter(_ == "Spark").map(word => (word, 1)).reduceByKey(_ + _)

val cl = sc.textFile("data/CHANGES.txt")
val cl_wc = cl.flatMap(l => l.split(" ")).filter(_ == "Spark").map(word => (word, 1)).reduceByKey(_ + _)
