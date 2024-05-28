name := "FakerStream"

scalaVersion := "2.13.10"

name := "fakerStream"
version := "1.0"

val sparkVersion = "3.5.0"
libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % sparkVersion,
    "org.apache.spark" %% "spark-sql" % sparkVersion,
    "org.apache.spark" %% "spark-sql-kafka-0-10" % sparkVersion,
    "com.datastax.spark" %% "spark-cassandra-connector" % sparkVersion,
    "com.datastax.cassandra" % "cassandra-driver-core" % "3.11.3",
)

assembly / assemblyMergeStrategy := {
    case PathList("META-INF", "services", _*) => MergeStrategy.concat
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case x => MergeStrategy.first
}