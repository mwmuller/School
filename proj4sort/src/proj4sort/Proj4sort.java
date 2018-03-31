/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj4sort;

import java.util.*;
import java.io.*;
import static proj4sort.HeapSort.printArray;

/**
 *
 * @author Michael Muller
 */
public class Proj4sort {

    static int[] testN = {1000, 10000, 100000};
    static Random rand = new Random();
    static double[] timesMerge = new double[15];
    static double[] timesHeap = new double[15];
    static double[] timesQuick = new double[15];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        MergeSort ms = new MergeSort();
        HeapSort hs = new HeapSort();
        QuickSort qs = new QuickSort();
        int[] arr = null;
        Scanner in = new Scanner(System.in);
        System.out.println("What array size would you like?\n"
                + "1) 1000\n"
                + "2) 10,000\n"
                + "3) 100,000\n"
                + "4) End");
        int selection = Integer.parseInt(in.nextLine());
        if (selection <= 3) {
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 5; i++) {
                    arr = generateArr(testN[j]);
                    double start = System.nanoTime();
                    ms.sort(arr, 0, arr.length - 1);
                    timesMerge[i + (j * 5)] = (System.nanoTime() - start) / 100000;
                    // ms.printArray(arr);
                    arr = generateArr(testN[j]);
                    start = System.nanoTime();
                    hs.sort(arr);
                    timesHeap[i + (j * 5)] = (System.nanoTime() - start) / 100000;
                    //  hs.printArray(arr);
                    arr = generateArr(testN[j]);
                    start = System.nanoTime();
                    qs.sort(arr, 0, arr.length - 1);
                    timesQuick[i + (j * 5)] = (System.nanoTime() - start) / 100000;
                    // qs.printArray(arr);
                }
            }
        } else {
            System.exit(-1);
        }
        printTimes();
    }

    public static int[] generateArr(int N) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = rand.nextInt(N);
        }
        return arr;
    }

    public static void printTimes() {
        // merge times
        System.out.println("MergeSort Times:");
        for (int i = 0; i < 15; i++) {
            if (i == 0) {
                System.out.println("Times for N = 1000:");
            } else if (i == 4) {
                System.out.println("Times for N = 10,000:");
            } else if (i == 9) {
                System.out.println("Times for N = 100,000:");
            }
            System.out.println(timesMerge[i] + "ms");
        }
        //heap times
        System.out.println("HeapSort Times:");
        for (int i = 0; i < 15; i++) {
            if (i == 0) {
                System.out.println("Times for N = 1000:");
            } else if (i == 4) {
                System.out.println("Times for N = 10,000:");
            } else if (i == 9) {
                System.out.println("Times for N = 100,000:");
            }
            System.out.println(timesHeap[i] + "ms");
        }
        //quick times
        System.out.println("QuickSort Times:");
        for (int i = 0; i < 15; i++) {
            if (i == 0) {
                System.out.println("Times for N = 1000:");
            } else if (i == 4) {
                System.out.println("Times for N = 10,000:");
            } else if (i == 9) {
                System.out.println("Times for N = 100,000:");
            }
            System.out.println(timesQuick[i] + "ms");
        }
    }
}
