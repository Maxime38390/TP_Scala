import org.apache.spark.sql.SparkSession
import java.nio.file.{Files, Paths}

object BookCorpusAnalysis {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .appName("BookCorpus Analysis")
      .master("local[*]")
      .getOrCreate()

    val dataDir = "/mnt/d/Documents/EPSI/TP_Scala/data"
    val part1Path = s"$dataDir/books_large_p1.txt"

    if (!Files.exists(Paths.get(part1Path))) {
      println("========================================")
      println(s"Le fichier suivant est manquant : $part1Path")
      println("========================================")
      sys.exit(1)
    }

    // Limite pour le nombre de lignes à charger
    val lineLimit = 10000000

    // Prétraitement
    val rawData = Preprocessing.loadData(spark, part1Path, lineLimit)
    val cleanedData = Preprocessing.cleanData(rawData)
    val booksData = Preprocessing.splitIntoBooks(cleanedData)

    // Compter et afficher le nombre de livres
    val bookCount = Analysis.countBooks(booksData)
    println("========================================")
    println(s"Nombre de livres : $bookCount")
    println("========================================")

    // Afficher les statistiques des livres
    Analysis.displayBookStats(booksData)

    // Analyse
    Analysis.verifyDataset(booksData)
    val analyzedData = Analysis.descriptiveStatistics(booksData)
    val advancedAnalysisData = Analysis.analyzeText(analyzedData)

    // Visualisation
    Visualization.visualizeData(analyzedData)

    spark.stop()
  }
}
