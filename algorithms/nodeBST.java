/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

/**
 *
 * @author LENOVO
 */
public class nodeBST {

    int key;
    nodeBST right;
    nodeBST left;

    /**
     * A comstruuctor of binary search tree node, with having a null children.
     * @param item take an integer key of the first node in the tree.
     */
    public nodeBST(int item) {
        key = item;
        left = right = null;
    }

}
