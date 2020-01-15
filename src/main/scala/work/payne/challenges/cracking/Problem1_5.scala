package work.payne.challenges.cracking

/**
 * problem 1.5 from cracking the coding interview 6th edition.
 * Summary
 * given 3 types of edits on strings
 * insert a char, remove a char, replace a char
 * write func takes two strings and if they are a single edit away.
 * example
 * pale, ple -> true
 * pales, pale -> true
 * pale, bake -> false
 */

object Problem1_5 extends App {
  
  /**
   * Thoughts off the top
   *
   * word1.size = m
   * word2.size = n
   * If two words are more then a single letter different then they can't be single edit away
   * if (m - n > 1)   return false early
   *
   * if m == n then you need to find just one different letter (single swap)
   *
   * if m + 1 = n then   need to find word2 as substring in word1 word
   * if n + 1 = m then  need to find word1 as substring in word2 word
   */

  def run(): Unit = {
      println(singleEditAway("blaat","blat"))
  }


  def singleLetterRemoved(needle: String, haystack: String): Boolean = {
    val h = haystack.toCharArray
    val n = needle.toCharArray
    var i, j = 0
    val allowedDiff = 1
    var diff = 0
    while(i < h.size && j < n.size && diff <= allowedDiff ) {
      if (h(i) == n(j)) { // letters are matching
        i += 1
        j += 1
      } else { // skip a letter in haystack
        i += 1
        diff += 1
      }
    }
    if (diff == allowedDiff) {
      true
    } else {
      false
    }

  }


  def singleLetterSwapped(a: String, b: String): Boolean = {
    val h: Array[Char] = a.toCharArray
    val n: Array[Char] = b.toCharArray
    val allowedNumberOfDiffs = 1
    var i, j = 0
    var numberOfDifferentLetters = 0
    while(i < h.length && j < n.length && numberOfDifferentLetters <= allowedNumberOfDiffs) {
      if (h(i) != n(j)) { // reset the needle index
        numberOfDifferentLetters += 1
      }
        i += 1
        j += 1
    }
    if (numberOfDifferentLetters <= allowedNumberOfDiffs && numberOfDifferentLetters > 0) {
      true
    } else {
      false
    }
  }

  def singleEditAway(wordA: String, wordB: String): Boolean = {
    (wordA.length, wordB.length) match {
      case (a, b) if a + 1 == b => singleLetterRemoved(wordA, wordB)
      case (a, b) if a == b + 1 => singleLetterRemoved(wordB, wordA)
      case (a, b) if a == b => singleLetterSwapped(wordA, wordB)
      case (_, _) => false
    }
  }



  run()
}
