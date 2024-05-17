import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.functions._
import org.apache.spark.rdd.RDD
import java.nio.file.{Files, Paths}
import breeze.plot._

object Visualization {
  def visualizeData(df: DataFrame): Unit = {
    val spark = SparkSession.builder.getOrCreate()
    import spark.implicits._

    val data = df.collect()

    val bookIds = data.map(_.getLong(0).toDouble)
    val avgWordsPerLine = data.map(_.getDouble(1))
    val avgUniqueWordsPerLine = data.map(_.getDouble(2))
    val numSentences = data.map(_.getLong(3).toDouble)

    val f = Figure()

    // Plot for Average Words Per Line
    val p1 = f.subplot(2, 2, 0)
    p1 += plot(bookIds, avgWordsPerLine, '.', colorcode = "blue")
    p1.xlabel = "Book ID"
    p1.ylabel = "Average Words Per Line"
    p1.title = "Average Words Per Line by Book ID"

    // Plot for Average Unique Words Per Line
    val p2 = f.subplot(2, 2, 1)
    p2 += plot(bookIds, avgUniqueWordsPerLine, '.', colorcode = "red")
    p2.xlabel = "Book ID"
    p2.ylabel = "Average Unique Words Per Line"
    p2.title = "Average Unique Words Per Line by Book ID"

    // Plot for Number of Sentences
    val p3 = f.subplot(2, 2, 2)
    p3 += plot(bookIds, numSentences, '.', colorcode = "green")
    p3.xlabel = "Book ID"
    p3.ylabel = "Number of Sentences"
    p3.title = "Number of Sentences by Book ID"

    f.saveas("output.png")
  }
}