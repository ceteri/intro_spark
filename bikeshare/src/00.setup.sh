export SPARK_HOME=~/opt/spark
rm -rf bike.parquet

SPARK_REPL_OPTS="-XX:MaxPermSize=1024m" $SPARK_HOME/bin/spark-shell
