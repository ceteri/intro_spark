# can query in Python using the DSL approach

bike_df.groupBy("s0", "s1").count().show()

bike_df.filter(bike_df.dur > 300000).show()

# can also query in Python using SQL queries

bike_df.registerTempTable("trips")
sqlContext.sql("SELECT * FROM trips").show()
