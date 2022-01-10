Matrix multiplication using multithreading in Java.

Goal: Analyze the reduction in multiplication time of two large matrices by using multithreading.

Execution command:

```bash
mvn exec:java -Dexec.mainClass="github.darshilchauhan.matrixmultiplication.Main"
```

Structure:
Rows will be divided evenly among all threads. Each thread will access a shared matrix and add their rows' values in it.

Todo: Update the result, my code is not actually multi-threading.
Result: No difference because threads are getting executed one after the other. This is because each thread is doing CPU-heavy work, so JVM lets each thread run completely before going to another thread. Having multiple cores and assigning each thread to it will yield more insightful results.
