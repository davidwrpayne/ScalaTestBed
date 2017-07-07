package work.payne.challenges

import work.payne.MyLogging

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}
import scala.concurrent.duration._

class TestForComprehensionNumber2 extends MyLogging with CodingProblem{

  def run(): Unit = {
    log(s"running class ${this.getClass.getName}")
    val t = (createFuture("tag1")).flatMap {
      case something => (createFuture(s"tag2 size: ${something.size}")).flatMap {
        case _ => (createFuture2()).map { case thestuff => thestuff }
    }
    }
    log("waiting on the stuff")

    t.map(log)
    Await.result(t, 1 seconds)
  }

  def createFuture(string: String):Future[List[Int]] = {
    log(s"${string} called")
    Thread.sleep(1000) // wait 1000 seconds?
    Future.successful(List(1,2,3,4,5,6,3))
  }

  def createFuture2(): Future[Boolean] = Future {
    log("future two called")
    true
  }

  def combineFutures(b1: Boolean, b2: Boolean): Future[Boolean] = {
    log("combineFutures called")
    Future.successful(b1 && b2)
  }

}
