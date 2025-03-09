package test;

import java.util.LinkedList;
import java.util.Queue;

// 算法：二叉树遍历
public class BinaryTree {
	public static class TreeNode {
		private int val;
		private TreeNode left;
		private TreeNode right;
		TreeNode(int val) {
			this.val = val;
		}
		@Override
		public String toString() {
			return "TreeNode [val=" + val + ", left=" + left + ", right=" + right + "]";
		}
	}

	public static void breadthFirstSearch(TreeNode root) {// BFS，广度遍历或层次遍历
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			TreeNode node = queue.poll();
			System.out.println(node.val);
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
	}

	public static void depthFirstSearch(TreeNode root) {// DFS，深度遍历
		System.out.println(root.val);
		if (root.left != null) {
			depthFirstSearch(root.left);
		}
		if (root.right != null) {
			depthFirstSearch(root.right);
		}
	}

	public static void main(String[] args) {
		TreeNode node = buildTree();
		breadthFirstSearch(node);
		depthFirstSearch(node);
	}

	private static TreeNode buildTree() {
		TreeNode node = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);
		node.left = left;
		node.right = right;

		TreeNode left2 = new TreeNode(4);
		TreeNode right2 = new TreeNode(5);
		left.left = left2;
		left.right = right2;

		TreeNode right3 = new TreeNode(6);
		right.right = right3;
		return node;
	}
}
