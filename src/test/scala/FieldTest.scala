class FieldTest extends UnitTest("Field") {

  it should "add occupied coordinates" in {
    val fieldTest = Field(upper_left = (5, 5), occupied = List())
    val newOccupiedCoord = (1, 2)
    fieldTest.addOccupied(coordOccupied = newOccupiedCoord).occupied shouldEqual List(newOccupiedCoord)
    fieldTest.addOccupied(coordOccupied = newOccupiedCoord) ne fieldTest
  }

}
