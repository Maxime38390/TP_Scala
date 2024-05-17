import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object Analysis {
  def verifyDataset(data: DataFrame): Unit = {
    val wordCounts = data.withColumn("word_count", size(split(col("text"), " ")))
    wordCounts.describe("word_count").show()
  }

  def descriptiveStatistics(data: DataFrame): DataFrame = {
    data.withColumn("word_count", size(split(col("text"), " ")))
  }

  def analyzeText(data: DataFrame): DataFrame = {
    // Placeholder for more advanced text analysis (e.g., sentiment analysis)
    data
  }

  def countBooks(data: DataFrame): Long = {
    data.select("book_id").distinct().count()
  }

  def countLinesAndWordsPerBook(data: DataFrame): DataFrame = {
    data.groupBy("book_id")
        .agg(
          count("*").alias("line_count"),
          sum(size(split(col("text"), " "))).alias("word_count")
        )
  }

  def displayBookStats(data: DataFrame): Unit = {
    val bookStats = countLinesAndWordsPerBook(data).collect()
    bookStats.foreach { row =>
      println(s"Livre ID: ${row.getAs[Long]("book_id")}, Nombre de lignes: ${row.getAs[Long]("line_count")}, Nombre de mots: ${row.getAs[Long]("word_count")}")
    }
  }
}
