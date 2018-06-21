package com.roland.shuzu;

import java.util.HashMap;
import java.util.Map;

public class BuildBinaryTree {

     public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

     public static void main(String[] args){
         int[] a = {5,3,2,1,4,7,6,8};
         int[] b = {1,2,3,4,5,6,7,8};
         buildTree(a,b);
     }


    static Map<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i = 0; i < inorder.length; i++){
            inorderMap.put(inorder[i], i);
        }
        return buildTree(0, 0, inorder.length - 1, preorder, inorder);
    }

    public static TreeNode buildTree(int preIndex, int inStart, int inEnd, int[] preorder, int[] inorder){
        if(inStart > inEnd || preIndex == preorder.length){
            return null;
        }
        int rootInorderIndex = inorderMap.get(preorder[preIndex]);
        TreeNode root = new TreeNode(inorder[rootInorderIndex]);
        root.left = buildTree(preIndex+1, inStart, rootInorderIndex-1, preorder, inorder);
        root.right = buildTree(rootInorderIndex+1,rootInorderIndex+1, inEnd, preorder, inorder);
        return root;
    }
}

