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
public class BST {
static final int COUNT = 10; 
    nodeBST root;

    /**
     * A constructor of an empty binary search tree.
     */
    public BST() {
        root = null;
    }

    /**
     * A warpper for the recursive insert method.
     * @param number
     */
    void insertR(int number) {
        root = insertRec(root, number);
    }

    /**
     * A warper method for recursive search method for a specific int key in the tree.
     * <br> print yes if the key found,
     * <br> no if it did not find the key.
     * @param key the number to found.
     */
    public void Search(int key) {
        if (searchRec(this.root, key) != null) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    /**
     * Recursive search method which implements the search algorithm.
     * <br> first it check if the value is in the node,
     * <br> if the value is smaller than the root it go to left side, vice versa.
     * 
     * @param root the root of the sub tree.
     * @param value the value we looking to find.
     * @return the node of BST if found, can be null if it did not the value.
     */
    public nodeBST searchRec(nodeBST root, int value) {
        if (root == null || root.key == value) {
            return root;
        }

        if (root.key > value) {
            return searchRec(root.left, value);
        }

        return searchRec(root.right, value);
    }

    /**
     * Shall be interval....
     * @param root
     * @param value
     * @return
     */
    public int intervalOneSide(nodeBST root, int value) {
        if (root == null || root.key == value) {
            return 0;
        }

        if (root.key > value) {
            return intervalOneSide(root.left, value) + 1;
        }

        return intervalOneSide(root.right, value) + 1;
    }

    void inorder() {
        inorderRec(root);
    }

    void inorderRec(nodeBST root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.key);
            inorderRec(root.right);
        }
    }

    nodeBST insertRec(nodeBST root, int num) {
        if (root == null) {
            return new nodeBST(num);
        }
        if (num < root.key) {
            root.left = insertRec(root.left, num);
        } else if (num > root.key) {
            root.right = insertRec(root.right, num);
        }
        return root;
    }

    void deleteKey(int key) {
        root = deleteRec(root, key);
    }

    nodeBST deleteRec(nodeBST root, int key) {
        /* If the tree is empty */
        if (root == null) {
            return root;
        }

        /* Otherwise, recur down the tree */
        if (key < root.key) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteRec(root.right, key);
        } // if key is same as root's key, then This is the node to be deleted 
        else {
            // node with only one child or no child 
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // node with two children: Get the smallest number in the right tree.
            root.key = minValue(root.right);

            // Delete the smallest number 
            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    int minValue(nodeBST root) {
        int minv = root.key;
        while (root.left != null) {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }

    void preOrderTabular() {
        preOrderRec(root, 1);
    }

    void preOrderRec(nodeBST root, int space) {
        if (root != null) {
            System.out.printf("%d ", root.key);
            System.out.println("");
            for (int i = 0; i <= space; i++) {
                System.out.printf("\t");
            }
            preOrderRec(root.left, space + 1);
            preOrderRec(root.right, space + 1);
        }
    }

     void print2DUtil(nodeBST root, int space) {
        // Base case  
        if (root == null) {
            return;
        }

        // Increase distance between levels  
        space += COUNT;

        // Process right child first  
        print2DUtil(root.right, space);

        // Print current node after space  
        // count  
        System.out.print("\n");
        for (int i = COUNT; i < space; i++) {
            System.out.print(" ");
        }
        System.out.print(root.key + "\n");

        // Process left child  
        print2DUtil(root.left, space);
    }

     void print2D() {
        // Pass initial space count as 0  
        print2DUtil(root, 0);
    }

    /**
     *
     * @param number
     * @param num
     * @return
     */
    public int iterval(int number, int num) {
        root = this.root;
        if (root == null) {
            return 0;
        } else {

            int x = num > number ? num : number;
            int y = x == num ? number : num;

            int lheight = intervalOneSide(root.left, x);
            int rheight = intervalOneSide(root.right, y);

            return (rheight + lheight);
        }
    }

    int interval(int low, int high) {
        return getCount(this.root, low, high);
    }

    // order statis tree
    int getCount(nodeBST node, int low, int high) {
        // Base Case 
        if (node == null) {
            return 0;
        }

        // in range?
        if (node.key >= low && node.key <= high) {
            return 1 + this.getCount(node.left, low, high)
                    + this.getCount(node.right, low, high);
        } // smaller? --> right 
        else if (node.key < low) {
            return this.getCount(node.right, low, high);
        } // go left.
        else {
            return this.getCount(node.left, low, high);
        }
    }
}
