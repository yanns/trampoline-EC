package benchmarks

import futures.{ScalaFuture, SimpleFastFuture}
import org.openjdk.jmh.annotations.Benchmark
import tasks.ScalazTask

import scala.concurrent.Await
import scala.concurrent.duration._


class RangeBenchmarks {

  @Benchmark
  def scalaFutures(): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global
    Await.result(new ScalaFuture().range(5000000), Duration.Inf)
  }

  @Benchmark
  def scalaFuturesTrampoline(): Unit = {
    import concurrent.Execution.Implicits.trampoline
    Await.result(new ScalaFuture().range(5000000), Duration.Inf)
  }

  @Benchmark
  def fastFuture(): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global
    Await.result(new SimpleFastFuture().range(5000000), Duration.Inf)
  }

}
