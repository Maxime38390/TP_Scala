import org.apache.spark.sql.DataFrame
import breeze.plot._
import breeze.linalg._

object Visualization {
  def visualizeData(data: DataFrame): Unit = {
    val wordCounts = data.select("word_count").collect().map(_.getInt(0))

    if (wordCounts.nonEmpty) {
      val f = Figure()
      val p = f.subplot(0)
      p += hist(DenseVector(wordCounts), 20)
      p.title = "Word Count Distribution"
      p.xlabel = "Word Count"
      p.ylabel = "Frequency"

      f.saveas("word_count_distribution.png")
      println("Plot saved to word_count_distribution.png")
    } else {
      println("No data available.")
    }
  }
}
