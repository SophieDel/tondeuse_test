import scala.io.Source

case class ConfigReaderParser(fileName : String) {

  def reader(): List[List[String]] = { // reader ce n'est pas très clair comme nom

    Source
      .fromFile(this.fileName) // Tu es dans une case class, pas besoin de this.
      .getLines()
      .toList
      .map(_.mkString)    // .map(_.mkString) : tu fais un mkstring sur une string, ça pas d'effet
      .map(_.split(" ")
        .map(_.trim)      // Sachant que tu viens de splitter sur " " tes champs ne peuvent pas avoir de " " au début ou à la fin. Du coup ton trim est inutile ici.
        .toList
      )
    }

  def giveUpperLeft(txtLines: List[List[String]]): List[Int] ={
    txtLines(0).map(_.toInt)  // A la place du '(0)' tu peux utiliser '.head'
  }

  def giveDirectionCoordInitList(txtLines: List[List[String]]): List[List[String]] = {

    txtLines.indices.collect { case i if i % 2 == 1 => txtLines(i) }.toList
  }

  def giveMovesList(txtLines: List[List[String]]): List[String] = {
    txtLines.indices.collect { case i if i % 2 == 0 && i >= 2 && txtLines.length >= 2 => txtLines(i).head}.toList
  }
}
