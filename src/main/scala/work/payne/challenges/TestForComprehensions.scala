package work.payne.challenges

import work.payne.MyLogging

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, _}
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

case class TestForComprehensions() extends MyLogging {


  def run(): Unit = {

    log(s"running class ${this.getClass.getName}")

    val f: Future[Boolean] = createFuture()

    f onComplete{
      case Success(b:Boolean) => log(b)
      case Failure(exception) =>
        log("exception happened" + exception.toString)
    }


//    log(success)

  }

  def createFuture():Future[Boolean] = {
    log("create Future called")
    if( true) {
      throw new Exception("Something terrible happened")
    } else {
      Future.successful(true)
    }

  }

  def createFuture2(): Future[Boolean] = {
    log("future two")
    Future.successful(false)
  }

  def combineFutures(b1: Boolean, b2: Boolean): Future[Boolean] = {
    log("combineFutures called")
    Future.successful(b1 && b2)
  }

}
