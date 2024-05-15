public class Sample {

    public static int[] sample(int ru) {
        //generate a sample
        int[] sample = new int[]{
                ru - 1000000, ru + 1000000, ru - 100000,
                ru + 100000, ru - 10000, ru + 10000,
                ru - 1000, ru + 1000, ru - 100, ru + 100,
                ru - 10, ru + 10, ru - 2, ru - 1, ru + 2,
                ru + 1, ru
        };
        return sample;
    }

}
