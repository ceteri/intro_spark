// create some data to use
val data = 1 to 10000

// create an RDD based on that data
val distData = sc.parallelize(data)

// use a filter to select values less than 10
distData.filter(_ < 10).collect()
