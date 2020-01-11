package work.payne.challenges.cracking

import work.payne.challenges.CodingProblem

/**
 * Problem Statement
 * class country with population and continent
 * write function getPopulation(countries Seq[Country], continent: String)
 */
class Problem13_7 extends CodingProblem {


  case class Country(name: String, continent: String, population: Int)



  override def run(): Unit = {

    val countries = Seq(
      Country("usa","north america", 300000000),
      Country("mexico", "north america", 129000000),
      Country("canada", "north america", 60000000),
      Country("guatamala", "south america", 70000000),
      Country("england", "europe", 128000000)
    )

    def getPopulation(countries: Seq[Country], continent: String): Int = {
      countries.foldLeft[Int](0)( (sum, c) => if(c.continent == continent) c.population + sum else sum )
    }

    def getPop(countries: Seq[Country], continent: String): Int = {
      countries.filter(c => c.continent == continent)
        .map(c => c.population)
        .fold(0)((a,b) => a+b)
    }
    println(getPop(countries, "north america"))
  }
}