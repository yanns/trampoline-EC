package futures

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.ExecutionContext.global

class ScalaFuture {

  def factorial(m: Int)(implicit ec: ExecutionContext): Future[BigInt] =
    m match {
      case 0 ⇒ Future.successful(0)
      case 1 ⇒ Future.successful(1)
      case n ⇒ Future(factorial(n - 1))(global) flatMap (_.map(_ * n))
    }

  def range(n: Int)(implicit ec: ExecutionContext): Future[Int] =
    (0 to n).foldLeft[Future[Int]](Future.successful(0)) { case (f, _) ⇒ f.map(_ + 1) }


  def ackermann(m: Int, n: Int)(implicit ec: ExecutionContext): Future[Int] = {
    (m, n) match {
      case (0, _) => Future.successful(n + 1)
      case (m, 0) => Future(ackermann(m - 1, 1))(global).flatMap(identity)
      case (m, n) => for {
        x <- Future(ackermann(m, n - 1))(global)
        y <- x
        z <- Future(ackermann(m - 1, y))(global)
        r <- z
      } yield r
    }
  }
}
