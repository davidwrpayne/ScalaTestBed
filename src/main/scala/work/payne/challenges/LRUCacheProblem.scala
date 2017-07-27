package work.payne.challenges

import java.util.logging.Logger

import scala.collection.mutable

class LRUCacheProblem extends CodingProblem{
  override def run(): Unit = {
    // LRU remove the least recently used item
    // item not used last.
    val logger = Logger.getGlobal

    val mb = 1024*1024
    val runtime = Runtime.getRuntime
    logger.info("** Used Memory:  " + (runtime.totalMemory - runtime.freeMemory) / mb)
    logger.info("** Free Memory:  " + runtime.freeMemory / mb)
    logger.info("** Total Memory: " + runtime.totalMemory / mb)
    logger.info("** Max Memory:   " + runtime.maxMemory / mb)

    val cache = LRUCache(40)
    cache.printCache()

    cache.set("a","a")
    cache.set("b","b")
    cache.set("c","c")
    cache.set("d","d")

    for (
      x <- 1 to 5000000
    ) {
      cache.set(s"$x",s"$x")
    }
    cache.printCache()
    cache.get("c")
    cache.printCache()
    cache.set("e","e")
    cache.printCache()
//    runtime = Runtime.getRuntime


    logger.info("** Used Memory:  " + (runtime.totalMemory - runtime.freeMemory) / mb)
    logger.info("** Free Memory:  " + runtime.freeMemory / mb)
    logger.info("** Total Memory: " + runtime.totalMemory / mb)
    logger.info("** Max Memory:   " + runtime.maxMemory / mb)
    System.gc()
    Thread.sleep(100000)
    logger.info("** Used Memory:  " + (runtime.totalMemory - runtime.freeMemory) / mb)
    logger.info("** Free Memory:  " + runtime.freeMemory / mb)
    logger.info("** Total Memory: " + runtime.totalMemory / mb)
    logger.info("** Max Memory:   " + runtime.maxMemory / mb)
  }

    case class LRUCache(var size: Int) {

      var currentSize = 0
      val nodePointers = mutable.HashMap[String,MyNode]()
      val orderedList = MyList()

      def set(key: String, value: String) = {
        if (currentSize == size) {
          orderedList.deleteFromTail()
          nodePointers.-=(key)
          currentSize -= 1
        }
        val n = nodePointers.get(key)
        if(n.isDefined) {
          val node = n.get
          node.value = value
          orderedList.moveToFront(node)
        } else {
          val n = MyNode(None,None,value)
          orderedList.addToFront(n)
          nodePointers.+=((key,n))
          currentSize += 1
        }

      }

      def get(key: String):Option[String] = {
        val n = nodePointers.get(key)
        if (n.isDefined) {
          orderedList.moveToFront(n.get)
          Some(n.get.value)
        } else {
          None
        }
      }

      def printCache() = {
        orderedList.printList()
      }
    }



    case class MyNode(var prev: Option[MyNode], var next: Option[MyNode], var value: String){
      override def equals(obj: scala.Any): Boolean = {
        obj match {
          case x: MyNode => x.value == value
          case _ => false
        }
      }

    }

    case class MyList() {
      var head: Option[MyNode] = None
      var tail: Option[MyNode] = None


      def addToFront(node: MyNode): Unit = {
        val n = Option(node)
        head.foreach(_.prev = n)
        n.foreach(_.next = head)
        node.prev = None
        head = n
        if(tail.isEmpty) {
          tail = n
        }
      }

      def moveToFront(n: MyNode): Unit = {
        if ( head.contains(n)) {
          return
        }
        val a = n.prev
        val b = n.next
        if ( tail.contains(n)) {
          tail = a
        }
        a.foreach(_.next = b)
        b.foreach(_.prev = a)
        val node = Some(n)
        head.foreach(_.prev = node)
        n.next = head
        head = node
      }

      def deleteFromTail(): Unit = {
        if (tail.isDefined) {
          val last = tail.get
          tail = last.prev
          tail.foreach(_.next = None)
          last.prev = None
          last.next = None
        }
      }
      def printList() = {
        var pointer = head
        var first = true
        while(pointer.isDefined) {
          if(first){
            first = false
          } else {
            print(", ")
          }
          print(s"'${pointer.get.value}'")
          pointer = pointer.get.next
        }
        println("")
      }
    }

}
