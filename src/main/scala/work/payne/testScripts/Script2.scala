package work.payne.testScripts

import akka.actor.ActorSystem
import spray.http._

import scala.concurrent.Future


case class Script2() extends TestScript{


  override def run(): Unit = {

    val uri = "http://payne.work/drinks"

    import spray.http._
    import spray.client.pipelining._

    implicit val system = ActorSystem()
    import system.dispatcher // execution context for futures

    val pipeline: HttpRequest => Future[HttpResponse] = sendReceive

    val response: Future[HttpResponse] = pipeline(Get(uri))

    response.map( x => {
      println(s"status: ${x.status.intValue}")
      println(x.entity.asString)
    })



  }

  def startup() = {

  }


}
