package work.payne

import work.payne.challenges.RepeatPatternProblem

import scala.concurrent.{Await, Future}

object Boot extends App with MyLogging {

  val problem = new RepeatPatternProblem
  problem.run()

}


