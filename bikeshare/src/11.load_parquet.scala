val bike_trips = sqlContext.parquetFile("bike.parquet").cache()
bike_trips.registerTempTable("biketrips")
bike_trips.printSchema()

sql("SELECT * FROM biketrips LIMIT 10").show()

val query = """
SELECT
 COUNT(*) AS num, MIN(dur) AS min_dur, MAX(dur) AS max_dur, s0, s1
FROM biketrips
WHERE NOT s0 = s1
GROUP BY s0, s1
ORDER BY num DESC
LIMIT 10
"""

sql(query).show()
// minimum durations between Columbus Circle and 8th & F St NE are about 3 minutes, 
// as Google Maps predicts
