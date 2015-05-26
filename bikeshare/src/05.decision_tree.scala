import org.apache.spark.mllib.tree.DecisionTree

val numClasses = 2
val categoricalFeaturesInfo = Map[Int, Int]()
val impurity = "gini"
val maxDepth = 5
val maxBins = 32

val model = DecisionTree.trainClassifier(train_set, numClasses, categoricalFeaturesInfo,
  impurity, maxDepth, maxBins)

val pred = test_set.map(t => (model.predict(t.features), t.label))
val cm = sc.parallelize(pred.countByValue().toSeq)
val cm_dt = cm.map(x => (x._1, (1.0 * x._2 / n))).sortBy(_._1, true)
cm_dt.foreach(println)
