class LawnMowerObjectTest extends UnitTest("LawnMowerObject"){

  it should "compute direction and move for the mower when the move is G, D, A." in {
    val mowerTest = LawnMowerObject(coord_i = (0, 0), direction_i = 'N', moves_seq = "AD")

    mowerTest.computeDirection(direction = 'N', move = 'D').new_direction shouldEqual 'E'
    mowerTest.computeDirection(direction = 'N', move = 'D').new_delta_xy shouldEqual (0, 0)
    mowerTest.computeDirection(direction = 'N', move = 'G').new_direction shouldEqual 'W'
    mowerTest.computeDirection(direction = 'N', move = 'G').new_delta_xy shouldEqual (0, 0)
    mowerTest.computeDirection(direction = 'N', move = 'A').new_direction shouldEqual 'N'
    mowerTest.computeDirection(direction = 'N', move = 'A').new_delta_xy shouldEqual (0, 1)

    mowerTest.computeDirection(direction = 'S', move = 'D').new_direction shouldEqual 'W'
    mowerTest.computeDirection(direction = 'S', move = 'D').new_delta_xy shouldEqual (0, 0)
    mowerTest.computeDirection(direction = 'S', move = 'G').new_direction shouldEqual 'E'
    mowerTest.computeDirection(direction = 'S', move = 'G').new_delta_xy shouldEqual (0, 0)
    mowerTest.computeDirection(direction = 'S', move = 'A').new_direction shouldEqual 'S'
    mowerTest.computeDirection(direction = 'S', move = 'A').new_delta_xy shouldEqual (0, -1)

    mowerTest.computeDirection(direction = 'E', move = 'D').new_direction shouldEqual 'S'
    mowerTest.computeDirection(direction = 'E', move = 'D').new_delta_xy shouldEqual (0, 0)
    mowerTest.computeDirection(direction = 'E', move = 'G').new_direction shouldEqual 'N'
    mowerTest.computeDirection(direction = 'E', move = 'G').new_delta_xy shouldEqual (0, 0)
    mowerTest.computeDirection(direction = 'E', move = 'A').new_direction shouldEqual 'E'
    mowerTest.computeDirection(direction = 'E', move = 'A').new_delta_xy shouldEqual (1, 0)

    mowerTest.computeDirection(direction = 'W', move = 'D').new_direction shouldEqual 'N'
    mowerTest.computeDirection(direction = 'W', move = 'D').new_delta_xy shouldEqual (0, 0)
    mowerTest.computeDirection(direction = 'W', move = 'G').new_direction shouldEqual 'S'
    mowerTest.computeDirection(direction = 'W', move = 'G').new_delta_xy shouldEqual (0, 0)
    mowerTest.computeDirection(direction = 'W', move = 'A').new_direction shouldEqual 'W'
    mowerTest.computeDirection(direction = 'W', move = 'A').new_delta_xy shouldEqual (-1, 0)

  }

  it should "raise an error when the move is not in G, D, A." in {

    val mowerTest = LawnMowerObject(coord_i = (0, 0), direction_i = 'N', moves_seq = "AD")
    the [IllegalArgumentException] thrownBy mowerTest.computeDirection(direction = 'W', move = 'T') should have message
      "Move is not G, D, A."
  }

  it should "raise an error when the direction is not in E, W, N, S." in {

    val mowerTest = LawnMowerObject(coord_i = (0, 0), direction_i = 'N', moves_seq = "AD")
    the [IllegalArgumentException] thrownBy mowerTest.computeDirection(direction = 'T', move = 'A') should have message
      "Direction is not E, W, N nor S."
  }

  it should "raise an error when the move nor the direction are correct" in {

    val mowerTest = LawnMowerObject(coord_i = (0, 0), direction_i = 'N', moves_seq = "AD")
    the [IllegalArgumentException] thrownBy mowerTest.computeDirection(direction = 'T', move = 'T') should have message
      "Move is not G, D, A."
  }

  it should "compute one moves sequence when the field is empty." in {
    val mowerTest = LawnMowerObject(coord_i = (0, 0), direction_i = 'N', moves_seq = "ADA")
    val field = Field(upper_left = (5, 5), occupied = List())
    mowerTest.computeMove(field = field)._1.new_direction shouldEqual 'E'
    mowerTest.computeMove(field = field)._1.new_delta_xy shouldEqual (1, 1)
    mowerTest.computeMove(field = field)._2.occupied shouldEqual List((1, 1))
  }

  it should "compute one moves sequence when the field is not empty." in {
    val mowerTest = LawnMowerObject(coord_i = (0, 0), direction_i = 'N', moves_seq = "ADA")
    val field = Field(upper_left = (5, 5), occupied = List((1, 1)))
    mowerTest.computeMove(field = field)._1.new_direction shouldEqual 'E'
    mowerTest.computeMove(field = field)._1.new_delta_xy shouldEqual (0, 1)
    mowerTest.computeMove(field = field)._2.occupied shouldEqual List((1, 1), (0, 1))
  }

  it should "compute one moves sequence when the field is empty and the move outpass the limits." in {
    val mowerTest = LawnMowerObject(coord_i = (0, 0), direction_i = 'W', moves_seq = "AA")
    val field = Field(upper_left = (5, 5), occupied = List())
    mowerTest.computeMove(field = field)._1.new_direction shouldEqual 'W'
    mowerTest.computeMove(field = field)._1.new_delta_xy shouldEqual (0, 0)
    mowerTest.computeMove(field = field)._2.occupied shouldEqual List((0, 0))
  }

  it should "give the final position and direction." in {
    val mowerTest = LawnMowerObject(coord_i = (1, 2), direction_i = 'W', moves_seq = "AA")
    val mowerAfterMoveTest = MowerAfterMove(new_direction = 'W', new_delta_xy = (1, 3))
    mowerTest.giveFinalPosition(mowerAfterMoveTest).final_direction shouldEqual'W'
    mowerTest.giveFinalPosition(mowerAfterMoveTest).final_coord shouldEqual (2, 5)
  }

}