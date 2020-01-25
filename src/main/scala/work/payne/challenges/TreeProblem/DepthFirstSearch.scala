package work.payne.challenges.TreeProblem

import scala.collection.mutable

object DepthFirstSearch extends App {





  case class Node(value: String)

  case class Graph(var edges: Seq[(Node,Node)],var nodes: Seq[Node])

  val nodeA = Node("a")
  val nodeB = Node("b")
  val nodeC = Node("c")
  val newGraph = Graph(
    Seq(
      (nodeA, nodeB),
      (nodeB, nodeC),
      (nodeC, nodeA)
    ),
    Seq(nodeA,nodeB,nodeC)
  )




  def canReachAllNodesFromThisNode(graph: Graph, node: Node): Boolean = {
    val explored = new mutable.HashSet[Node]()
    val toExplore = new mutable.Stack[Node]()
    toExplore.push(node)

    while (toExplore.nonEmpty) {
      val currentNode = toExplore.pop()
      explored.+=(currentNode)
      graph.edges.foreach(node =>
        if (node._1 == currentNode) {
          if (!explored(node._2)) {
            toExplore.push(node._2)
          }
        }
      )
    }

    var allReached = true
    graph.nodes.foreach( node =>
      allReached = allReached && explored(node)
    )
    allReached
  }

  println(canReachAllNodesFromThisNode(newGraph, nodeB))

}
