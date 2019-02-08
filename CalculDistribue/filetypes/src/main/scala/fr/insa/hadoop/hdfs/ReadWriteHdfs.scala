package fr.insa.hadoop.hdfs

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path, RemoteIterator}

import scala.io.Source

case class ScalaRemoteIterator[A](rit: RemoteIterator[A]) extends Iterator[A] {
  override def hasNext: Boolean = rit.hasNext

  override def next(): A = rit.next()
}

object ReadWriteHdfs extends App {
  implicit def remoteToScala[A](rit: RemoteIterator[A]): ScalaRemoteIterator[A] = ScalaRemoteIterator(rit)

  System.setProperty("HADOOP_USER_NAME", "hadoop")
  val configuration = new Configuration()
  configuration.set("fs.default.name", "hdfs://51.15.133.130:9000")
  configuration.set("dfs.datanode.use.datanode.hostname", "true")
  configuration.set("dfs.client.use.datanode.hostname", "true")

  def createHadoopFileSystem(configuration: Configuration): FileSystem = FileSystem.get(configuration)

  def listHdfsFiles(path: Path): List[String] = fs
    .listFiles(path, false)
    .map(f => f.getPath.getName).toList

  def readHdfsFile(path: Path): String = {
    val is = fs.open(path)
    Stream.continually(is.read()).takeWhile(b => b != -1).map(_.asInstanceOf[Char]).mkString
  }

  def writeHdfsFile(source: String, destination: Path): Unit = {
    val os = fs.create(destination, true)
    Source.fromFile(source).toStream.takeWhile(_ != -1).foreach(b => os.write(b))
    os.close()
  }

  val fs = createHadoopFileSystem(configuration)

  listHdfsFiles(new Path("./")).foreach(println)

  writeHdfsFile("./sample.txt", new Path("sample.txt"))

  println(readHdfsFile(new Path("./sample.txt")))
}
