package work.payne

trait MyLogging {
  def log(msg: Any): Unit = {
    val t = System.nanoTime() % 100000000

    printf("%s: %08d: %s\n",Thread.currentThread().getId, t, msg)
  }
}
