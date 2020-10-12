package com.company;
import java.lang.management.*;
import java.lang.management.ManagementFactory;
import java.util.Collections;
import java.util.Random;
import java.util.Arrays;
import java.lang.String;


public class Main {

    public static void main(String[] args) {
        long startTime;
        long endTime;
        int numTests = 90;
        int tooLong = 10000000;



        // Table Header information.
        String emptyString = "";
        String header = String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s",emptyString,"fibRecur",emptyString,emptyString,"fibCache",emptyString,emptyString,"fibLoop",emptyString,emptyString,"fibMatrix",emptyString,emptyString);
        String headerTwo = String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s","N","Time","Doubling Ratio","Expected Doubling","Time","Doubling Ratio","Expected Doubling","Time","Doubling Ratio","Expected Doubling","Time","Doubling Ratio","Expected Doubling");

        System.out.println(header);
        System.out.println(headerTwo);


        for(int n = 0; n < numTests; n++){
            long previousTime = 1;

            // Formatting.
            if(previousTime >= tooLong) {
                System.out.format("%-20s", n);
                startTime = getCpuTime();
                fibRecur(n);
                endTime = getCpuTime();
                System.out.format("%-20s", endTime - startTime);
                System.out.format("%-20s", ((endTime - startTime) / previousTime));
                previousTime = endTime - startTime;
                System.out.format("%-20s", (Math.pow(n, 3) / Math.pow(n / 2, 3)));
            }
        }

        for(int n = 0; n < numTests; n++){
            long previousTime = 1;

            // Formatting.
            if(previousTime >= tooLong) {
                System.out.format("%-20s", n);
                startTime = getCpuTime();
                fibCache(n);
                endTime = getCpuTime();
                System.out.format("%-20s", endTime - startTime);
                System.out.format("%-20s", ((endTime - startTime) / previousTime));
                previousTime = endTime - startTime;
                System.out.format("%-20s", (Math.pow(n, 3) / Math.pow(n / 2, 3)));
            }
        }

        for(int n = 0; n < numTests; n++){
            long previousTime = 1;

            // Formatting.
            if(previousTime >= tooLong) {
                System.out.format("%-20s", n);
                startTime = getCpuTime();
                fibLoop(n);
                endTime = getCpuTime();
                System.out.format("%-20s", endTime - startTime);
                System.out.format("%-20s", ((endTime - startTime) / previousTime));
                previousTime = endTime - startTime;
                System.out.format("%-20s", (Math.pow(n, 3) / Math.pow(n / 2, 3)));
            }
        }

        for(int n = 0; n < numTests; n++){
            long previousTime = 1;

            // Formatting.
            if(previousTime >= tooLong) {
                System.out.format("%-20s", n);
                startTime = getCpuTime();
                fibMatrix(n);
                endTime = getCpuTime();
                System.out.format("%-20s", endTime - startTime);
                System.out.format("%-20s", ((endTime - startTime) / previousTime));
                previousTime = endTime - startTime;
                System.out.format("%-20s", (Math.pow(n, 3) / Math.pow(n / 2, 3)));
            }
        }
    }

    public static long getCpuTime(){
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ?
                bean.getCurrentThreadCpuTime() : 0L;
    }

    public static long fibRecur(long n){
        if(n == 0 || n == 1){
            return n;
        }
        else{
            return fibRecur(n - 1) + fibRecur(n - 2);
        }
    }

    public static long fibCache(long n){
        long cache[] = new long[100];
        cache[0] = 0;
        cache[1] = 1;

        for(int x = 2; x < 100; x++) {
            cache[x] = 0;
        }

        class fibHelp {
            public long fibCacheHelper(long n){

                // known solutions to the fib sequence
                if(n == 0 || n == 1){
                    return n;
                }
                // Found it in the table.
                else if(cache[(int)n] != 0){
                    return cache[(int)n];
                }
                // Add to the table.
                else {
                    cache[(int)n] = fibCacheHelper(n - 1) + fibCacheHelper(n - 2);
                    return cache[(int)n];
                }
            }
        }
        fibHelp helper = new fibHelp();
        return helper.fibCacheHelper(n);
    }



    public static long fibLoop(long n){
        long a = 0;
        long b = 1;
        long next;

        if(n < 2)
            return n;

        for(long i = 2; i <= n; i++){
            next = a + b;
            a = b;
            b = next;
        }
        return b;
    }

    public static long fibMatrix(long n){
        int MATRIX_SIZE = 2;
        long matrix[][] = new long[MATRIX_SIZE][MATRIX_SIZE];
        long matrixTwo[][] = new long[MATRIX_SIZE][MATRIX_SIZE];
        long result[][] = new long[MATRIX_SIZE][MATRIX_SIZE];

        matrix[0][0] = 1;
        matrix[0][1] = 1;
        matrix[1][1] = 1;
        matrix[1][0] = 0;

        matrixTwo[0][0] = 1;
        matrixTwo[0][1] = 1;
        matrixTwo[1][1] = 1;
        matrixTwo[1][0] = 0;

        if(n == 0) {
            result = matrix;
        }else if(n == 1) {
            result = matrixTwo;
        }
        // Finds the next fibinaci number
        for(int x = 0; x < n - 2; x++){
            result[0][0] = matrix[0][0] * matrix[0][1] + matrixTwo[0][0] * matrixTwo[1][0];
            result[0][1] = matrix[0][0] * matrix[0][1] + matrixTwo[0][1] * matrixTwo[1][1];
            result[1][0] = matrix[1][0] * matrix[1][1] + matrixTwo[0][0] * matrixTwo[1][0];
            result[1][1] = matrix[1][0] * matrix[1][1] + matrixTwo[0][1] * matrixTwo[1][1];

            // Reset the first matrix to the new result.
            matrix = result;
        }
        return result[0][0];
    }
    public static long intPowerBrute(long x, long y){
        long product = 1;

        for(long i = 0; i < y; i++){
            product = product * x;
        }
        return product;
    }
    /*
    public static long justSmart() {
        long result = 0;

        // Not any faster without storage. I presume.
        // Cycle through the remiaining bits.
        for (n > 0; n = n / 2) {
            // If the bit is one.
            if (n % 2 != 0) {
                result += fibRecur(n);
            }
        }
        */
}

