class ConfigReaderParserTest extends UnitTest("ConfigReaderParser"){

  it should "read the txt file" in {
    val readerTest = ConfigReaderParser(fileName = "./src/ressources/config_test.txt")
    readerTest.reader().isInstanceOf[List[List[String]]]
  }

  it should "give the upper left coordinates of the field" in {
    val readerTest = ConfigReaderParser(fileName = "./src/ressources/config_test.txt")
    val linesList = List(List("5", "5"), List("1", "2", "N"), List("GA"))
    readerTest.giveUpperLeft(linesList) shouldEqual List(5, 5)
  }

  it should "give an empty list when the upper left coordinates are empty." in {
    val readerTest = ConfigReaderParser(fileName = "./src/ressources/config_test.txt")
    val linesList = List(List(), List(), List())
    readerTest.giveUpperLeft(linesList) shouldEqual List()
  }

  it should "give the initial coordinates and direction." in {
    val readerTest = ConfigReaderParser(fileName = "./src/ressources/config_test.txt")
    val linesList = List(List("5", "5"), List("1", "2", "N"), List("GA"), List("4", "4", "E"), List("A"))
    readerTest.giveDirectionCoordInitList(linesList) shouldEqual List(List("1", "2", "N"), List("4", "4", "E"))
  }

  it should "give the initial coordinates and direction as empty when the conf file is empty." in {
    val readerTest = ConfigReaderParser(fileName = "./src/ressources/config_test.txt")
    val linesList = List(List(), List(), List())
    readerTest.giveDirectionCoordInitList(linesList) shouldEqual List(List())
  }

  it should "give the moves list" in {
    val readerTest = ConfigReaderParser(fileName = "./src/ressources/config_test.txt")
    val linesList = List(List("5", "5"), List("1", "2", "N"), List("GA"), List("4", "4", "E"), List("A"))
    readerTest.giveMovesList(linesList) shouldEqual List("GA", "A")
  }

  it should "give an empty moves list when the conf file is empty." in {
    val readerTest = ConfigReaderParser(fileName = "./src/ressources/config_test.txt")
    val linesList = List(List())
    readerTest.giveMovesList(linesList) shouldEqual List()
  }

}
