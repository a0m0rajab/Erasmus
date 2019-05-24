/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Abdurrahman RAJAB
 */
public class optimizationTime {

   
//    public static void main(String[] args) {
    // test codes.
//        // first array from the file
////        int arr[] = {1, 2, 3, 1, 2, 3, 1, 2, 3};
////        // try on both algorthms
////        nTn(arr.length, arr);
////        n(arr.length, arr);
////        int x[] = randomArray(100000, 1000);
////        time(0, x.length, x);
////        time(1, x.length, x);
////        time(2, x.length, x);
////        fileToArray();
//      
//    }
    /**
     * A methods that print all the written algorithms, in text-table form.
     * Shows the in-number,algorithm name, most occurened number, the number of
     * occurence, number of operatations and time in millisecond.
     *
     */
    public void printAll() {
        // since the files name ends with 10,100,1000 till 1000000
        int n = 10;
        long[] x;
        for (int i = 0; i < 3; i++) {
//        int n = 10; // yeah logical error, kept to remind me of my small mistakes :) 
//            Sort.ops = 0;
            x = fileReaders.fileToArray(n);
            print(x);
            n = n * 10;
        }
    }

    void print(long x[]) {
        System.out.println("");
        System.out.printf("%20s %20s %20s %20s %20s %20s \n", "in number", "algorithm", "number", "occurence", "number of ops", "time(mills)");

        for (int j = 0; j < 4; j++) {
            time(j, x.length, x);
        }

    }

    /**
     * Aa method that get the Result of algorithms to be able to print it in
     * text-table mode. used in time method with the written algorithms.
     *
     * @param result the results of algorithm to be printed.
     *
     */
    public static void PrintLine(long[] result) {
        //"number", "occurence",  "number of ops","time"
        System.out.printf("%20d %20d %20d", result[0], result[1], result[2]);

    }

    /**
     * A method to check the time of a specific algorthm by choosing it.
     * PrintLine used inside to show the results.
     *
     * @param x number of algorthm to check, <br>
     * 0 --> Hash algorithms  <br>
     * 1 --> n^2 algorithm, which have two loops that go throw the array to find
     * the most occurence number.
     * <br> 2 --> Two array algorithms works as an array to keep the elemenat
     * and array to keep the occurence number , meanwhile it checks the last
     * occurence number to find the highest.
     * <br>3 --> Sorting the Array using merge sort with NlogN O(n), then going
     * through the array to count the number of elemants
     *
     * @param n number of elemants in array
     * @param arr[] the array of elements
     */
    public static void time(int x, int n, long arr[]) {
        // in number.
        System.out.printf("%20d", n);
        // start time calculating.
        Instant start = Instant.now();

        switch (x) {
            case 0:
                System.out.printf("%20s", "Hash algorithm ");
                PrintLine(n(n, arr));
                break;
            case 1:
                System.out.printf("%20s", "n^n algorithm ");
                PrintLine(nTn(n, arr));
                break;
            case 2:
                //Arrays.stream(arr).mapToLong(i -> i).toArray()
                System.out.printf("%20s", "two array algorithm ");
                PrintLine(nAlgo(n, arr));
                break;
            case 3:
                //Arrays.stream(arr).mapToLong(i -> i).toArray()
                System.out.printf("%20s", "Merge sorted ");
                Sort.mergeSort(arr, arr.length);
                PrintLine(Sort.mostOccurence(arr));
                break;
            default:
                System.err.println("The wanted algorthm not found.");
                return;
        }

        // execution time calculation
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();  //in millis
        System.out.printf("%20d\n", timeElapsed);
    }

    /**
     * Array with random numbers generator. used as test, before getting the
     * test files.
     *
     * @param limit number of elemants in array
     * @param maxNum largest limit of the generated random number .
     * @return Array of random generated number
     */
    public static int[] randomArray(int limit, int maxNum) {
        Random randNumGenerator = new Random();
        int[] rand = new int[limit];
        for (int i = 0; i < rand.length; i++) {
            rand[i] = (randNumGenerator.nextInt(maxNum) + 1);
        }
        return rand;
    }

