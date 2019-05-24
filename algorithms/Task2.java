/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import static algorithms.optimizationTime.time;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import javax.crypto.Mac;

/**
 *
 * @author LENOVO
 */
public class Task2 {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] ex1 = {2, 4, 1, 8, 2, 1, 4};
        int max1 = 10;
        int[] ex2 = {5, 8, 2, 6, 9, 5, 4};
        int max2 = 12;
        int[] ex3 = {5, 9, 2, 6, 3, 5, 4};
        int max3 = 12;
        int[] ex4 = {4, 6, 9, 1, 4, 8, 7, 9, 3, 3};
        int max4 = 20;
        int[] ex5 = {2, 6, 4, 10, 8, 14, 2, 4, 8, 6};
        int max5 = 11;
        int[] ex6 = {31, 44, 17, 46, 18, 12, 1, 5, 20, 43, 42, 31, 39, 35, 7, 49, 13, 5, 13, 31, 32, 10, 12, 46, 37, 25, 18, 7, 46, 28};
        int max6 = 100;
        int maxN = 10;
        int[] exN = {8, 5, 4, 3, 1};
        //        greedy(max1, ex1);

//        greedy(max2, ex2);
//        int[] powerTwo = powerTwo(test);
//        System.out.println("ks");
//        System.out.println(ks(max1, ex1));
//        System.out.println("test");
//        System.out.println(test);
//        System.out.println(9999 * 9999);
//        System.out.println(Integer.MAX_VALUE);
// the knapsack 
//        System.out.println(knapSack(max5, ex5));
//        both(max6, ex6);
        // the greedy and knapsack from file
        fileToArray(100);
//both(maxN, exN);

        //100000000
    }
    //
