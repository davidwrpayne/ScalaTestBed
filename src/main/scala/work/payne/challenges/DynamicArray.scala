package work.payne.challenges
import scala.collection.mutable.Seq

class DynamicArray extends CodingProblem {
  var seqList: Seq[Seq[Int]] = Seq.empty[Seq[Int]]
  var lastAnswer = 0
  override def run(): Unit = {


    val instructions = readInput()
    instructions.foreach({
      case (1,x,y) => queryOne(x,y)
      case (2,x,y) => queryTwo(x,y)
      case _ => Unit
    })
  }

  def queryOne(x: Int, y: Int): Unit = {
    val index = (x ^ lastAnswer) % seqList.size
    val newList = seqList(index) ++ Seq(y)
    seqList(index) = newList
  }

  def queryTwo(x: Int, y: Int): Unit = {
    val index = (x ^ lastAnswer) % seqList.size
    val seq = seqList(index)
    lastAnswer = seq(y % seq.size)
    println(lastAnswer)
  }

  def readInput(): Seq[Tuple3[Int,Int,Int]] = {
    val sc = new java.util.Scanner (System.in);
    val n = sc.nextInt()
    val q = sc.nextInt()

    for( numArray <- 0 until n) {
      seqList = seqList ++ Seq(Seq.empty[Int])
    }

    var instructions = Seq.empty[Tuple3[Int,Int,Int]]
    for( line <- 0 until q) {
      val query = sc.nextInt()
      val x = sc.nextInt()
      val y = sc.nextInt()
      instructions = instructions ++ Seq((query,x,y))
    }
    instructions
  }
}
