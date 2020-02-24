package github.darshilchauhan.matrixmultiplication;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class MultiplicationTest {
    static double[][] leftVals;
    static double[][] rightVals;

    @BeforeAll
    static void createMatrices() {
        leftVals = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        rightVals = new double[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } };
    }

    @Test
    @Order(1)
    void illegalArgumentsTest() {
        assertThrows("Null left matrix", IllegalArgumentException.class, () -> new Multiplication(null, rightVals));
        assertThrows("Null right matrix", IllegalArgumentException.class, () -> new Multiplication(leftVals, null));
        assertThrows("Incompatible matrices", IllegalArgumentException.class,
                () -> new Multiplication(leftVals, leftVals));
    }

    @Test
    void sampleMultiplyTest() {
        Multiplication mult = new Multiplication(leftVals, rightVals);
        Matrix resultMat = mult.multiplyUsingThreads(2);
        double[][] resultVals = resultMat.get2DArray();
        double[][] expectedVals = new double[][] { { 22, 28 }, { 49, 64 } };
        assertArrayEquals(expectedVals, resultVals, "Multiplication gives unexpected values as result");
    }
}