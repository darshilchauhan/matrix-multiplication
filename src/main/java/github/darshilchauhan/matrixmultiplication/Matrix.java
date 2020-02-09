package github.darshilchauhan.matrixmultiplication;

public class Matrix {
    private double[][] vals;
    private int numRows;
    private int numCols;

    /**
     * @param vals
     */
    public Matrix(double[][] vals) {
        throwExceptionIfHasNull(vals);
        for (int i = 1; i < vals.length; i++) {
            if (vals[i].length != vals[0].length) {
                throw new IllegalArgumentException("Row lengths are not same for matrix input");
            }
        }
        this.vals = vals;
        this.numRows = vals.length;
        this.numCols = vals[0].length;
    }

    /**
     * Static factory for generating transpose matrix.
     * 
     * @param vals
     * @return Matrix
     */
    public static Matrix getTransposeMatrix(double[][] vals) {
        throwExceptionIfHasNull(vals);
        double[][] transposeVals = new double[vals[0].length][vals.length];
        for (int i = 0; i < vals.length; i++) {
            for (int j = 0; j < vals[0].length; j++) {
                transposeVals[j][i] = vals[i][j];
            }
        }
        return new Matrix(transposeVals);
    }

    /**
     * Throws exception if matrix itself, or any row is null. Intended to be used
     * only during creation of the object (including creation by static factories),
     * therefore it is static.
     * 
     * @param vals
     */
    private static void throwExceptionIfHasNull(double[][] vals) {
        if (vals == null)
            throw new IllegalArgumentException("Matrix input is null");
        for (int i = 0; i < vals.length; i++) {
            if (vals[i] == null)
                throw new IllegalArgumentException("Matrix input has " + i + "th row to be null");
        }
    }

    /**
     * @param row
     */
    private void throwExceptionIfRowOutOfBound(int row) {
        if (row < 0 || row >= this.numRows) {
            throw new ArrayIndexOutOfBoundsException("Row index " + row + " out of bound for the matrix");
        }
    }

    /**
     * @param col
     */
    private void throwExceptionIfColOutOfBound(int col) {
        if (col < 0 || col >= this.numCols) {
            throw new ArrayIndexOutOfBoundsException("Column index " + col + " out of bound for the matrix");
        }
    }

    /**
     * @param row
     * @param col
     * @return double
     */
    public double getElement(int row, int col) {
        throwExceptionIfRowOutOfBound(row);
        throwExceptionIfColOutOfBound(col);
        return this.vals[row][col];
    }

    /**
     * @param row
     * @param col
     * @param val
     */
    public void setElement(int row, int col, double val) {
        throwExceptionIfRowOutOfBound(row);
        throwExceptionIfColOutOfBound(col);
        this.vals[row][col] = val;
    }

    /**
     * @param row
     * @return double[]
     */
    public double[] getRow(int row) {
        throwExceptionIfRowOutOfBound(row);
        return this.vals[row];
    }

    /**
     * @return double[][]
     */
    public double[][] get2DArray() {
        return this.vals;
    }
}
