package work.payne


import spray.http._
import work.payne.testScripts.Script1

import scala.util.{Failure, Success, Try}

object Boot extends App with MyLogging {
  Script1().run()
}


