package work.payne.challenges.cracking

import work.payne.challenges.cracking.Problem1_8.matrix

import scala.collection.mutable
import scala.util.Random

/**
 * Zero Matrix Problem 1.8 from Cracking the Coding Interview
 * Write an algorithm such that if an element in MxN matrix is 0 then
 * its entire row and column are set to zero
 */
object Problem1_8 extends App {
  type Matrix = mutable.ArraySeq[mutable.ArraySeq[Int]]
  // setting up matrix builder
  val M = 10
  val N = 10

  def randomMatrix(seed: Long): Matrix = {
    Random.setSeed(seed)
    val matrix = new mutable.ArraySeq[mutable.ArraySeq[Int]](M)
    for (i <- 0 until M) {
      matrix(i) = new mutable.ArraySeq[Int](N)
    }
    for {
      i <- 0 until M
      j <- 0 until N
    } yield {
      matrix(i)(j) = randomValue(1, 100)
    }
    matrix
  }

  def randomValue(i: Int, i1: Int): Int = {
    Random.nextInt(i1 - i) + 1
  }

  def printMatrix(matrix: Matrix): Unit = {
    for (y <- matrix.indices) {
      for (x <- matrix(y).indices) {

        print("%02d ".format(matrix(y)(x)))
      }
      println()
    }
  }
  val matrix = randomMatrix(403L)


  matrix(5)(3) = 0
  matrix(7)(7) = 0

  printMatrix(matrix)
  println("______________\n")
  printMatrix(zeroColumnAndRows(matrix))


  def setRowToZero(matrix: Matrix, i: Int): Unit = {
    for (x <- matrix(i).indices) {
      matrix(i)(x) = 0
    }
  }

  def setColumnToZero(matrix: Matrix, i: Int): Unit = {
    for(y <- matrix.indices) {
      matrix(y)(i) = 0
    }
  }
  def zeroColumnAndRows(matrix: Matrix): Matrix = {
    var zeroColumns = mutable.Set[Int]()
    var zeroRows = mutable.Set[Int]()
    for (y <- matrix.indices) {
      for (x <- matrix(y).indices) {
        if (matrix(y)(x) == 0) {
          zeroColumns = zeroColumns.+(x)
          zeroRows = zeroRows.+(y)
        }
      }
    }
    zeroColumns.foreach((x => setColumnToZero(matrix, x)))
    zeroRows.foreach((y => setRowToZero(matrix, y)))
    matrix
  }


}
