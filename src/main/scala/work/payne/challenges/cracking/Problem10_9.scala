package work.payne.challenges.cracking

import scala.collection.mutable
import scala.util.Random

object Problem10_9 extends App {


  /**
   * The problem statement
   *
   * Design an alogrithm to find an element in a matrix
   * where the rows are sorted asc and the columns are sorted ascending
   * e.g.
   * 1  3  6
   * 2  4  8
   * 5  9  10
   *
   *
   */


  def run(): Unit = {
    var m = 35
    var n = 5
    //    printMatrix(buildSortedArrayByLayers(8,8))
    Random.setSeed(100L)
    val matrix = buildSortedArrayByLayers(m, n)
    printMatrix(matrix)
    println(findElement_mLogN(254, matrix))

  }

  def findElement_mLogN(element: Int, matrix: Array[Array[Int]]): Option[(Int,Int)] = {
    matrix.zipWithIndex.foldLeft[Option[(Int,Int)]](None)({
      case (Some(v), _) => Some(v) // found it already short circuit to end
      case (None, (_, row)) =>
        binarySearchRow(element, matrix, row) match {
          case Some(col) => Some((row,col))
          case None => None
        }
    })
  }

  def binarySearchRow(element: Int, matrix: Array[Array[Int]], row: Int): Option[Int] = {
    var start = 0
    var end = matrix.size - 1
    var found = false
    var midpoint = 0
    while (!found && start < end ) {
      midpoint = (end - start) / 2 + start
      matrix(midpoint)(row) match {
        case x if x == element => found = true;
        case x if x < element => start = midpoint + 1 // element is greater search right side of midpoint
        case x if x > element => end = midpoint - 1
      }
    }
    if (found) {
      Some(midpoint)
    } else {
      None
    }
  }

  def binarySearchColumn(element: Int, matrix: Array[Array[Int]], col: Int): Option[Int] = {
    var topOffset = 0
    var bottomOffset = matrix(col).size - 1
    var found = false
    var middlePivot = 0
    while (topOffset != bottomOffset && !found) {
      middlePivot = (bottomOffset - topOffset) / 2 + topOffset
      matrix(col)(middlePivot) match {
        case x if x == element => found = true;
        case x if x < element => topOffset = middlePivot
        case x if x > element => bottomOffset = middlePivot
      }
    }
    if (found) {
      Some(middlePivot)
    } else {
      None
    }
  }

  def buildRandomArray(m: Int, n: Int): Array[Array[Int]] = {
    var seen = mutable.Set[Int]()
    var matrix = new Array[Array[Int]](m)
    for (i <- 0 until m) {
      matrix(i) = new Array[Int](n)
      for (j <- matrix(i).indices) {
        var v = Random.nextInt(100)
        while (seen.contains(v)) {
          v += 1
        }
        matrix(i)(j) = v
        seen.add(v)
      }
    }
    matrix
  }

  def printMatrix(matrix: Array[Array[Int]]): Unit = {
    val m = matrix.size
    val n = matrix(0).size
    for (y <- 0 until n) {
      for (x <- 0 until m) {
        print("%7d ".format(matrix(x)(y)))
      }
      println()
    }
  }


  def buildSortedArrayByLayers(m: Int, n: Int): Array[Array[Int]] = {
    var matrix = new Array[Array[Int]](m)
    for (i <- 0 until m) {
      matrix(i) = new Array[Int](n)
    }

    // start at 0 randomly m
    var prev = 0
    var sortedSet: Array[Int] = getRandomSortedSetArray(m * n)

    var nextSortedElementIndex = 0
    for (layer <- 0 until m + n) {
      val layerStartsInTopRow = layer < m
      var x, y = 0
      if (layerStartsInTopRow) {
        x = layer
        y = 0
      } else {
        x = m - 1
        y = layer - m + 1
      }
      while (x >= 0 && y < n) {
        matrix(x)(y) = sortedSet(nextSortedElementIndex)
        x -= 1
        y += 1
        nextSortedElementIndex += 1
      }
    }
    matrix
  }

  private def getRandomSortedSetArray(length: Int): Array[Int] = {
    var sortedSet = new Array[Int](length)
    Range(0, length).zipWithIndex.foldLeft(0)((prev, indexValue) => {
      val nextValue = prev + Random.nextInt(10) + 1
      sortedSet(indexValue._1) = nextValue
      nextValue
    })
    sortedSet
  }


  def buildSortedMatrix(m: Int, n: Int): Array[Array[Int]] = {
    val set = getRandomSortedSetArray(m * n)
    val matrix = new Array[Array[Int]](m)
    val fullMatrix = matrix.map(_ => new Array[Int](n))
    fullMatrix

  }


  run()
}
