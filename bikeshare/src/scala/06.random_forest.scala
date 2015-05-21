import org.apache.spark.mllib.tree.RandomForest
import org.apache.spark.mllib.tree.model.RandomForestModel

val numClasses = 2
val categoricalFeaturesInfo = Map[Int, Int]()
val numTrees = 3 // Use more in practice.
val featureSubsetStrategy = "auto" // Let the algorithm choose.
val impurity = "gini"
val maxDepth = 4
val maxBins = 32

val model = RandomForest.trainClassifier(train_set, numClasses, categoricalFeaturesInfo,
  numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins)

val pred = test_set.map(t => (model.predict(t.features), t.label))
val cm = sc.parallelize(pred.countByValue().toSeq)
val cm_rf = cm.map(x => (x._1, (1.0 * x._2 / n))).sortBy(_._1, true)
cm_rf.foreach(println)
