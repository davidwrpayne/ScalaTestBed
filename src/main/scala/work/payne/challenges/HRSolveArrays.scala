package work.payne.challenges

import work.payne.testScripts.TestScript

/**
  * This is to solve a HackerRank problem
  * compute hour glass. its a Solve arrays problem
  */
class HRSolveArrays extends CodingProblem {

  override def run(): Unit = {
    val sc = new java.util.Scanner (System.in);
    var arr = Array.ofDim[Int](6,6)
    for(arr_i <- 0 to 6-1) {
      for(arr_j <- 0 to 6-1){
        arr(arr_i)(arr_j) = sc.nextInt()
      }
    }

    def getLargest(arr: Array[Array[Int]]): Int = {
      arr.flatten.foldLeft(Int.MinValue)((largest,value) =>
        if(largest > value) largest else value
      )
    }
    val outArr = solveArr(arr)
    println(getLargest(outArr))
  }


  def print2DArray[T](arr: Array[Array[T]]) = {

    for( arr_i <- arr.indices) {
      for (arr_j <- arr(arr_i).indices) {
        print(s"${arr(arr_i)(arr_j)} ")
      }
      print("\n")
    }

  }

  def validIndexLocation(i: Int, j: Int): Boolean = {
    if( 1 <= i && i <= 4 &&  1 <= j && j <= 4 ) {
      true
    } else
      false
  }

  def computeHourGlass(arr: Array[Array[Int]], i: Int, j: Int): Int = {
    val locations = Seq(
      (i-1,j-1),(i-1,j),(i-1,j+1),
                (i,j),
      (i+1,j-1),(i+1,j),(i+1,j+1)
    )
    val values = locations.map({ case (i: Int, j: Int) => arr(i)(j)})
    val sum = values.foldLeft(0)( (acc,value) => acc + value)
    sum
  }

  def solveArr(arr: Array[Array[Int]]): Array[Array[Int]] = {
    var newArr = Array.ofDim[Int](4,4)

    for( i <- arr.indices) {
      for ( j <- arr(i).indices) {
        if(validIndexLocation(i,j)) {
          newArr(i-1)(j-1) = computeHourGlass(arr,i,j)
        }
      }
    }
    newArr
  }

}
