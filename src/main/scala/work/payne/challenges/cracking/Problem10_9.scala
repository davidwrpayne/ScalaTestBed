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
   *     1  3  6
   *     2  4  8
   *     5  9  10
   *
   *
   */
//
//
//
//  // bruteforce option
//  def binarySearchMatrix(e: Int, matrix: Array[Array[Int]]): Option[(Int,Int)] = {
//
//
//    for (i <- matrix.indices) {
//      matrix(i)(0)
//    }
//  }

  def binarySearchRow(element: Int, matrix: Array[Array[Int]], row: Int): Option[Int] = {
    var left = 0
    var right = matrix.size - 1
    var found = false
    var pivot = 0
    while(left != right && ! found) {
      pivot = (right - left)/ 2
      pivot match {
        case x if x == element => found = true;
        case x if x < element => right = pivot
        case x if x > element => left = pivot
      }
    }
    if (found) {
      Some(pivot)
    } else {
      None
    }
  }
  var m = 3
  var n = 10



  def buildRandomArray(m: Int, n: Int): Array[Array[Int]] = {
    var seen = mutable.Set[Int]()
    var matrix = new Array[Array[Int]](m)
    for(i <- 0 until m) {
      matrix(i) = new Array[Int](n)
      for (j <- matrix(i).indices) {
        var v = Random.nextInt(100)
        while(seen.contains(v)) { v += 1}
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



  def buildSortedArray(m: Int, n: Int): Array[Array[Int]] = {
    var matrix = new Array[Array[Int]](m)
    for(i <- 0 until m) {
      matrix(i) = new Array[Int](n)
    }

    // start at 0 randomly m
    var prev = 0

    var sortedSet = new Array[Int](m * n)
    Range(0, m * n).zipWithIndex.foldLeft(0)((prev, indexValue) => {
      val nextValue = prev + Random.nextInt(10) + 1
      sortedSet(indexValue._1) = nextValue
      nextValue
    })

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

  printMatrix(buildSortedArray(8,8))
}
