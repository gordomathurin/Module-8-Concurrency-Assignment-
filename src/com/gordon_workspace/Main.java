package com.gordon_workspace;
import java.util.Random;

public class Main {


    public static void main(String[] args)  {

                Random random = new Random();

                int[] randomNumArray = new int[200000000];

                for (int i = 0; i < randomNumArray.length; i++) {
                    randomNumArray[i] = random.nextInt(10) + 1;
                }
                long start = System.currentTimeMillis();
                System.out.println("Sum Of values " + ParallelSum.singleSum(randomNumArray));
                System.out.println("Single Sum Time: " + (System.currentTimeMillis() - start));
                start = System.currentTimeMillis();
                System.out.println("Sum Of values " + ParallelSum.parallelSum(randomNumArray));
                System.out.println("Single Thread Sum Time: " + (System.currentTimeMillis() - start));


        }
    }

