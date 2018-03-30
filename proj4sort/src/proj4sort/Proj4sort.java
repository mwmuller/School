/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj4sort;

import java.util.*;

/**
 *
 * @author Michael Muller
 */
public class Proj4sort {

    static int[] testN = {1000, 10000, 100000};
    static Random rand = new Random();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        MergeSort ob = new MergeSort();
        int[] arr = null;
        Scanner in = new Scanner(System.in);
        System.out.println("What array size would you like?\n"
                + "1) 1000\n"
                + "2) 10,000\n"
                + "3) 100,000\n"
                + "4) End");
        int selection = Integer.parseInt(in.nextLine());
        if (selection <= 3) {
            for (int i = 0; i < 5; i++) {
                arr = generateArr(testN[selection - 1]);
                ob.sort(arr, 0, arr.length - 1);
            }
        } else {
            System.exit(-1);
        }

    }

    public static int[] generateArr(int N) {
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = rand.nextInt();
        }
        return arr;
    }
}
