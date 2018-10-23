import scala.io.Source

case class ConfigReaderParser(fileName : String) {

  def reader(): List[List[String]] = {
    Source.fromFile(this.fileName).getLines().toList.map(_.mkString).map(_.split(" ").map(_.trim).toList)
    }

  def giveUpperLeft(txtLines: List[List[String]]): List[Int] ={
    txtLines(0).map(_.toInt)
  }

  def giveDirectionCoordInitList(txtLines: List[List[String]]): List[List[String]] = {

    txtLines.indices.collect { case i if i % 2 == 1 => txtLines(i) }.toList
  }

  def giveMovesList(txtLines: List[List[String]]): List[String] = {
    txtLines.indices.collect { case i if i % 2 == 0 && i >= 2 && txtLines.length >= 2 => txtLines(i).head}.toList
  }

  }
