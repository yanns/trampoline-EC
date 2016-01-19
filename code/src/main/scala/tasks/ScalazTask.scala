package tasks

class ScalazTask {

  import scalaz.concurrent.Task, Task._

  def factorial(m: Int): Task[BigInt] =
    m match {
      case 0 ⇒ now(0)
      case 1 ⇒ now(1)
      case n ⇒ suspend(factorial(n - 1)) map (_ * n)
    }

  def ackermann(m: Int, n: Int): Task[Int] = {
    (m, n) match {
      case (0, _) => now(n + 1)
      case (m, 0) => suspend(ackermann(m - 1, 1))
      case (m, n) =>
        suspend(ackermann(m, n - 1)).flatMap { x =>
          suspend(ackermann(m - 1, x)) }
    }
  }
}
