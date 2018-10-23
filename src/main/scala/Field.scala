
case class Field (upper_left: (Int, Int) = (5, 5), occupied: List[(Int, Int)]) {

  def addOccupied(coordOccupied: (Int, Int)): Field = {
    copy(occupied = occupied :+ coordOccupied)
  }
}