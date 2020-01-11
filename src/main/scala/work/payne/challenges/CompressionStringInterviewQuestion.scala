package work.payne.challenges

import work.payne.MyLogging

import scala.collection.mutable

case class CompressionStringInterviewQuestion() extends MyLogging with CodingProblem{



  def run(): Unit = {

    val input = "aaaabbcc"

//    val timer = System.nanoTime()
//    val res = compressNonRecursive(input)
//    val endTime = System.nanoTime()

//    val resultTime = (endTime - timer)
//    println(s"It took: $resultTime nanoseconds\n")
    println(s" inputLength = ${input.size}")
//    println(s" res   = $res")

//    val timerREC = System.nanoTime()
//    val resREC = compressNonRecursive(input)
//    val endTimeREC = System.nanoTime()

//    val resultTimeREC = (endTimeREC - timerREC)
//    println(s"It took: $resultTimeREC nanoseconds \n")
    println(s" inputLength = ${input.size}")
//    println(s" res   = $resREC")
  }


  def compressRecursive(input: String): String = {
    if( input.length <= 0 ) {
      ""
    } else {
      compressRecursive("",input.head,0,input.toList)
    }
  }

  def compressRecursive(result: String, curChar: Char, count: Int, remaining: List[Char]):String = {
    remaining match {
      case head::Nil =>
        if( curChar.equals(head)){ s"$result$curChar${count+1}"}
        else s"$result$curChar$count${head}1"
      case head::tail =>
        if (curChar.equals(head)){ compressRecursive(result,curChar,count+1,tail) }
        else {compressRecursive(s"$result$curChar$count",head,1,tail)}
      case _ =>
        throw new Exception(s"Failure in Compressing current compression $result, curChar = $curChar, remaining = $remaining")
    }
  }


//
//  def compressNonRecursive(input: String):String = {
//
//    val i = input.iterator
//    var lastChar: Option[Char] = None
//    var curChar: Option[Char] = None
//    var count: Int = 0
//    val result = mutable.StringBuilder.newBuilder
//    while(i.hasNext) {
//      curChar = Some(i.next())
//      if(lastChar.contains(curChar.get)) {
//        count += 1
//      } else {
//        if( lastChar.isDefined && count > 0) {
//          result.append(lastChar.get).append(count)
//        }
//        lastChar = curChar
//        count = 1
//      }
//    }
//    if( lastChar.isDefined && count > 0) {
//      result.append(lastChar.get).append(count)
//    }
//    result.toString()
//  }





}
