package com.example.algorithmadvanced.tree;

public class MaxPathSum {

    public static void main(String[] args) {

    }

    class TreeNode{
        public TreeNode left;
        public TreeNode right;
        public int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public int maxPathSum(TreeNode root){
        // 如果最大值是负数，我们选择不选
        int left = Math.max(helper(root.left),0);
        int right = Math.max(helper(root.right),0);
        return root.val+left+right;
    }

    public int helper(TreeNode childNode){
        if (childNode == null){
            return 0;
        }
        int left = Math.max(helper(childNode.left),0);
        int right = Math.max(helper(childNode.right),0);
        // 选择左子树和右子树中值最大的那一个
        return childNode.val+Math.max(left,right);
    }
}
