package work.payne.challenges

import work.payne.MyLogging

case class ListAToListListA() extends MyLogging with CodingProblem {


  def run():Unit = {


    val inputList: IndexedSeq[Int] = for {
      i <- 0 to 10000
    } yield {i}

    val largeList = inputList.toSeq

    val t1 = System.nanoTime()
    val res = davidsFunction(largeList, addOne)
    val t2 = System.nanoTime()



    val res2 = ziadsFunction(largeList, addOne)
    val t3 = System.nanoTime()


    val res3 = brentsFunction(largeList, addOne)
    val t4 = System.nanoTime()

    println("result")
//    println(res)
    println(s"Davids time   = ${t2 - t1}")
//    println(res2)
    println(s"Ziads time    = ${t3 - t2}")
    println(s"Brents time   = ${t4 - t2}")

    val t = "asdf"
    t.spl
  }


  def addOne(x: Int):Int = { x + 1}


  def davidsFunction(list: Seq[Int], function: (Int) => Int):Seq[Seq[Int]] = {
    val applied = list.map(function)
    applied.zipWithIndex.map {
      case (value, index) => {
        val (l, r) = list.splitAt(index)
        (l ++ Seq(value) ) ++ (if (r.nonEmpty) r.tail else Seq.empty[Int])
      }
    }
  }





  def ziadsFunction(list: Seq[Int], f: (Int) => Int): Seq[Seq[Int]] = {
    for {i <- 0 to list.length-1} yield { list.updated(i, f(list(i))) }
  }


  def brentsFunction(l: Seq[Int], f: (Int) => Int): Seq[Seq[Int]] = {
    (0 to l.length - 1).toList.map { pivot =>
      l.slice(0, pivot) ++ l.slice(pivot, pivot+1).map(f) ++ l.slice(pivot+1, l.length)
    }
  }

}
