package work.payne.challenges


import scala.collection.mutable
import scala.util.Random

/**
 * order statistics concept
 * Starts with the problem find Min element
 * Then find 2nd Min element
 * This problem is to use code to find the nth smallest item from an unorderered set of numbers.
 * find the nth order statistic of the unordered set.
 * You can solve it in N time. Which is a bit of a gotcha.
 */
class FindNthSmallestNumber extends CodingProblem {

  def findMin(input: Seq[Int]): Int = {

    var min = input.head
    input.foreach(i => {
      if (i < min) {
        min = i
      }
    })
    min
  }

  override def run(): Unit = {
    println(findNthOrderedStatistic(Seq(99, 10, 33, 43, 22, 13, 596, 5, 11, 7, 43), 5))
  }


  def findNthOrderedStatistic(input: Seq[Int], n: Int): Int = {
    if (input.size == 1 && n == 0) {
      input.head
    } else {
      assert(input.size > n, "input was less then or equal to n so can't return nth ordered statistic")
      var lessThanPivot: mutable.Seq[Int] = collection.mutable.Seq[Int]()
      var moreThanPivot: mutable.Seq[Int] = collection.mutable.Seq[Int]()
      val pivotIndex = Random.nextInt(input.size)

      for (i <- input.indices) {
        if (i != pivotIndex) { // while not pivot
          if (input(i) < input(pivotIndex)) {
            lessThanPivot = lessThanPivot.:+(input(i))
          } else {
            moreThanPivot = moreThanPivot.:+(input(i))
          }
        }
      }

      if (lessThanPivot.size == n) {
        input(pivotIndex)
      } else if (lessThanPivot.size > n) {
        findNthOrderedStatistic(lessThanPivot, n)
      } else {
        findNthOrderedStatistic(moreThanPivot, n - lessThanPivot.size - 1)
      }
    }
  }

}
