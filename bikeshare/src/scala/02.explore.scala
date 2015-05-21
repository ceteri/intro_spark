// use DataFrames to explore the data
val bike_df = bike_trips.toDF()
bike_df.registerTempTable("bikeshare")

sql("SELECT * FROM bikeshare LIMIT 10").show()

val query = """
SELECT COUNT(*) AS num, s1, s0 FROM bikeshare GROUP BY s0, s1 ORDER BY num DESC LIMIT 10
"""
sql(query).show()

// compare these results with Google Maps, 8th NE & F St NE to Columbus Circle
// http://goo.gl/sAOdSv
