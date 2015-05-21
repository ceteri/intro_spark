import org.apache.spark.mllib.classification.NaiveBayes
val model = NaiveBayes.train(train_set, lambda = 1.0)

val pred = test_set.map(t => (model.predict(t.features), t.label))
val cm = sc.parallelize(pred.countByValue().toSeq)
val cm_nb = cm.map(x => (x._1, (1.0 * x._2 / n))).sortBy(_._1, true)
cm_nb.foreach(println)
