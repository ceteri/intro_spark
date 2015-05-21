// load error messages from a log into memory
// then interactively search for various patterns

// base RDD
val lines = sc.textFile("data/error_log.txt")

// transformed RDDs
val errors = lines.filter(_.startsWith("ERROR"))
val messages = errors.map(_.split("\t")).map(r => r(1))

// persistence
messages.cache()

// action 1: count the errors related to MySQL
messages.filter(_.contains("mysql")).count()

// action 2: count the errors related to PHP
messages.filter(_.contains("php")).count()
