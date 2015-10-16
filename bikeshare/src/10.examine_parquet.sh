ls -lh 2014-Q1-Trips-History-Data2.csv
ls -lh bike.parquet

SPARK_REPL_OPTS="-XX:MaxPermSize=1024m" $SPARK_HOME/bin/spark-shell
