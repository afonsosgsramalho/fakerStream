import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming._
import org.apache.spark.sql.types._
import org.apache.spark.sql.cassandra._

import com.datastax.oss.driver.api.core.uuid.Uuids

object StreamProcessor {
    def main(args: Array[String]) = 
    {
        // udf for Cassandra schema
        val makeUUID = udf(() => Uuids.timeBased().toString())

        // create Spark Session
        // val spark = sparkSession 
        //     .builder 
        //     .master(settings.spark("master")) 
        //     .appName(settings.spark("fakerStream")) 
        //     .config("spark.cassandra.connection.host",settings.cassandra("host"))  
        //     .config("spark.cassandra.connection.host",settings.cassandra("host")) 
        //     .config("spark.cassandra.auth.username", settings.cassandra("username")) 
        //     .config("spark.cassandra.auth.password", settings.cassandra("password")) 
        //     .config("spark.sql.shuffle.partitions", settings.spark("shuffle_partitions")) 
        //     .getOrCreate();    
    }
}