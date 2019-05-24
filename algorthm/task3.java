/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorthm;

import static algorthm.Task2.MinKnapSack;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author LENOVO
 */
public class task3 {

    public static void main(String[] args) {
        task3 ts = new task3();
        ts.readFile();
        
    }
//    -insert new number

    public void insert() {
    }
//-delete a number

    public void delete() {
    }
//-search for a number

    public void search() {
    }
//-find how many numbers in the set are from interval <a, b> (try to do in effectively without
//searching the whole tree)

    public void interval() {
    }
//-print tree structure on screen

    public void print() {
    }
//-construct a tree from file

    public void readFile() {
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
