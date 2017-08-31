package work.payne.challenges

import work.payne.MyLogging

class RepeatPatternProblem extends CodingProblem with MyLogging {


  override def run(): Unit = {


//    log("Starting Repeat")
//
//
//    log( PatternProducer.getPattern(4))
    PatternProducer.getPattern(10)

    object PatternProducer {
      def getPattern(n: Int): String = {
        var curPattern = "1"
        for ( _ <- 0 until n) {
          curPattern = createPatternFromPreviousPattern(curPattern)
        }
        curPattern
//        if (n == 1) "1" else createPatternFromPreviousPattern(getPattern(n - 1))
      }

      def createPatternFromPreviousPattern(str: String): String = {
        // countOfLastChar, lastChar, Accumulated
        type CharCount = (Int, String, String)
        val inValue: CharCount = (0, "", "")
        val result = str.foldLeft(inValue)((acc: (Int, String, String), c: Char) => {
          val s = c.toString
          acc match {
            case (number, current, result) if current == s =>
              (acc._1 + 1, s, acc._3)
            case (number, current, result) if number > 0 && current != s =>
              (1, s, s"${acc._3}${acc._1}${acc._2}")
            case _ =>
              (1,s,"")
          }
        })

        val res = s"${result._3}${result._1}${result._2}"
        println(res)
        res
      }
    }

  }


}