    /**
     * an algorithm of N^2 time complexity to get the largest most repeated
     * number in an array
     * <br> it has two arrays that loops through the array to check the number.
     *
     * @param n the length of the array
     * @param arr[] the array to be checked
     * @return an array that contians
     * <br>0 = the largest repeated number ,
     * <br>1 = repeated time.
     */
    public static long[] nTn(int n, long arr[]) {
        // num = the most occurence number.
        // temp to compare it.
        long num = arr[0], temp = 0l;
        // times is the time of the most occured number , coutner is to check as temp.
        int times = 0,
                // number of ops to check the number of operation that excuted.
                counter = 0,
                numberOfOps = 0;

        // first loop take the number and check it.
        for (int i = 0; i < n; i++) {
            temp = arr[i];
            counter = 0;
            // secpmd loop to count the number.
            for (int j = i; j < n; j++) {
                if (arr[j] == temp) {
                    counter += 1;
                }
                numberOfOps++;
            }
            // check the number and occurence time compared to the temp.
            if (counter > times) {
                num = temp;
                times = counter;
                // if its the same then get the bigger number.
            } else if (counter == times && num < temp) {
                num = temp;
            }

        }
//        System.out.println(num + " " + times);
        return new long[]{num, times, numberOfOps};

    }

    /**
     * A method of N time complexity to get the largest most repeated number in
     * an array, using hashTable implementation.
     *
     * @param n the length of the array
     * @param arr[] the array to be checked
     * @return an array that contians
     * <br>0 = the largest repeated number ,
     * <br>1 = repeated time.
     * <br> 2= number of operations
     *
     */
    public static long[] n(int n, long arr[]) {
        // number of operations
        int numberOfOps = 0;
        // hashtable 
        Hashtable<Long, Long> h = new Hashtable<Long, Long>();
        // the largest number and temp to check it.
        Long previous = 0l, saveme = 0l;
        for (int i = 0; i < n; i++) {
            // if the number is not inside the hashTable
            if (!h.containsKey(arr[i])) {
                h.put(arr[i], 0l);
            }

            Long temp = h.get(arr[i]);
            h.put(arr[i], ++temp);

// compare with largest and get the biggest number.
            if (temp > previous) {
                saveme = arr[i];
                previous = temp;
            } else if (temp == previous && saveme < arr[i]) {
                saveme = arr[i];
            }
            numberOfOps++;

        }
        Long times = h.get(saveme);
//        System.out.println(saveme + " " + times);
        return new long[]{saveme, times, numberOfOps};

    }

    /**
     * A method to check that have two array one to keep the number and other to
     * save the occurence number of it.
     *
     * then it compare the numbers and get the most occured.
     *
     * @param n number of elemants in array
     * @param arr[] the array of elements
     * @return array contains : the most occured number, occurence time and
     * operation number.
     */
    public static long[] nAlgo(int n, long arr[]) {
        // number of operations, numbers array and counter array.
        long numberOfOps = 0l;
        ArrayList<Long> numbers = new ArrayList<>();
        ArrayList<Integer> count = new ArrayList<>();
        // frequence of number and the first number of array as most occurence number.
        long frequency = 0l;
        long number = arr[0];
        //loop through the array.
        for (int i = 0; i < arr.length; i++) {
            // check if its in the array or not.
            if (!numbers.contains(arr[i])) {
                numbers.add(arr[i]);
                count.add(count.size(), 1);
            } else {
                // get the index set the count add one, and then compare the occurency number with previous data.
                int index = numbers.indexOf(arr[i]);
                count.set(index, count.get(index) + 1);
                if (count.get(index) > frequency) {
                    frequency = count.get(index);
                    number = arr[i];
                } else if (count.get(index) == frequency && number < arr[i]) {
                    number = arr[i];
                }
            }
            numberOfOps++;
        }
//        System.out.println(number + " == " + frequency);
        return new long[]{number, frequency, numberOfOps};
    }

}
