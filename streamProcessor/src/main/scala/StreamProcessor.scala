import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming._
import org.apache.spark.sql.types._
import com.datastax.oss.driver.api.core.uuid.Uuids

object StreamProcessor {
    def main(args: Array[String]) = 
    {
        // Example settings, replace with your actual configuration
        val settings: Map[String, Map[String, String]] = Map(
            "spark" -> Map(
                "master" -> "local[*]",
                "fakerStream" -> "Faker Stream",
                "shuffle_partitions" -> "200"
            ),
            "cassandra" -> Map(
                "host" -> "localhost",
                "username" -> "cassandra",
                "password" -> "cassandra"
            ),
            "kafka" -> Map(
                "server_address" -> "kafka:29092",
                "topic" -> "fakerPerson",
                "min_partitions" -> "1"
            )
        )

        // udf for Cassandra schema
        val makeUUID = udf(() => Uuids.timeBased().toString())

        // create Spark Session
        val spark = SparkSession.builder
            .master(settings("spark")("master"))
            .appName(settings("spark")("fakerStream"))
            .config("spark.cassandra.connection.host", settings("cassandra")("host"))
            .config("spark.cassandra.auth.username", settings("cassandra")("username"))
            .config("spark.cassandra.auth.password", settings("cassandra")("password"))
            .config("spark.sql.shuffle.partitions", settings("spark")("shuffle_partitions"))
            .getOrCreate()

        // read streams from Kafka
        var inputDF = spark
            .readStream
            .format("kafka")
            .option("kafka.bootstrap.servers", settings("kafka")("server_address"))
            .option("subscribe", settings("kafka")("topic"))
            .option("minPartitions", settings("kafka")("min_partitions"))
            .load()

        // rename columns and add proper timestamps
         val finalDF = inputDF
            .withColumn("uuid", makeUUID())
            .withColumn("ingest_timestamp",current_timestamp().as("ingest_timestamp"))

        // write query to Cassandra
        val query = finalDF
            .writeStream
            .foreachBatch { (batchDF:DataFrame,batchID:Long) =>
                println(s"Writing to Cassandra $batchID")
                batchDF
                    .write
                    // .cassandraFormat(settings("cassandra")("fakerPerson"), settings("cassandra")("keyspace"))
                    .format("org.apache.spark.sql.cassandra")
                    .options(Map("table" -> "person", "keyspace" -> "fakerPerson"))
                    .mode("append")
                    .save()
            }
            .outputMode("update")
            .start()
        
        // Your further processing logic here
        println("ola madafacas")
        println(spark)
        println("Spark Version : " + spark.version)
    }
}
