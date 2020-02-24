package github.darshilchauhan.matrixmultiplication;

import java.util.ArrayList;
import java.util.List;

public class Multiplication {
    private final Matrix leftMat;
    private final Matrix rightMat;

    /**
     * Since Matrix does not return columns, taking transpose of rightMatrix. All
     * subsequent calculations will be done accordingly. For example, instead of
     * leftMatrix's row multiplying with rightMatrix's column, it will be
     * rightMatrix's row.
     * 
     * @param leftVals
     * @param rightVals
     */
    Multiplication(double[][] leftVals, double[][] rightVals) {
        this.leftMat = Matrix.getMatrix(leftVals);
        this.rightMat = Matrix.getTransposeMatrix(rightVals);
        if (this.leftMat.getNumCols() != this.rightMat.getNumCols()) {
            throw new IllegalArgumentException("Matrices not compatible for multicplication");
        }
    }

    Multiplication(Matrix leftMat, Matrix rightMat) {
        this.leftMat = leftMat;
        this.rightMat = rightMat;
        if (this.leftMat.getNumCols() != this.rightMat.getNumCols()) {
            throw new IllegalArgumentException("Matrices not compatible for multicplication");
        }
    }

    /**
     * @param numThreads
     * @return Matrix
     * @throws InterruptedException
     */
    Matrix multiplyUsingThreads(int numThreads) {
        Matrix resultMat = new Matrix(this.leftMat.getNumRows(), this.rightMat.getNumRows());
        int startRow = 0;
        for (int i = 0; i < numThreads; i++) {
            int numRows = resultMat.getNumRows() / numThreads;
            if (i < resultMat.getNumRows() % numThreads)
                numRows++;
            int endRow = startRow + numRows;
            MultiplyThread thread = new MultiplyThread(leftMat, rightMat, resultMat, startRow, endRow, i);
            thread.run();
            startRow = endRow;
        }
        return resultMat;
    }

    public class MultiplyThread extends Thread {
        Matrix leftMat;
        Matrix rightMat;
        Matrix resultMat;
        int startRow;
        int endRow;
        int index;

        MultiplyThread(Matrix leftMat, Matrix rightMat, Matrix resultMat, int startRow, int endRow, int index) {
            this.leftMat = leftMat;
            this.rightMat = rightMat;
            this.resultMat = resultMat;
            this.startRow = startRow;
            this.endRow = endRow;
            this.index = index;
        }

        public void run() {
            for (int row = startRow; row < endRow; row++) {
                for (int col = 0; col < resultMat.getNumCols(); col++) {
                    double resultVal = 0;
                    // calculate and set element of resultMat
                    for (int i = 0; i < leftMat.getNumCols(); i++) {
                        resultVal += leftMat.getElement(row, i) * rightMat.getElement(col, i);
                    }
                    resultMat.setElement(row, col, resultVal);
                }
            }
        }
    }
}