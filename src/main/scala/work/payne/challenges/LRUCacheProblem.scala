package work.payne.challenges

import scala.collection.mutable

class LRUCacheProblem extends CodingProblem{
  override def run(): Unit = {





    case class LRUCache(size: Int) {

      val nodePointers = mutable.HashMap[String,String]()
      val orderedList = mutable.MutableList()

      def set(key: String, value: String) = {

      }

      def get(key: String):Option[String] = {

        None
      }

    }


  }
}
