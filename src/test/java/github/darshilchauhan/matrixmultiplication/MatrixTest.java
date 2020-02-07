package github.darshilchauhan.matrixmultiplication;

import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MatrixTest {
    @Test
    @Order(1)
    void nullMatrixTest() {
        double[][] vals = null;
        assertThrows(NullPointerException.class, () -> new Matrix(vals));
    }
}