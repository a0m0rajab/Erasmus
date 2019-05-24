/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class Sort {

    // well idk why i used it when i wrote the code, i think i have to analyse it and check it again later, 
    // but in geenral it count the number of operations in the algorithm.
    static int ops = 0;
    /**
     * warper method for all test methods.
     * <br> it calls the most occurence test. 
     * <br> and the sorting test.
     * 
     */
    void testall() {
        testMostOccurence();
        testSorting();
    }

    /**
     * A method of merge sort to merge the array again all together after
     * sorting it.
     *
     * @param a - the main array
     * @param l - left array
     * @param r - right array
     * @param left - left array length
     * @param right - right array length
     */
    public static void merge(
            long[] a, long[] l, long[] r, long left, long right) {
        ops++;
        // increasingly adding the elemants compared between two array to the main array.
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        // if still elemant did not got compared just add them.
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    /**
     * A method of mergeSort to devide the array to two paths , sort it and get
     * it back.
     *
     * @param a array itself,
     * @param n number of elemants in array.
     */
    public static void mergeSort(long[] a, int n) {
        // if there is less than two elemants return/finsihed.
        if (n < 2) {
            return;
        }
        // get the length of two array after spliting and initilaising it.
        int mid = n / 2;
        long[] l = new long[mid];
        long[] r = new long[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);
        merge(a, l, r, mid, n - mid);
    }

    /**
     * A method that loops in a sorted array to find the most occurence number.
     *
     * @param arr get the array to check the occurency on it.
     * @return the number, occurency number, and operation number.
     */
    public static long[] mostOccurence(long arr[]) {
        // resest the operation number, get the first elamant as most occurence, 
        // get count zero and compared it with resultCount, and compare first elemant with result.
        ops = 0;
        long firstElemant = arr[0];
        int count = 0;
        int resultCount = 0;
        long result = arr[0];
        // loop through the array and compare it.
        for (int i = 0; i < arr.length; i++) {
            ops++;
            if (firstElemant == arr[i]) {
                count = count + 1;
            } else {
                if (count > resultCount) {

                    result = firstElemant;
                    resultCount = count;
                } else if (count == resultCount && result < firstElemant) {
                    result = firstElemant;
                }
//                    System.out.println(count + " "+ firstElemant);
                firstElemant = arr[i];
                count = 1;
            }

        }
        return new long[]{result, resultCount, ops};
    }

    /**
     * A test methdos of using the mergeSort algorithm.
     */
    public void testSorting() {
        long[] actual = {5, 1, 6, 3, 3, 4};
        long[] expected = {1, 2, 3, 4, 5, 6};
        Sort.mergeSort(actual, actual.length);
        System.out.println(Arrays.equals(actual, expected));
    }

    /**
     * A method to test the most occurence number in the array.
     */
    public void testMostOccurence() {
        System.out.println("test");
        long array[] = fileReaders.fileToArray(10);
        Sort.mergeSort(array, array.length);
        long[] number = mostOccurence(array);
        System.out.println(number[0] + " occurence time " + number[1]);

        System.out.println("ops = " + ops);
        System.out.println("finished");
    }

}
