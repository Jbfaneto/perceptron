import java.util.Arrays;
import java.util.Random;

public class PerceptronModel {
    // epochs: number of iterations
    private int epochs;
    // learningRate: the rate at which the model learns
    private double learningRate;
    // bias: the bias term
    private double bias;
    // n: the number of features
    private int n;
    // weights: the weights of the model
    private double[] weights;
    //getters and setters
    public int getEpochs() {
        return epochs;
    }

    public void setEpochs(int epochs) {
        this.epochs = epochs;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    //constructor
    public PerceptronModel(int epochs, double learningRate) {
        this.epochs = epochs;
        this.learningRate = learningRate;
        this.bias = 0.5;
    }

    //activator function
    private int activator(double x) {
        return x >= 0 ? 1 : -1;
    }

    //transformation function
    private int[] transformation(int x) {
        return String.valueOf(x).chars().map(Character::getNumericValue).toArray();
    }

    //fit method
    public void fit(int[] x, int[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("The size of sample is not the same!");
        }


        //transform the sample
        int[][] xAdjusted = Arrays.stream(x).mapToObj(this::transformation).toArray(int[][]::new);
        this.n = xAdjusted[0].length;
        this.weights = new Random().doubles(this.n).toArray();

        //fit the model
        for (int i = 0; i < this.epochs; i++) {
            double mse = 0;
            for (int j = 0; j < xAdjusted.length; j++) {
                double net = dotProduct(xAdjusted[j], this.weights) + this.bias;
                int prediction = activator(net);
                int error = y[j] - prediction;

                //update the weights and bias
                for (int k = 0; k < this.weights.length; k++) {
                    this.weights[k] += this.learningRate * error * xAdjusted[j][k];
                }
                this.bias += this.learningRate * error;

                mse += Math.pow(error, 2);
            }

            //if the model is good to predict
            if (mse == 0) {
                System.out.println("The model is good to predict!");
                this.epochs = i;
                return;
            }
            mse = 0;
        }
    }

    //predict method
    public int[] predict(int[] x) {
        int[][] xAdjusted = Arrays.stream(x).mapToObj(this::transformation).toArray(int[][]::new);
        double[] net = new double[xAdjusted.length];
        for (int i = 0; i < xAdjusted.length; i++) {
            net[i] = dotProduct(xAdjusted[i], this.weights) + this.bias;
        }

        return Arrays.stream(net).mapToInt(this::activator).toArray();
    }

    //dot product method
    private double dotProduct(int[] a, double[] b) {
        //check if the size of the arrays is the same
        double sum = 0;
        //calculate the dot product
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }
}
