# load from Parquet files

from pyspark.sql import SQLContext
sqlContext = SQLContext(sc)

bike_df = sqlContext.parquetFile("bike.parquet").cache()
