package com.alogrithm;


import com.alogrithm.util.TreeNode;
import com.alogrithm.util.UtilTool;
import jdk.jshell.execution.Util;

import java.util.*;

public class Solution {
    final Random random = new Random();
    final int MOD = (int) 1e9 + 7;

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }

        TreeNode leftNode = searchBST(root.left, val);
        if (leftNode != null) return leftNode;
        TreeNode rightNode = searchBST(root.right, val);
        if (rightNode != null) return rightNode;
        return null;
    }


    public void run() {
        String input = "4,2,1,3,None,None,None,None,7,None,None,";
        final TreeNode deserialize = UtilTool.deserialize(input);
        long before = System.currentTimeMillis();
        final TreeNode treeNode = searchBST(deserialize, 4);
        UtilTool.bfsPrintTree(treeNode);
        System.out.println("耗时" + (System.currentTimeMillis() - before) + "ms");
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.run();
    }

}
