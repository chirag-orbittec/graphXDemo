import org.apache.spark.SparkConf
import org.apache.spark.graphx.GraphLoader
import org.apache.spark.sql.SparkSession






object Sample {

  def main(args: Array[String]): Unit = {
    print("Hello World")




    val conf = new SparkConf().setMaster("local[2]")
    val spark = SparkSession
      .builder
      .appName("SampleApp").config(conf)
      .getOrCreate()
    val sc = spark.sparkContext


    // $example on$
    // Load the edges as a graph
    val graph = GraphLoader.edgeListFile(sc, "facebook_combined.txt")
    // Run PageRank
    val ranks = graph.pageRank(1).vertices


    // Join the ranks with the usernames
    /*val users = sc.textFile("users.txt").map { line =>
      val fields = line.split(",")
      (fields(0).toLong, fields(1))
    }
    val ranksByUsername = users.join(ranks).map {
      case (id, (username, rank)) => (username, rank)
    }
    */
    // Print the result
    //println(ranksByUsername.collect().mkString("\n"))
    print(ranks.collect().mkString("\n"))
    // $example off$


    spark.stop()

  }
}
