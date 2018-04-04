/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortingballs;

import java.util.*;
import org.omg.CORBA.Environment;

public class SortingBalls {

    /**
     * @param args the command line arguments
     */
    private int colors;
    private static int[] testN = {1000000};
    private static Random rand = new Random();

    public static void main(String[] args) {
        rand.setSeed(50);
        Scanner in = new Scanner(System.in);
        System.out.println("Select a preset for testing:\n"
                + "1) 2 Colors\n"
                + "2) 3 Colors\n"
                + "3) Custom Number\n"
                + "4) End");
        String selection = in.nextLine();
        switch (Integer.parseInt(selection)) {
            case 1:
                twoColors();
                break;
            case 2:
                threeColors();
                break;
            case 3:
                anyColors();
                break;
            case 4:
                System.exit(-1);
                break;
        }

    }

    public static int[] OnQuicksortAny(int[] balls, int max, int endIndex) {
        int countmin = 0, countmax = 0;
        int leftVal, rightVal, pivotVal;
        int end = endIndex, start = 0;
        for (int i = 0; i < end; i++) { // This for loops counts the number of the balls.
            if (balls[i] < max) {       // We consider the max value as one set of balls, 
                countmin++;             // and all other colors are grouped together
            } else if (balls[i] == max) {// This will always run at O(n) because we iterate through
                countmax++;             // Each element in the balls array.
            }
        }
        end = end - 1;
        int pivot = end - countmax + 1; // Makes sure that the spots to the right of the pivit will hold the number of 1's
        while (true) {
            leftVal = balls[start];
            rightVal = balls[end];
            pivotVal = balls[pivot];
            if (start == pivot) {
                if (pivotVal == max && leftVal != max && rightVal == max) { // Checks if pivot needs to be swapped
                    balls[end] = pivotVal;
                    balls[pivot] = rightVal;
                    break;
                } else if (leftVal == max) {
                    break;
                }
            } else if (end == pivot) {
                if (pivotVal != max && rightVal != max && leftVal == max) { // checks if pivot needs to be swapped
                    balls[start] = pivotVal;
                    balls[pivot] = leftVal;
                    break;
                } else if (rightVal == max) {
                    break;
                }
            }
            if (leftVal != max) {
                start++;
            }
            if (rightVal == max) {
                end--;
            }
            if (leftVal > rightVal && leftVal == max) { // swaps the values
                int temp = leftVal;
                balls[start] = rightVal;
                balls[end] = temp;
            }
        }
        if (max == 1) {
            return balls;
        } else {
            return OnQuicksortAny(balls, max - 1, pivot);
        }
    }

    public static int[] generateArray2(int N) {
        int[] ballArr = new int[N];
        for (int i = 0; i < N - 1; i++) {
            ballArr[i] = rand.nextInt(2);
        }

        return ballArr; // place holder
    }

    public static void twoColors() {
        long start = System.nanoTime();
        for (int i = 0; i < testN.length; i++) {
            int[] genArr = generateArray2(testN[i]);
            printArr(OnQuicksortAny(genArr, 1, genArr.length));
            System.out.println("\n");
        }
        System.out.println("Run Rime: " + (System.nanoTime() - start) / 1000000 + " ns");
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i % 40 == 0) {
                System.out.println("\n");
            }
            System.out.print(arr[i] + ", ");
        }
    }

    public static int[] generateArray3(int N) {
        int[] ballArr = new int[N];
        for (int i = 0; i < N - 1; i++) {
            ballArr[i] = rand.nextInt(3);
        }
        return ballArr;
    }

    public static int[] generateArrayAny(int N, int bound) {
        int[] ballArr = new int[N];
        for (int i = 0; i < N - 1; i++) {
            ballArr[i] = rand.nextInt(bound);
        }
        return ballArr;
    }

    public static void threeColors() {
        long start = System.nanoTime();
        for (int i = 0; i < testN.length; i++) {
            int[] genArr = generateArray3(testN[i]);
            printArr(OnQuicksortAny(genArr, 2, genArr.length));
            System.out.println("\n");
        }
        System.out.println("Run Rime: " + (System.nanoTime() - start) / 1000000 + " ns");
    }

    public static void anyColors() {
        Scanner in = new Scanner(System.in);
        int[] arr = null;
        int bound = 0;
        while (bound <= 1) {
            System.out.println("Enter the number of colors you want: ");
            bound = Integer.parseInt(in.nextLine());
        }
        long start = System.nanoTime();
        for (int i = 0; i < testN.length; i++) {
            int[] genArr = generateArrayAny(testN[i], bound);
            arr = OnQuicksortAny(genArr, bound - 1, genArr.length);
            System.out.println("\n");
        }
        long total = (System.nanoTime() - start) / 1000000;
        start = System.nanoTime();
        printArr(arr);
        long printTotal = (System.nanoTime() - start) / 1000000;
        System.out.println("\n");
        System.out.println("Elements Sorted: " + testN[0]);
        System.out.println("Sort Time: " + total + " ms");
        System.out.println("Print Time: " + printTotal + " ms");
    }
}
