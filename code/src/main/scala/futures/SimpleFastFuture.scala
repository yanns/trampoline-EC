package futures

import akka.http.scaladsl.util.FastFuture
import akka.http.scaladsl.util.FastFuture._

import scala.concurrent.{ExecutionContext,Future}
import scala.concurrent.ExecutionContext.global
import scala.util.Success

/**
  * Created by matze on 24.03.16.
  */
class SimpleFastFuture {

  def factorial(m: Int)(implicit ec: ExecutionContext): Future[BigInt] =
    m match {
      case 0 ⇒ FastFuture(Success(0))
      case 1 ⇒ FastFuture(Success(1))
      case n ⇒ FastFuture(Success(factorial(n - 1))).fast.flatMap(_.fast.map(_ * n))(global)
    }

  def range(n: Int)(implicit ec: ExecutionContext): Future[Int] =
    (0 to n).foldLeft[Future[Int]](FastFuture(Success(0))) { case (f, _) ⇒ f.fast.map(_ + 1)(global) }


  def ackermann(m: Int, n: Int)(implicit ec: ExecutionContext): Future[Int] = {
    (m, n) match {
      case (0, _) => FastFuture(Success(n + 1))
      case (m, 0) => FastFuture(Success(ackermann(m - 1, 1))).fast.flatMap(identity)(global)
      case (m, n) => for {
        x <- FastFuture(Success(ackermann(m, n - 1)))
        y <- x
        z <- FastFuture(Success(ackermann(m - 1, y)))
        r <- z
      } yield r
    }
  }

}
