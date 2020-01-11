package work.payne.challenges.cracking

import java.util

import work.payne.challenges.CodingProblem

import scala.collection.mutable

/**
 * Given a binary tree design an algorithm that creates a linked list of nodes at each depth.
 *
 * My first thought for this was to use DFS but I think BFS is simpler
 */
class Problem4_3 extends CodingProblem {



  case class TreeNode[T](var left: TreeNode[T],value: T, var right: TreeNode[T])
  val list = new mutable.ArraySeq[Option[mutable.MutableList[Int]]](0)



  override def run(): Unit = {

  }


//  def buildList()

  def addValueAtDepth[T](depth: Int, v: T, list: mutable.ArraySeq[Option[mutable.MutableList[T]]]): Unit = {
    if (list.size < depth) {
      list.padTo(depth, None)
    }
    list(depth) match {
      case None =>
        val newList = new mutable.MutableList[T]()
        newList.+=(v)
        list.update(depth, Some(newList))
      case Some(list) => list.+=(v)
    }
  }




}