//   MAKE_CHANGE(denominations, amount)
//foreach(denomination in denominations)
//n=amount/denomination
//if(n>0)
//add n coins of denomination to the solution
//amount=amount%denomination
//if(amount==0)
//return solution
//return NO SOLUTION

    /**
     *
     * @param maxLoad
     * @param masses
     * @return
     */
    public static int[] greedy(int maxLoad, int masses[]) {
        int[] unsorted = Arrays.copyOf(masses, masses.length);
        Arrays.sort(masses);
        int remain = maxLoad;
        ArrayList<Integer> solution = new ArrayList<>();
        ArrayList<Integer> itemsPlace = new ArrayList<>();

        for (int i = masses.length - 1; i >= 0; i--) {
            if (masses[i] <= remain) {
                solution.add(masses[i]);
                remain -= masses[i];
            }
        }
        for (int i = 0; i < solution.size(); i++) {
            for (int j = 0; j < unsorted.length; j++) {
                if (solution.get(i) == unsorted[j] && !itemsPlace.contains(j)) {
                    itemsPlace.add(j);
                    break;
                }
            }
        }
        if (remain == 0) {
            System.out.println("number of items in solution");
            System.out.println(solution.size());
            System.out.println("solution items");
            System.out.println(solution.toString());
            System.out.println("items places");
            Collections.sort(itemsPlace);
            System.out.println(itemsPlace.toString());
            System.out.println("hella Yeah");
        } else {
            System.out.println("no solution... sorry");
        }

        return new int[]{0};
    }

    /**
     *
     * @param Array
     * @return
     */
    public static int[] powerTwo(int[] Array) {
        int[] numberArray = Array.clone();
        for (int i = 0; i < numberArray.length; i++) {
            numberArray[i] = numberArray[i] * numberArray[i];
        }
        return numberArray;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public static int max(int x, int y) {
        return x > y ? x : y;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public static int min(int x, int y) {
        return x < y ? x : y;
    }

    static int[][] ksSol = new int[8][11];

    /**
     *
     * @param maxLoad
     * @param masses
     * @return
     */
    public static int ks(int maxLoad, int masses[]) {
        int result;
        if (ksSol[masses.length][maxLoad] != 0) {
            return ksSol[masses.length][maxLoad];
        }
        if (maxLoad == 0 || masses.length == 0) {
            result = 0;
        } else if (masses[masses.length - 1] > maxLoad) {
            result = ks(maxLoad, Arrays.copyOf(masses, masses.length - 1));
        } else {
            int temp1 = ks(maxLoad, Arrays.copyOf(masses, masses.length - 1));
            int temp2 = masses[masses.length - 1] + ks(maxLoad - masses[masses.length - 1], Arrays.copyOf(masses, masses.length - 1));

            result = temp1 > temp2 ? temp1 : temp2;
            ksSol[masses.length][maxLoad] = result;
        }
        //            ksSol.add(result == temp1 ? masses.length : masses.length - 1);
        return result;
    }

    /**
     *
     * @param maxLoad
     * @param masses
     * @return
     */
    public static int itKS(int maxLoad, int masses[]) {
        int table[][] = new int[maxLoad][masses.length];
        for (int i = 0; i < maxLoad; i++) {
            table[i][0] = 0;
        }
        for (int i = 0; i < masses.length; i++) {
            table[0][i] = 0;
        }
        for (int i = 1; i < maxLoad; i++) {
            for (int j = 0; j < masses.length; j++) {
                if (j > maxLoad) {
                    table[i][j] = table[maxLoad][masses.length - 1];

                } else {
                    table[i][j] = max(table[i - 1][j], masses[j] + table[maxLoad - masses[j]][]);
                }
            }
        }

        int temp1 = ks(maxLoad, Arrays.copyOf(masses, masses.length - 1));
        int temp2 = masses[masses.length - 1] + ks(maxLoad - masses[masses.length - 1], Arrays.copyOf(masses, masses.length - 1));

        result = temp1 > temp2 ? temp1 : temp2;
        ksSol[masses.length][maxLoad] = result;

        //            ksSol.add(result == temp1 ? masses.length : masses.length - 1);
        return 0;
    }

    static int knapSack(int maxLoad, int items[]) {
//        System.out.println(Arrays.toString(items));

        int table[][] = new int[items.length + 1][maxLoad + 1];
        for (int i = 0; i <= items.length; i++) {
            for (int j = 0; j <= maxLoad; j++) {
                table[i][j] = 0;
            }
        }
        // Build table K[][] in bottom up manner 
        for (int i = 1; i <= items.length; i++) {
//            System.out.println("");
            for (int j = 1; j <= maxLoad; j++) {
//                if (i == 0 || j == 0) {
//                    table[i][j] = 0;
//                } else
                if (items[i - 1] <= j) {
                    table[i][j] = Math.max(items[i - 1] + table[i - 1][j - items[i - 1]], table[i - 1][j]);
//                    System.out.println("");
                } else {
                    table[i][j] = table[i - 1][j];
                }
            }
        }

//        System.out.println(table);
        minTableCraw(maxLoad, table, items);
        return table[items.length][maxLoad];
    }

    static int MinKnapSack(int maxLoad, int items[]) {
//        System.out.println(Arrays.toString(items));

        int table[][] = new int[items.length + 1][maxLoad + 1];
        for (int i = 0; i <= items.length; i++) {
            for (int j = 0; j <= maxLoad; j++) {
                table[i][j] = items.length;
            }
        }
        // Build table K[][] in bottom up manner 
        for (int i = 1; i <= items.length; i++) {
//            System.out.println("");
            for (int j = 1; j <= maxLoad; j++) {
                if (i == 0 || j == 0) {
                    table[i][j] = 0;
                } else if (items[i - 1] <= j) {
                    table[i][j] = Math.min((1 + table[i - 1][j - items[i - 1]])%100, table[i - 1][j]);
//                    System.out.println("");
                } else {
                    table[i][j] = table[i - 1][j]%100;
                }
            }
        }

//        System.out.println(table);
//        tableCraw(maxLoad, table, items);
        return table[items.length][maxLoad];
    }

    /**
     *
     * @param table
     */
    public static void printTable(int table[][]) {
        System.out.println("Printing");
//        System.out.println(" = ");
        System.out.printf("%3s ", ""
        );
        for (int j = 0; j < table[0].length; j++) {
            System.out.printf("%5s ", j);
        }
        System.out.println("");
        for (int i = 0; i < table.length; i++) {
            System.out.print(i + " = ");
            for (int j = 0; j < table[i].length; j++) {
                System.out.printf("%5s ", table[i][j]);
            }
            System.out.println("");
        }
        System.out.println(" finished XD ");
    }

    /**
     *
     * @param maxLoad
     * @param table
     * @param weight
     */
    public static void tableCraw(int maxLoad, int table[][], int weight[]) {
//        printTable(table);
        int m = maxLoad;
        System.out.println("shall be the rseult");
        for (int i = table.length; i > 0; i--) {
            if (m == table[i - 1][m]) {
                continue;
            } else {
                System.out.print(i + " ");
//                System.out.println("weight= " + weight[i - 1]);

                m = m - weight[i - 1];
            }
        }
        System.out.println("end of it");
    }

    /**
     *
     * @param maxLoad
     * @param table
     * @param weight
     */
    public static void minTableCraw(int maxLoad, int table[][], int weight[]) {
//        printTable(table);
        ArrayList<Integer> temp = new ArrayList<>();
        ArrayList<Integer> solution = new ArrayList<>();
        ArrayList<Integer> weights = new ArrayList<>();
        ArrayList<Integer> tempWeights = new ArrayList<>();

        int m = maxLoad;
//        System.out.println("shall be the rseult");
        all:
        for (int j = table.length - 1; j > 0; j--) {
            if (table[j][m] != maxLoad) {
                break;
            }
            temp.add(j);
            tempWeights.add(weight[j - 1]);
//            System.out.print(j + " ");
//            System.out.println("weight= " + weight[j - 1]);
            m = m - weight[j - 1];
            for (int i = table.length; i > 0; i--) {
                if (m < 0) {
                    break all;
                }
                if (m == table[i - 1][m]) {
                    continue;
                } else {
                    temp.add(i);
                    tempWeights.add(weight[i - 1]);

//                    System.out.print(i + " ");
//                    System.out.println("weight= " + weight[i - 1]);
                    m = m - weight[i - 1];

                }
            }
            //  System.out.println("Temp size" + temp.size());

            if (solution.size() == 0) {
                solution = (ArrayList<Integer>) temp.clone();
                weights = (ArrayList<Integer>) tempWeights.clone();
            }
            if (temp.size() < solution.size() && temp.size() != 0) {
                solution = (ArrayList<Integer>) temp.clone();
                weights = (ArrayList<Integer>) tempWeights.clone();
            }
            temp.clear();
            tempWeights.clear();
            m = maxLoad;
        }
        if (solution.size() == 0 || m < 0) {
            System.out.println("there is no solution Sorry");
        } else {
            System.out.println(solution.size() + "  items to fill the truck");
            System.out.println(solution.toString() + " number of items");
            System.out.println(weights.toString() + " Weights of items");
        }

//        System.out.println("end of it");
    }

    /**
     *
     * @param items
     * @param weight
     * @return
     */
    public static int dpKS(int[] items, int weight) {
        int table[][] = new int[items.length][weight];
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < weight; j++) {
                table[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 1; i < items.length; i++) {
            for (int j = 1; j < weight; j++) {
                System.out.println(i + "  " + j);
                table[i][j] = min(table[i - 1][weight - items[i - 1]] + items[i - 1], table[i - 1][weight - 1]);
            }
        }
        System.out.println("test");
        return 0;
    }

    /*
    SubsetSum(n, W):
Initialize M[0,r] = 0 for each r = 0,...,W
Initialize M[j,0] = 0 for each j = 1,...,n
For j = 1,...,n: for every row
For r = 0,...,W: for every column
If w[j] > r: case where item can’t fit
M[j,r] = M[j-1,r]
M[j,r] = max( which is best?
M[j-1,r],
w[j] + M[j-1, W-w[j]]
)
Return M[n,W]
     */

    /**
     *
     * @param items
     * @param w
     * @return
     */

    public static int subSum(int[] items, int w) {
        int table[][] = new int[items.length][w];
        for (int i = 0; i < w; i++) {
            table[0][i] = 0;
        }
        for (int i = 0; i < items.length; i++) {
            table[i][0] = 0;
        }
        for (int i = 1; i < items.length; i++) {
            for (int j = 0; j < w; j++) {
                if (items[i] > j) {
                    table[i][j] = table[i - 1][j];
                }
                table[i][j] = max(table[i - 1][j], items[i] + table[i - 1][w - items[i]]);

            }
        }
        System.out.println("here its ");
        System.out.println(table[items.length - 1][w - 1]);
        return 0;
    }

    /**
     *
     * @param n
     */
    public static void fileToArray(int n) {
        Scanner sc;
        try {
            sc = new Scanner(new File("test" + n + ".txt"));
            int maxLoad = sc.nextInt();
            int length = sc.nextInt();
            int x[] = new int[length];
            for (int j = 0; j < length; j++) {
                x[j] = sc.nextInt();
            }
            System.out.println(MinKnapSack(maxLoad, x));
//            greedy(maxLoad, x);
        } catch (FileNotFoundException ex) {
            System.out.println("Please check the file location");
        }
    }

    /**
     *
     * @param maxLoad
     * @param x
     */
    public static void both(int maxLoad, int[] x) {
        System.err.println("normal numbers");
        System.out.println(knapSack(maxLoad, x));
        greedy(maxLoad, x);
        System.err.println("Masses of items power two");
        System.out.println(knapSack(maxLoad, powerTwo(x)));
        greedy(maxLoad, powerTwo(x));
    }
}
