package work.payne.testScripts

import spray.http._

import scala.util.{Failure, Success, Try}


/**
  * This script was to test something I noticed Zack do during a Pull request into Storefront service
  *
  */
case class Script1() extends TestScript{
  override def run(): Unit = {

    val request = HttpResponse(StatusCodes.OK,HttpEntity.apply("ntoth"),List.empty[HttpHeader],HttpProtocols.`HTTP/1.1`)
    finishTimer1(Timer())(Success(request))

    def finishTimer1(timer: Timer): PartialFunction[Try[HttpResponse], Unit] = {
      case any =>
        timer.stopTimer()

        any match {
          case Success(rsp) =>
            AllMetrics.counterForResponse(rsp).inc()
            AllMetrics.successCounter.inc()
          case Failure(_) =>
            AllMetrics.failureCounter.inc()
        }
    }


    case class Timer() {
      def stopTimer():Int = {
        1
      }
    }


    object AllMetrics {
      def counterForResponse(rsp: HttpResponse): Counter = Counter("HttpResponse")
      def successCounter: Counter = Counter("success")
      def failureCounter: Counter = Counter("failure")
    }

    case class Counter(name: String) {
      def inc() = {
        println(s"$name: count ++")
      }
    }


  }
}
