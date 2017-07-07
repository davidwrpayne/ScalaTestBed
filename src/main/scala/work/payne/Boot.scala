package work.payne

import java.util

import work.payne.challenges._

import scala.concurrent.{Await, Future}

object Boot extends App with MyLogging{
    new LRUCacheProblem().run()
  }

