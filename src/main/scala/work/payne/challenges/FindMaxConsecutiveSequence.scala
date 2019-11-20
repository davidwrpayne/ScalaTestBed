package work.payne.challenges

import scala.collection.mutable

/**
 * From interview.io problem
 * Given list of integers L find maximum length of consecutive numbers
 * { 99, 100, 4,3,1,5,2 } => max length ( 99.100 , 1.5 )
 *
 *
 * first thoughts here is you could sort the list then starting at first element travel until you found the
 * missing element and record the max length you were able to find and keep track of which group you had
 *
 *
 * second thoughts are: (granted with a bit more listening to interview)
 * The important bits are that you know they are a list of integers and integers have the property of
 * being known distances between each other
 * which means given a single integer you can find another in O(1)
 * Which means as long as you can look up whether you've seen an integer then you can determine they were in your set
 *
 * thought was iterate over input hashing elements as group members looking 1 left and 1 right for close groups
 * in the hashTable of groups. merging when you found them. the problem is this is pretty complex
 */
class FindMaxConsecutiveSequence extends CodingProblem {

   def findMaxConsecSeq(input: Seq[Int]): Seq[Int] = {
     val seenHash = new mutable.HashSet[Int]()
     input.foreach( i => seenHash.+=(i) )
     var inGroupHash = new mutable.HashSet[Int]()

     var largestGroup: Option[(Int,Int)] = None
     var currentGroup: Option[(Int,Int)] = Some((input.head,input.head))

     input.foreach( i =>
       if(!inGroupHash.apply(i)) {
         inGroupHash.+=(i)
         // if last group was larger then largest keep it.
         (currentGroup, largestGroup) match {
           case (Some((min,max)),Some((lastMin,lastMax))) if (lastMax-lastMin) < (max - min) => largestGroup = currentGroup
           case (Some((min,max)), None) => largestGroup = currentGroup
           case _ => ()
         }
          currentGroup =  Some((i,i))

          // look left
          var j = i
          while(seenHash(j-1)) {
            inGroupHash.+=(j-1)
            currentGroup = Some((j-1,currentGroup.get._2))
            j = j - 1
          }
         j = i
          while(seenHash(j+1)) {
            inGroupHash.+=(j+1)
            currentGroup = Some((currentGroup.get._1, j+1))
            j = j + 1
          }
       }
     )
     (currentGroup, largestGroup) match {
       case (Some((min,max)),Some((lastMin,lastMax))) if (lastMax-lastMin) < (max - min) => largestGroup = currentGroup
       case (Some((min,max)), None) => largestGroup = currentGroup
       case _ => ()
     }
     Seq(largestGroup.get._1,largestGroup.get._2)
  }


  override def run(): Unit = {
    println(findMaxConsecSeq(Seq(99,100,1,2,3,4,5)))
  }
}
