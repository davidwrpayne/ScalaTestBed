package work.payne.challenges.cracking

/**
 * Problem statement:
 * Determine if two Linked Lists are intersecting. Intersecting means that the Jth node of one list
 * and the Kth node of another list is the same node. Not the same value but same node
 */
object Problem2_7 extends App {

  /** first instinct was run pointers to the end of the lists then check that last pointer matches */
  /** This only works on a non cycling Linked List. */
  /** if there is a cycle then both linked lists should be checked for the start of the cycle node and compare that we
   * can end up checking the cycle nodes against each other to see whether or not they match.
   */

   /** Solution for non cycling linked lists */


  class Node[T](val value: T,val next: Option[Node[T]]) {
  }

  def getEndNode[T](root: Node[T]): Node[T] = {
    var current = root
    while(current.next.isDefined) {
      current = current.next.get
    }
    current
  }

  def listsIntersect[T](rootA: Node[T], rootB: Node[T]): Boolean = {
    val endA = getEndNode(rootA)
    val endB = getEndNode(rootB)
    endA == endB
  }

  val listA = new Node(1,Some(new Node(2,Some(new Node(3,Some(new Node(4, None)))))))
  val listB = new Node(1,Some(new Node(2,Some(new Node(3,Some(new Node(4, None)))))))

  println(listsIntersect[Int](listA,listB))

  val subList = new Node(3,Some(new Node(4, None)))
  val listC = new Node(1,Some(new Node(2,Some(subList))))
  val listD = new Node(1,Some(new Node(2,Some(new Node(3,Some(subList))))))
  println(listsIntersect(listC, listD))

  val subList2 = new Node(3, None)
  val listE = new Node(1,Some(new Node(2,Some(subList2))))
  val listF = new Node(1,Some(new Node(2,Some(new Node(3,Some(subList2))))))
  println(listsIntersect(listE, listF))
}
