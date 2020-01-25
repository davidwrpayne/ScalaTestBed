package work.payne.challenges.cracking

import scala.collection.mutable

/**
 * Problem statement
 * Loop detection write an algorithm that returns the node at the start of a cycle if there is one in a linked list
 */
object Problem2_8 extends App {


  class Node[T](val value: T,var next: Option[Node[T]] = None) {
    def next(node: Node[T]): Node[T] = {
      next = Some(node)
      this
    }
  }

  object Node {
    def apply[T](value: T): Node[T] = {
      new Node(value)
    }

  }

  def hasCycleUsingHashSet[T](node: Node[T]): Option[Node[T]] = {
    var current = node
    val seen = new mutable.HashSet[Node[T]]()

    while(current.next.isDefined && !seen.contains(current.next.get)) {
      current = current.next.get
      seen.+=(current)
    }
    if(current.next.isDefined && seen.contains(current.next.get)) Some(current.next.get)
    else None
  }


  def hasCycleUsingPointers[T](node: Node[T]):Option[Node[T]] = {

    var p1 = node
    var p2 = node
    var cycleDetected = false
    while (p1.next.isDefined && p2.next.isDefined && p2.next.get.next.isDefined && !cycleDetected){
      p1 = p1.next.get
      p2 = p2.next.get.next.get
      if (p1 == p2) cycleDetected = true
    }
    if (cycleDetected) {
      val seen = new mutable.HashSet[Node[T]]
      while(!seen.contains(p1)){
        p1 = p1.next.get
        seen.+=(p1.next.get)
      }
      p2 = node
      while(!seen.contains(p2.next.get)) {
        p2 = p2.next.get
      }
      Some(p2.next.get)
    } else {
      None
    }
  }



  val a = Node(1)
  val b = Node(2)
  val c = Node(3)
  val d = Node(3)
  val e = Node(4)

  a.next(b)
  b.next(c)
  c.next(d)
  d.next(e)
  e.next(c)

  val cycleNode = hasCycleUsingPointers(a)
  println(cycleNode.contains(c))

}
