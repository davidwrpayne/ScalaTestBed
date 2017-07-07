package work.payne.challenges

import work.payne.MyLogging

case class ProductOfOtherNumbersQuestion() extends CodingProblem with MyLogging {


  override def run(): Unit = {
    val input = Seq(1,2,3,4,5)
    val expected = Seq(120,60,100,30,24)

    assert(productOfOthers(input).equals(expected))
  }

  def productOfOthers(in: Seq[Int]): Seq[Int] = {

    Seq.empty[Int]
  }

}
