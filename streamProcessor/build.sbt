scalaVersion := "2.13.10"

name := "fakerStream"
version := "1.0"

val sparkVersion = "3.5.0"
libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
    "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
    "org.apache.spark" %% "spark-sql-kafka-0-10" % sparkVersion % "provided",
    "com.datastax.spark" %% "spark-cassandra-connector" % sparkVersion % "provided",
    "com.datastax.cassandra" % "cassandra-driver-core" % "3.11.3" % "provided"
)
