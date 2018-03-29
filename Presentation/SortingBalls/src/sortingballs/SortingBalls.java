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

    public static int[] OnQuicksort3(int[] balls, int max, int endIndex) {
        int countmin = 0, countmax = 0;
        int leftVal, rightVal;
        int end = endIndex, start = 0;
        for (int i = 0; i < end; i++) {
            if (balls[i] < max) {
                countmin++;
            } else if (balls[i] == max) {
                countmax++;
            }
        }
        end = end - 1;
        int pivot = end - countmax + 1; // Makes sure that the spots to the right of the pivit will hold the number of 1's
        while (true) {
            leftVal = balls[start];
            rightVal = balls[end];
            if (start == pivot) {
                if (balls[pivot] == max && leftVal != max && rightVal == max) {
                    balls[end] = balls[pivot];
                    balls[pivot] = rightVal;
                    break;
                } else if (leftVal == max) {
                    break;
                }
            } else if (end == pivot) {
                if (balls[pivot] != max && rightVal != max && leftVal == max) {
                    balls[start] = balls[pivot];
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
            return OnQuicksort3(balls, max - 1, pivot);
        }
    }

    public static int[] OnQuicksort2(int[] balls) {
        int count1 = 0, count0 = 0;
        int leftVal, rightVal;
        int end = 0, start = 0;
        for (int i = 0; i < balls.length; i++) {
            if (balls[i] == 0) {
                count0++;
            } else {
                count1++;
            }
        }
        int pivot = balls.length - count1; // Makes sure that the spots to the right of the pivit will hold the number of 1's
        end = balls.length - 1;
        while (true) {
            leftVal = balls[start];
            rightVal = balls[end];
            if (start == pivot && balls[pivot] == 0 && leftVal == 1 && rightVal == 0) {
                balls[end] = balls[pivot];
                balls[pivot] = rightVal;
                break;
            } else if (end == pivot && balls[pivot] == 0 && rightVal == 0 && leftVal == 1) {
                balls[start] = balls[pivot];
                balls[pivot] = leftVal;
                break;
            }
            if (leftVal == 0) {
                start++;
            }
            if (rightVal == 1) {
                end--;
            }
            if (leftVal > rightVal) { // swaps the values
                int temp = leftVal;
                balls[start] = rightVal;
                balls[end] = temp;
            }

        }
        return balls;
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
            printArr(OnQuicksort2(generateArray2(testN[i])));
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
            printArr(OnQuicksort3(genArr, 2, genArr.length));
            System.out.println("\n");
        }
        System.out.println("Run Rime: " + (System.nanoTime() - start) / 1000000 + " ns");
    }

    public static void anyColors() {
        Scanner in = new Scanner(System.in);
        int[] arr = null;
        int bound = 0;
        while(bound <= 1){
        System.out.println("Enter the number of colors you want: ");
        bound = Integer.parseInt(in.nextLine());
        }
        long start = System.nanoTime();
        for (int i = 0; i < testN.length; i++) {
            int[] genArr = generateArrayAny(testN[i], bound);
            arr = OnQuicksort3(genArr, bound - 1, genArr.length);
            System.out.println("\n");
        }
        long total = (System.nanoTime() - start) / 1000000;
        start = System.nanoTime();
        printArr(arr);
        long printTotal = (System.nanoTime() - start) / 1000000;
        System.out.println("\n");
        System.out.println("Sort Time: " + total + " ms");
        System.out.println("Print Time: " + printTotal + " ms");
    }
}
