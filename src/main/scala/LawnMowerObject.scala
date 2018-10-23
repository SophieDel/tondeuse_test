

case class LawnMowerObject(coord_i: (Int, Int), direction_i: Char, moves_seq: String) {

  def giveFinalPosition(mowerAfterMove: MowerAfterMove): LawnMowerFinalPosition = {
    LawnMowerFinalPosition(final_direction = mowerAfterMove.new_direction,
      final_coord = (this.coord_i._1 + mowerAfterMove.new_delta_xy._1, this.coord_i._2 + mowerAfterMove.new_delta_xy._2 ))
  }

  def computeMove(field: Field): (MowerAfterMove, Field) = {

    def computeOneDelta(move_index: Int, direction: Char, coord_ini: (Int, Int), moves_seq: String,
                          delta_xy: (Int, Int), field: Field): (MowerAfterMove, Field) = {
      if (move_index >= moves_seq.length)
        (MowerAfterMove(new_direction = direction, new_delta_xy = delta_xy),
          field.addOccupied(coord_ini._1 + delta_xy._1, coord_ini._2 + delta_xy._2))
      else {
        val move = moves_seq.charAt(move_index)
        val mowerAfterMoveDelta = computeDirection(direction, move)
        val new_direction = mowerAfterMoveDelta.new_direction
        val x = coord_ini._1 + delta_xy._1 + mowerAfterMoveDelta.new_delta_xy._1
        val y = coord_ini._2 + delta_xy._2 + mowerAfterMoveDelta.new_delta_xy._2
        val delta_xy_f =
        if (x <= field.upper_left._1 && x >= 0 &&
            y <= field.upper_left._2 && y >= 0 &&
              !field.occupied.contains((x, y))
          )
        (delta_xy._1 + mowerAfterMoveDelta.new_delta_xy._1, delta_xy._2 + mowerAfterMoveDelta.new_delta_xy._2)
        else {
            (delta_xy._1, delta_xy._2)
          }

        computeOneDelta(move_index=move_index + 1, direction=new_direction, coord_ini=coord_ini,
            moves_seq=moves_seq, delta_xy=delta_xy_f, field=field)
      }
    }
    computeOneDelta(move_index=0, direction=direction_i, coord_ini=coord_i,
      moves_seq=moves_seq,  delta_xy = (0, 0), field=field)
  }

  def computeDirection(direction: Char, move: Char): MowerAfterMove = {

    val mowerAfterMove = if (List('G', 'D') contains move) {

      val new_direction_num_tmp =
        if (move == 'G')
          (MapDirectionAngle.dico(direction) + 0.5) % 2
        else
          (MapDirectionAngle.dico(direction) - 0.5) % 2

      val new_direction_num =
        if (new_direction_num_tmp < 0)
           2 + new_direction_num_tmp
        else
          new_direction_num_tmp

      val new_direction = MapDirectionAngle.reverseDico(new_direction_num)
      val delta_x = 0
      val delta_y = 0
      val mowerAfterMove = MowerAfterMove(new_direction = new_direction, new_delta_xy = (delta_x, delta_y))
      mowerAfterMove
    }

    else if (move == 'A') {
      val (delta_x, delta_y) =
        if (direction == 'E') (1, 0)
        else if (direction == 'N') (0, 1)
        else if (direction == 'W') (-1, 0)
        else if (direction == 'S') (0, -1)
        else {
          throw new IllegalArgumentException("Direction is not E, W, N nor S.")
        }
      val mowerAfterMove = MowerAfterMove(new_direction = direction, new_delta_xy = (delta_x, delta_y))
      mowerAfterMove
    }

    else {
      throw new IllegalArgumentException("Move is not G, D, A.")
    }

    mowerAfterMove
  }

}
