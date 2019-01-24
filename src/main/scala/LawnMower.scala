

object LawnMower {

  case class CommandLineArgs(
                              confPath: String = "./src/ressources/config_test.txt"  // Tout ça sur une ligne c'est bien aussi :)
                            )


  def main(args: Array[String]): Unit = {

    val parser = new scopt.OptionParser[CommandLineArgs]("lawn-mower") {
      head("lawn-mower", "1.0")
      opt[String]('k', "confPath").action((x, c) =>
        c.copy(confPath = x)).text("confPath is the configuration path. There is a default one.")
    }

    parser.parse(args, CommandLineArgs()) match {

      case Some(config) =>

        val filename = config.confPath
        val reader = ConfigReaderParser(fileName = filename)
        val linesList = reader.reader()

        val upperLeft = reader.giveUpperLeft(linesList)
        val field =
          if (linesList.head.isEmpty)
            Field(occupied = List())
          else Field(upper_left = (upperLeft(0), upperLeft(1)), occupied = List())

        val lawnMowerInitDirectionCoord = reader.giveDirectionCoordInitList(linesList)
        val lawnMowerInitSeq = reader.giveMovesList(linesList)

        val lawnMowerList =
          if (lawnMowerInitSeq.nonEmpty)
            (lawnMowerInitDirectionCoord, lawnMowerInitSeq).zipped
              .map((a, b) => LawnMowerObject(coord_i = (a(0).toInt, a(1).toInt), direction_i = a(2).charAt(0), moves_seq = b))
          else {
            List.empty
          }

        val lawnMowerMovesList = lawnMowerList.map(e => e.computeMove(field = field))

        val lawnMowerFinalPositionList: Seq[LawnMowerFinalPosition] = (lawnMowerList, lawnMowerMovesList).zipped
          .map { (a, b) => a.giveFinalPosition(mowerAfterMove = b._1) }

        val finalField = lawnMowerMovesList.map(e => e._2.occupied)

        println("Here are the final positions of the mowers: ")
        println(lawnMowerFinalPositionList.map(e => (e.final_coord._1, e.final_coord._2, e.final_direction)))
        println("Here is the field state after the mowers mowe: ")
        println(finalField)

      case None =>
        // Un petit message d'erreur ?

    }
  }
}
