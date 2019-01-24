object MapDirectionAngle {
  val dico = Map('E' -> 0.0, 'N'-> 0.5, 'W'-> 1.0, 'S'-> 1.5) // Pas ouf dico comme nom de variable
  val reverseDico = dico.map(_.swap)

}
