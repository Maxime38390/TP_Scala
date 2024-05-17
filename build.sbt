name := "BookCorpusAnalysis"

version := "0.1"

scalaVersion := "2.13.6"

lazy val sparkcore = "org.apache.spark" %% "spark-core" % "3.3.0"
lazy val sparksql = "org.apache.spark" %% "spark-sql" % "3.3.0"
lazy val hadoopclient = "org.apache.hadoop" % "hadoop-client" % "3.3.2"
lazy val breeze = "org.scalanlp" %% "breeze" % "1.2"
lazy val breezeViz = "org.scalanlp" %% "breeze-viz" % "1.2"

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
  sparkcore,
  sparksql,
  hadoopclient,
  breeze,
  breezeViz
)

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"


fork in run := true
