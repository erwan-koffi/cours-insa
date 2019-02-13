package fr.insa.hadoop.yarn

import java.lang

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
import org.apache.hadoop.mapreduce.{Job, Mapper, Reducer}

class WordCountMapper() extends Mapper[LongWritable, Text, Text, IntWritable] {
  override def map(key: LongWritable, value: Text, context: Mapper[LongWritable, Text, Text, IntWritable]#Context): Unit = {
    val line = value.toString
    line.split("\\W+").foreach(word => {
      if (word.length > 0) {
        context.write(new Text(word), new IntWritable(1))
      }
    })
  }
}

case class WordCountReducer() extends Reducer[Text, IntWritable, Text, IntWritable] {
  override def reduce(key: Text, values: lang.Iterable[IntWritable], context: Reducer[Text, IntWritable, Text, IntWritable]#Context): Unit = {
    import scala.collection.JavaConverters._
    val sum = values.asScala.map(_.get).sum
    context.write(key, new IntWritable(sum))
  }
}

object LaunchMapReduce extends App {
  System.setProperty("HADOOP_USER_NAME", "hadoop")
  val configuration = new Configuration()
  configuration.set("fs.default.name", "hdfs://namenode:9000")
  configuration.set("dfs.datanode.use.datanode.hostname", "true")
  configuration.set("dfs.client.use.datanode.hostname", "true")
  configuration.set("yarn.resourcemanager.hostname", "resourcemanager")
  configuration.set("mapreduce.framework.name", "yarn")

  val job = new Job(configuration)

  job.setJobName("Word count")
  FileInputFormat.addInputPath(job, new Path("./sample.txt"))

  FileOutputFormat.setOutputPath(job, new Path("./wordcount.txt"))

  job.setMapperClass(classOf[WordCountMapper])
  job.setReducerClass(classOf[WordCountReducer])

  job.setOutputKeyClass(classOf[Text])
  job.setOutputValueClass(classOf[IntWritable])

  val jobStatus = job.waitForCompletion(true)
  System.exit(if (jobStatus) 0 else 1)
}
