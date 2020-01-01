package work.payne.challenges.cracking

import work.payne.challenges.CodingProblem

import scala.collection.mutable

class Problem1_1 extends CodingProblem {
  /** Implement a function that determines if a string has all unique elements */
  /** What if you can't use an additional datastructure. */
  override def run(): Unit = {
    val unique = "abcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()-=_+/.,<>?"
    val non_unique = "abcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()-=_+/.,<>?AA"
    println(s"unique elements $unique = ${allUniqueElementsNoExtraDataStructure(unique)}")
  }

  // initial thought is it set up a hashset of each character

  // for if we can't use extra information my thought is to loop but only loop over the remaining text as we've already
  // checked the earlier portion of text.

  def allUniqueElements(input: String): Boolean = {
    val seen = mutable.HashSet[Char]()
    input.foldLeft(true) {
      case (false, _) => false
      case (true, currentChar: Char) if (seen.contains(currentChar)) => false
      case (true, currentChar) => seen.+=(currentChar); true
    }
  }


  def allUniqueElementsNoExtraDataStructure(input: String): Boolean = {
    for (i <- 0 until input.size) {
      val currentChar = input(i)
      for (j <- i+1 until input.size) {
        if (input.charAt(j) == currentChar) {
          return false
        }
      }
    }
    true
  }


}
