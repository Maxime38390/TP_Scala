import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.functions._
import org.apache.spark.rdd.RDD
import java.nio.file.{Files, Paths}
import breeze.plot._

object Preprocessing {
  val isbnPattern = "(97(8|9))?\\d{9}(\\d|X)".r
  val chapterPattern = "(?i)Chapter (1 |One)"

  def loadData(spark: SparkSession, filePath: String, limit: Int): RDD[String] = {
    spark.sparkContext.textFile(filePath).zipWithIndex().filter(_._2 < limit).map(_._1)
  }

  def cleanData(rdd: RDD[String]): RDD[String] = {
    rdd.map(line => line.replaceAll("[^a-zA-Z0-9\\s]", "").trim).filter(_.nonEmpty)
  }

  def splitIntoBooks(rdd: RDD[String]): RDD[(String, Long)] = {
    rdd.zipWithIndex().mapPartitions { iter =>
      var bookId = 0L
      var linesSinceLastBook = 0L
      iter.map { case (line, index) =>
        if (linesSinceLastBook >= 500 && (isbnPattern.findFirstIn(line).isDefined || chapterPattern.r.findFirstIn(line).isDefined)) {
          bookId += 1
          linesSinceLastBook = 0
        }
        linesSinceLastBook += 1
        (line, bookId)
      }
    }
  }
}