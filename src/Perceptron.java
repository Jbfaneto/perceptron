import java.util.Arrays;
import java.util.Random;

/**
 * Perceptron
 * Autor: João Neto
 * Data: 14/05/2024
 */

public class Perceptron {
    public static void main(String[] args) {
        //my ru number
        int ru = 3925398;
        //seed for random number generator
        int seed = 10;
        Random random = new Random(seed);
        //create a PerceptronModel object
        PerceptronModel model = new PerceptronModel(200000, 0.01);

        //generate a sample
        int[] x = Sample.sample(ru);
        //create a label array
        int[] y = new int[x.length];
        //assign labels
        for (int i = 0; i < x.length; i++) {
            y[i] = x[i] >= ru ? 1 : -1;
        }

        //fit the model
        model.fit(x, y);
        //predict the labels
        int[] preds = model.predict(x);

        //calculate the score
        double score = (double) Arrays.stream(preds).filter(pred -> pred == 1).count() / preds.length;
        System.out.println("Score: " + score);
        //print the model details
        System.out.println("Model details:");
        System.out.println("Epochs: " + model.getEpochs());
        System.out.println("Learning rate: " + model.getLearningRate());
        System.out.println("Bias: " + model.getBias());
        System.out.println("Weights: " + Arrays.toString(model.getWeights()));
    }
}