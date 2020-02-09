package github.darshilchauhan.matrixmultiplication;

public class Matrix {
    private double[][] vals;
    private int numRows;
    private int numCols;

    /**
     * @param vals
     */
    public Matrix(double[][] vals) {
        this.vals = vals;
        if (vals == null || vals[0] == null) {
            throw new NullPointerException("Matrix values are null");
        }
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
        if (vals == null || vals[0] == null) {
            throw new NullPointerException("Matrix values are null");
        }
        double[][] transposeVals = new double[vals[0].length][vals.length];
        for (int i = 0; i < vals.length; i++) {
            for (int j = 0; j < vals[0].length; j++) {
                transposeVals[j][i] = vals[i][j];
            }
        }
        return new Matrix(transposeVals);
    }

    /**
     * @param row
     */
    private void throwExceptionIfRowOutOfBound(int row) {
        if (row < 0 || row >= this.numRows) {
            throw new IndexOutOfBoundsException("Row index " + row + " out of bound for the matrix");
        }
    }

    /**
     * @param col
     */
    private void throwExceptionIfColOutOfBound(int col) {
        if (col < 0 || col >= this.numCols) {
            throw new IndexOutOfBoundsException("Column index " + col + " out of bound for the matrix");
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
