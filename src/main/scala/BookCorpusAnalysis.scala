import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.functions._
import org.apache.spark.rdd.RDD
import java.nio.file.{Files, Paths}
import breeze.plot._

object BookCorpusAnalysis {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .appName("BookCorpus Analysis")
      .config("spark.executor.memory", "4g")
      .config("spark.driver.memory", "4g")
      .config("spark.sql.shuffle.partitions", "200")
      .master("local[*]")
      .getOrCreate()

    val dataDir = "/mnt/d/Documents/EPSI/TP_Scala/data"
    val part1Path = s"$dataDir/books_large_p1.txt"
    val part2Path = s"$dataDir/books_large_p2.txt"

    if (!Files.exists(Paths.get(part1Path)) || !Files.exists(Paths.get(part2Path))) {
      println("========================================")
      println(s"Les fichiers suivants sont manquants :")
      if (!Files.exists(Paths.get(part1Path))) println(s"$part1Path")
      if (!Files.exists(Paths.get(part2Path))) println(s"$part2Path")
      println("========================================")
      sys.exit(1)
    }

    // Load only the first 3 million lines from each file
    val lineLimit = 3000000
    val rawData1 = Preprocessing.loadData(spark, part1Path, lineLimit)
    val rawData2 = Preprocessing.loadData(spark, part2Path, lineLimit)
    
    // Combine the datasets
    val rawData = rawData1.union(rawData2)

    // Prétraitement
    val cleanedData = Preprocessing.cleanData(rawData)
    val booksData = Preprocessing.splitIntoBooks(cleanedData).cache()

    // Compter et afficher le nombre de livres
    val bookCount = Analysis.countBooks(booksData)
    println("========================================")
    println(s"Nombre de livres : $bookCount")
    println("========================================")

    // Afficher les statistiques des livres
    Analysis.displayBookStats(booksData)

    // Analyse
    Analysis.verifyDataset(booksData)
    val descriptiveStats = Analysis.descriptiveStatistics(booksData).cache()
    val advancedAnalysisData = Analysis.analyzeText(booksData)

    // Visualisation
    Visualization.visualizeData(advancedAnalysisData)

    spark.stop()
  }
}