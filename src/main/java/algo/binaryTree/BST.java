package algo.binaryTree;

import sun.reflect.generics.tree.Tree;

public class BST {

    TreeNode root;

    public BST(TreeNode _root) {
        root = _root;
    }

    public TreeNode insertNode(TreeNode root, TreeNode node) {
        if (root == null) {
            return new TreeNode(node.val);
        } else {
            if (root.val > node.val) {
                root.left = insertNode(root.left, node);
            } else if (root.val < node.val) {
                root.right = insertNode(root.right, node);
            }
            return root;
        }
    }

    public TreeNode search(TreeNode root, TreeNode target) {
        if (root == null) {
            return null;
        }
        if (root.val == target.val) {
            return root;
        } else if (root.val > target.val) {
            return search(root.left, target);
        } else {
            return search(root.right, target);
        }
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null){
            return null;
        }
        else {
            if (root.val > key) {
                root.left = deleteNode(root.left, key);
                return root;
            }
            else if (root.val < key) {
                root.right = deleteNode (root.right, key);
                return root;
            }
            else {
                if (root.left == null) {
                   return root.right;
                }
                else {
                    TreeNode maxNode = new TreeNode(findMax(root.left).val);
                    maxNode.left = deleteMax(root.left);
                    maxNode.right = root.right;
                    return maxNode;
                }
            }
        }
    }

    private TreeNode deleteMax(TreeNode root) {
        if (root.right == null) {
            return root.left;
        }
        else {
            root.right = deleteMax(root.right);
            return root;
        }
    }

    private TreeNode findMax(TreeNode root) {
        if (root.right == null) {
            return root;
        }
        else {
            return findMax(root.right);
        }
    }

    private int binarySearch(int key, int[] array) {
        if(array == null || array.length == 0) {
            return -1;
        }
        else{
            int low = 0;
            int high = array.length - 1;
            while(low < high) {
                int mid = low + (high - low) / 2;
                if (array[mid] < key){
                    low = mid + 1;
                }
                else if (array[mid] > key) {
                    high = mid - 1;
                }
                else {
                    return mid;
                }
            }
            return -1;
        }
    }
}
