Matrix multiplication using multithreading in Java.

Goal:

1. Analyze the reduction in multiplication time of two large matrices by using multithreading.
2. Put proper unit tests in place and create an API-like structure (remove this goal once done)

Structure:
Rows will be divided evenly among all threads. Each thread will create their own matrix, processing one row at a time. In the end they will access a shared matrix and add their matrix's value in it. This last step will require "synchronized".
