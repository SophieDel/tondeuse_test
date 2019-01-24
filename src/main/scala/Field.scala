
case class Field (upper_left: (Int, Int) = (5, 5), occupied: List[(Int, Int)]) {  // Je ne trouve pas pertinent de mettre une valeur par défaut pour upper_left (qui devrait s'appeler upperLeft d'ailleurs ;))

  def addOccupied(coordOccupied: (Int, Int)): Field = {
    copy(occupied = occupied :+ coordOccupied)
  }
}