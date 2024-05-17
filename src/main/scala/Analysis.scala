import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.functions._
import org.apache.spark.rdd.RDD
import java.nio.file.{Files, Paths}
import breeze.plot._



object Analysis {
  def countBooks(rdd: RDD[(String, Long)]): Long = {
    rdd.map(_._2).distinct().count()
  }

  def displayBookStats(rdd: RDD[(String, Long)]): Unit = {
    val spark = SparkSession.builder.getOrCreate()
    import spark.implicits._

    rdd.map { case (line, bookId) =>
      val wordCount = line.split("\\s+").length
      val uniqueWordCount = line.split("\\s+").distinct.length
      (bookId, 1, wordCount, uniqueWordCount, line.length)
    }.toDF("book_id", "line_count", "word_count", "unique_word_count", "char_count")
      .groupBy("book_id")
      .agg(
        sum("line_count").alias("total_lines"),
        sum("word_count").alias("total_words"),
        avg("word_count").alias("avg_words_per_line"),
        sum("unique_word_count").alias("total_unique_words"),
        avg("unique_word_count").alias("avg_unique_words_per_line"),
        sum("char_count").alias("total_characters")
      ).show(truncate = false)
  }

  def verifyDataset(rdd: RDD[(String, Long)]): Unit = {
    rdd.take(10).foreach(println)
  }

  def descriptiveStatistics(rdd: RDD[(String, Long)]): DataFrame = {
    val spark = SparkSession.builder.getOrCreate()
    import spark.implicits._

    rdd.map { case (line, bookId) =>
      val wordCount = line.split("\\s+").length
      val uniqueWordCount = line.split("\\s+").distinct.length
      (bookId, wordCount, uniqueWordCount)
    }.toDF("book_id", "word_count", "unique_word_count")
      .groupBy("book_id")
      .agg(
        avg("word_count").alias("avg_words_per_line"),
        avg("unique_word_count").alias("avg_unique_words_per_line"),
        count("word_count").alias("num_sentences")
      )
  }

  def analyzeText(rdd: RDD[(String, Long)]): DataFrame = {
    val spark = SparkSession.builder.getOrCreate()
    import spark.implicits._

    rdd.map { case (line, bookId) =>
      val wordCount = line.split("\\s+").length
      val uniqueWordCount = line.split("\\s+").distinct.length
      (bookId, wordCount, uniqueWordCount)
    }.toDF("book_id", "word_count", "unique_word_count")
      .groupBy("book_id")
      .agg(
        avg("word_count").alias("avg_words_per_line"),
        avg("unique_word_count").alias("avg_unique_words_per_line"),
        count("word_count").alias("num_sentences")
      )
  }
}