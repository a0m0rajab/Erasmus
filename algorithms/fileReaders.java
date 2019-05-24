/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author LENOVO
 */
public class fileReaders {

    /**
     * File reader to convert the readed file to an array and use it on other
     * methods. input number to check the test data that asked for in the class.
     * <br> Used for the first task examples.
     *
     * @param n number of input data in file to read. input Data from 10 power
     * to 1 till 6.
     * @return a long array contians the array from file or null, if there was
     * any error.
     */
    public static long[] fileToArray(int n) {
        Scanner sc;
        try {
            sc = new Scanner(new File("in" + n + ".txt"));
            int length = sc.nextInt();
            long x[] = new long[length];
            for (int j = 0; j < length; j++) {
                x[j] = sc.nextLong();
            }
//            time(0, length, x);
//            time(2, length, x);
//            time(1, length, x);
//            time(1, length, x);
            return x;
        } catch (FileNotFoundException ex) {
            System.out.println("Please check the file location");
            return null;
        }

    }

    /**
     * Reads the tree commands and excute them, then print the last version of the tree in tabular mode.
     * <br> Used for the third task.
     *
     */
    public void BST_Reader() {
        String path = "C:\\Users\\LENOVO\\Downloads\\example_hand.txt";
        Scanner sc;
        try {
            sc = new Scanner(new File(path));

            int numberOfInstruction = sc.nextInt();
            String com;
            int x, y;
            BST bst = new BST();
            for (int i = 0; i < numberOfInstruction; i++) {
                com = sc.next();
                switch (com) {
                    case "INT":
                        System.out.println(bst.interval(sc.nextInt(), sc.nextInt()));
                        break;
                    case "ADD":
                        bst.insertR(sc.nextInt());
                        break;
                    case "DEL":
                        bst.deleteKey(sc.nextInt());
                        break;
                    case "SEA":
                        bst.Search(sc.nextInt());
                        break;
                    default:
                        System.out.println("command not found" + com);

                }

            }
//            System.out.println("The numbers");
//            bst.inorder();
////            System.out.println("The tree");
////            bst.preOrderTabular();
            System.out.println("test 2");
            bst.print2D();
//            greedy(maxLoad, x);
        } catch (FileNotFoundException ex) {
            System.out.println("Please check the file location");
        }
    }

}
