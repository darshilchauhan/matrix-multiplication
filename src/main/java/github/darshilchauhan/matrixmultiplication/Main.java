package github.darshilchauhan.matrixmultiplication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException, FileNotFoundException, InterruptedException {
        String folder = "resources/bigMatrices/";

        System.out.println("Reading first matrix from file");
        Matrix leftMat = Matrix.readFromFile(folder + "leftMatrix.csv");
        System.out.println("Reading second matrix from file");
        Matrix rightMat = Matrix.getTransposeMatrix(Matrix.readFromFile(folder + "rightMatrix.csv"));
        Multiplication mult = new Multiplication(leftMat, rightMat);
        // Matrix resultMat = Matrix.readFromFile(folder + "resultMatrix.csv");

        System.out.println("Now multiplying with different number of threads...");
        for (int numThreads = 1; numThreads <= 8; numThreads++) {
            long startTime = System.nanoTime();
            Matrix res = mult.multiplyUsingThreads(numThreads);
            System.out.println("Using " + numThreads + " thread(s): " + (System.nanoTime() - startTime) / 1000_000_000.0
                    + " seconds");
        }
    }

    /**
     * For much smaller matrices, uncomment only for testing
     * 
     * @param args
     * @throws IOException
     * @throws FileNotFoundException
     * @throws InterruptedException
     */
    // public static void main2(String[] args) throws IOException,
    // FileNotFoundException, InterruptedException {
    // int leftMatRows = 20;
    // int leftMatCols = 50;
    // int rightMatRows = leftMatCols;
    // int rightMatCols = 10;
    // double[][] bigLeftVals = new double[leftMatRows][leftMatCols];
    // double[][] bigRightVals = new double[rightMatRows][rightMatCols];
    // Random rand = new Random();
    // for (int i = 0; i < leftMatRows; i++) {
    // for (int j = 0; j < leftMatCols; j++) {
    // bigLeftVals[i][j] = rand.nextDouble();
    // }
    // }
    // for (int i = 0; i < rightMatRows; i++) {
    // for (int j = 0; j < rightMatCols; j++) {
    // bigRightVals[i][j] = rand.nextDouble();
    // }
    // }
    // Matrix bigLeftMat = Matrix.getMatrix(bigLeftVals);
    // Matrix bigRightMat = Matrix.getMatrix(bigRightVals);
    // Multiplication mult = new Multiplication(bigLeftVals, bigRightVals);

    // for (int numThreads = 4; numThreads <= 4; numThreads++) {
    // long startTime = System.nanoTime();
    // Matrix res = mult.multiplyUsingThreads(numThreads);
    // System.out.println("Using " + numThreads + " thread(s): " +
    // (System.nanoTime() - startTime));
    // }
    // }
}