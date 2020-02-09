package github.darshilchauhan.matrixmultiplication;

public class Matrix {
    private double[][] vals;
    private final int numRows;
    private final int numCols;

    /**
     * Public constructor, initialized to zero values.
     * 
     * @param numRows
     * @param numCols
     */
    public Matrix(int numRows, int numCols) {
        vals = new double[numRows][numCols];
        this.numRows = numRows;
        this.numCols = numCols;
    }

    /**
     * Constructor from given values. Private because it was difficult to manage
     * when to deep copy the data (while maintaining encapsulation) when both
     * constructor and static factory (for transpose) were part of API. Expects
     * input to be valid input for matrix. Use
     * {@link #throwExceptionIfHasNull(double[][]) throwExceptionIfHasNull} to
     * validate.
     * 
     * @param inputVals
     */
    private Matrix(double[][] inputVals) {
        for (int i = 1; i < inputVals.length; i++) {
            if (inputVals[i].length != inputVals[0].length) {
                throw new IllegalArgumentException("Row lengths are not same for matrix input");
            }
        }
        this.numRows = inputVals.length;
        this.numCols = inputVals[0].length;
        this.vals = inputVals;

    }

    public static Matrix getMatrix(double[][] inputVals) {
        throwExceptionIfHasNull(inputVals);
        double[][] copiedVals = new double[inputVals.length][inputVals[0].length];
        for (int i = 0; i < inputVals.length; i++) {
            for (int j = 0; j < inputVals[0].length; j++) {
                copiedVals[i][j] = inputVals[i][j];
            }
        }
        return new Matrix(copiedVals);
    }

    /**
     * Static factory for generating transpose matrix. Caveat: transposed 2D array
     * 'vals' goes to constructor and gets deep copied, which is a waste of memory.
     * Not sure how to avoid that without making bad design choice.
     * 
     * @param inputVals
     * @return Matrix
     */
    public static Matrix getTransposeMatrix(double[][] inputVals) {
        throwExceptionIfHasNull(inputVals);
        double[][] transposeVals = new double[inputVals[0].length][inputVals.length];
        for (int i = 0; i < inputVals.length; i++) {
            for (int j = 0; j < inputVals[0].length; j++) {
                transposeVals[j][i] = inputVals[i][j];
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
        if (vals.length == 0)
            throw new IllegalArgumentException("Matrix input is of size 0");
        int arrayLength = 0;
        for (int i = 0; i < vals.length; i++) {
            if (vals[i] == null)
                throw new IllegalArgumentException(i + "th array in matrix input is null");
            if (vals[i].length == 0)
                throw new IllegalArgumentException(i + "th array in matrix input has 0 length");
            if (i == 0) {
                arrayLength = vals[i].length;
            } else if (vals[i].length != arrayLength) {
                throw new IllegalArgumentException(
                        "Length mismatch in matrix input: " + i + "th array with 0th array.");
            }
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

    // /**
    // * @param row
    // * @return double[]
    // */
    // public double[] getRow(int row) {
    // throwExceptionIfRowOutOfBound(row);
    // return this.vals[row];
    // }

    /**
     * Package private because only to be used for testing. Not part of API.
     * 
     * @return double[][]
     */
    double[][] get2DArray() {
        return this.vals;
    }

    /**
     * @return int
     */
    public int getNumRows() {
        return this.numRows;
    }

    /**
     * @return int
     */
    public int getNumCols() {
        return this.numCols;
    }
}
