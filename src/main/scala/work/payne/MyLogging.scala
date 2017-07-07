package work.payne

trait MyLogging {
  def log(msg: Any): Unit = {
    println(s"${Thread.currentThread().getId}: $msg")
  }
}
