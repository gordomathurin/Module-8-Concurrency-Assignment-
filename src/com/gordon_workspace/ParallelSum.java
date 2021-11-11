package com.gordon_workspace;

import java.util.Arrays;

public class ParallelSum extends Thread {

    private int[] randomNumArray;
    private int lowestNum, highestNum, partial;

    public ParallelSum(int[] randomNumArray, int lowestNum, int highestNum) {
        this.randomNumArray = randomNumArray;
        this.lowestNum = lowestNum;
        this.highestNum = Math.min(highestNum, randomNumArray.length);

    }

    public int getPartialSum() {
        return partial;
    }

    public void run() {
        partial = singleSum(randomNumArray, lowestNum, highestNum);
    }

    public static int singleSum(int[] randomNumArray) {
        return singleSum(randomNumArray, 0, randomNumArray.length);
    }

    public static int singleSum(int[] randomNumArray, int low, int high) {
        int total = 0;
        for (int i = low; i < high; i++) {
            total += randomNumArray[i];

        }
        return total;

    }

    public static int parallelSum(int[] arr) {
        return parallelSum(arr, Runtime.getRuntime().availableProcessors());
    }

    public static int parallelSum(int[] randomNumArray, int threads) {
        int size = (int) Math.ceil(randomNumArray.length * 1.0 / threads);
        ParallelSum[] sums = new ParallelSum[threads];
        for (int i = 0; i < threads; i++) {
            sums[i] = new ParallelSum(randomNumArray, i * size, (i + 1) * size);
            sums[i].start();
        }
        try {
            for (ParallelSum sum : sums) {
                sum.join();
            }

        } catch (InterruptedException e) {
        }

        int total = Arrays.stream(sums).mapToInt(ParallelSum::getPartialSum).sum();
        return total;

    }
}

