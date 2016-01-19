Comparing:
- scala Futures with the global Execution 
- scala Futures with the trampoline EC
- scalaz Tasks


Compiling:

    ~benchmarks/jmh:run -i 1 -wi 1 -f1 -t1

Running the perf tests

    benchmarks/jmh:run -t max

