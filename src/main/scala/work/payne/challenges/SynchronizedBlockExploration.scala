package work.payne.challenges


import scala.concurrent.{Await, Future}

case class SynchronizedBlockExploration() extends CodingProblem{
  override def run(): Unit = {
//    import scala.concurrent.ExecutionContext.Implicits.global
//    val storage = DataStorage(0)
//
//    val f1 = Future{
//      Thread.sleep(24)
//      println(s"Thread 1: ${System.nanoTime()}")
//      val newValue = storage.getData() + 4
//      storage.setData(newValue)
//    }
//    val f2 = Future{
//      Thread.sleep(20)
//      println(s"Thread 2: ${System.nanoTime()}")
//      val newValue = storage.getData() + 10
//      storage.setData(newValue)
//    }
//
//    val t = for {
//      _ <- f1
//      _ <- f2
//    } yield {
//      println("success")
//    }
//
//    import scala.concurrent.duration._
//    Await.result(t, 10 seconds)
//  }








}


object SynchronizedBlockExploration {

//
//  case class DataStorage(init: Int) {
//    var myData: Int = init
//    def getData(): Int = this.synchronized{
//      synchronized(
//        myData
//      )
//    }
//    def setData(in: Int): Int = {
//      synchronized({
//        myData = in
//      }
//      )
//    }
  }
}