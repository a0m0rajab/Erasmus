/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package why;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author LENOVO
 */
public class Dijkestra {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        Dijkestra dj = new Dijkestra();
        dj.readFile();
        dj.shortestPath();
        dj.printAll();

    }
    // the start point
    int initVertic;
// the distance from start point to other points
    int distance[];
    // the number of steps between start point and other points
    int steps[];
    // the nodes that passed throw the movement
    ArrayList<Integer>[] passedNodes;
    // the  adjacency matrix 
    int adjMatrics[][];

    public Dijkestra(int[][] adjMatrics, int start) {
        this.adjMatrics = adjMatrics;
        this.initVertic = start;
    }

    public Dijkestra() {
    }

    public void setDijkestra(int[][] adjMatrics, int start, int vn) {
        this.adjMatrics = adjMatrics;
        this.initVertic = start;
        this.steps = new int[vn];
        this.distance = new int[vn];
        this.passedNodes = new ArrayList[vn];
        for (int i = 0; i < vn; i++) {
            this.passedNodes[i] = new ArrayList<Integer>();
        }

    }

    public void readFile() {
        Scanner sc;
        try {
            sc = new Scanner(new File("C:\\Users\\LENOVO\\Downloads\\dij.txt"));
            // vertices number
            int vn = sc.nextInt();
            // edges number
            int en = sc.nextInt();
            // the start point
            int sp = sc.nextInt() - 1;

            int x[][] = new int[vn][vn];
            for (int[] elem : x) {
                Arrays.fill(elem, -1);
            }
            for (int j = 0; j < en; j++) {
                x[sc.nextInt() - 1][sc.nextInt() - 1] = sc.nextInt();
            }
            setDijkestra(x, sp, vn);
        } catch (FileNotFoundException ex) {
            System.out.println("Please check the file location");

        }
    }

    /*
    
     */
    public void shortestPath() {
        // vertices
        for (int i = 0; i < adjMatrics.length; i++) {

            if (i == initVertic) {
                distance[i] = 0;
            } else {
                distance[i] = adjMatrics[initVertic][i];
            }
        }

        for (int i = 0; i < adjMatrics.length; i++) {
            if (i == initVertic) {
                passedNodes[i].add(0);
            } else if (adjMatrics[i][initVertic] != -1) {
                passedNodes[i].add(initVertic);
            }
        }
        // started to loop around vertics
        for (int i = 0; i < adjMatrics.length; i++) {
            if (i == initVertic) {
                continue;
            }
            for (int j = 0; j < adjMatrics.length; j++) {
                if (j >= i) {
                    if (distance[j] > distance[i] + adjMatrics[i][j]) {
                        distance[j] = distance[i] + adjMatrics[i][j];
                        steps[j] = i;
                    }
                }
            }

        }

    }

    public void printAll() {
        System.out.println(steps);
        System.out.println(distance);
    }

}
