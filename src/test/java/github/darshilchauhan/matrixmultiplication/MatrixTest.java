package github.darshilchauhan.matrixmultiplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MatrixTest {
    private static final double[][] vals = { { 1.1, 3, 5 }, { 2, 4, 6 } };
    private static final Matrix mat = Matrix.getMatrix(vals);

    @Test
    @Order(1)
    void illegalArgumentsTest() {
        assertThrows("Null matrix test", IllegalArgumentException.class, () -> Matrix.getMatrix(null));
        assertThrows("Null row test", IllegalArgumentException.class, () -> Matrix.getMatrix(new double[][] { null }));
        assertThrows("Zero length row test", IllegalArgumentException.class, () -> Matrix.getMatrix(new double[0][]));
        assertThrows("Unequal row lengths", IllegalArgumentException.class,
                () -> Matrix.getMatrix(new double[][] { { 1, 2, 3 }, { 4, 5 } }));
    }

    @Test
    void getNumRowsColsTest() {
        assertEquals(2, mat.getNumRows());
        assertEquals(3, mat.getNumCols());
    }

    @Test
    void getElementTest() {
        assertEquals(1.1, mat.getElement(0, 0), 0.0001);
        assertEquals(4, mat.getElement(1, 1), 0.0001);
        assertThrows(IndexOutOfBoundsException.class, () -> mat.getElement(2, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> mat.getElement(0, 3));
    }

    @Test
    void zeroMatrixConstructorTest() {
        Matrix newMat = new Matrix(3, 4);
        for (int i = 0; i < newMat.getNumRows(); i++) {
            for (int j = 0; j < newMat.getNumCols(); j++) {
                assertEquals(0, newMat.getElement(i, j), 0.0001);
            }
        }
    }

    // @Test
    // void getRowTest() {
    // assertArrayEquals(vals[0], mat.getRow(0), 0.0001);
    // assertArrayEquals(vals[1], mat.getRow(1), 0.0001);
    // assertThrows(IndexOutOfBoundsException.class, () -> mat.getRow(2));
    // }

    @Test
    void get2DArrayTest() {
        assertArrayEquals(vals, mat.get2DArray());
    }

    @Test
    void getTransposeTest() {
        Matrix transposeMat = Matrix.getTransposeMatrix(vals);
        for (int i = 0; i < vals.length; i++) {
            for (int j = 0; j < vals[0].length; j++) {
                assertEquals(mat.getElement(i, j), transposeMat.getElement(j, i), 0.0001);
            }
        }
        assertThrows(IndexOutOfBoundsException.class, () -> transposeMat.getElement(3, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> transposeMat.getElement(0, 2));
        assertNotEquals(vals, transposeMat.get2DArray());
    }

    @Test
    void setElementTest() {
        Matrix newMat = Matrix.getMatrix(new double[][] { { -2.5 } });
        newMat.setElement(0, 0, -1);
        assertEquals(-1, newMat.getElement(0, 0), 0.0001);
    }

}