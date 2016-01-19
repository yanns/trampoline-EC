package benchmarks

import futures.ScalaFuture
import org.openjdk.jmh.annotations.Benchmark
import tasks.ScalazTask

import scala.concurrent.Await
import scala.concurrent.duration._

class AckermannBenchmarks {

  @Benchmark
  def scalaFutures(): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global
    Await.result(new ScalaFuture().ackermann(3, 3), Duration.Inf)
  }

  @Benchmark
  def scalaFuturesTrampoline(): Unit = {
    import concurrent.Execution.Implicits.trampoline
    Await.result(new ScalaFuture().ackermann(3, 3), Duration.Inf)
  }

  @Benchmark
  def scalazTasks(): Unit = {
    new ScalazTask().ackermann(3, 3).unsafePerformSync
  }

}
