object TondeusesSeq {

  // Quand tu as des structures un peu complexes comme ça : ((Int, Int), Char, String) je préfère créer une case class
  // pour que ça soit plus lisible

  val mowers_seq: Option[List[((Int, Int), Char, String)]] = Some(List(((1, 2), 'N', "GAGAGAGAA"),
    ((3, 3), 'E', "AADAADADDA")))


}

