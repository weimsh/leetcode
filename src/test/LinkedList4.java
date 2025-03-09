package test;

import java.util.PriorityQueue;

public class LinkedList4 {
	// 实现单链表排序
	// 如：{-1,0,3,1,2} -> {-1,0,1,2,3}
	// 要求：时间复杂度为O(nlogn)，空间复杂度为O(n)或者O(logn)。提示使用归并排序或者堆排序
	public static void main(String[] args) {
		Node n1 = new Node(-1);
		n1.next = new Node(0);
		n1.next.next = new Node(3);
		n1.next.next.next = new Node(1);
		n1.next.next.next.next = new Node(2);

		Node n2 = sort2(n1);
		println(n2);
	}

	private static void println(Node n) {
		while (n != null) {
			System.out.println(n.val);
			n = n.next;
		}
	}

	public static Node sort(Node n1) {// 堆排序
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		while (n1 != null) {
			queue.offer(n1);
			n1 = n1.next;
		}
		Node dummy = new Node(0);
		Node curr = dummy;
		while (!queue.isEmpty()) {
			Node next = queue.poll();
			curr.next = new Node(next.val);
			curr = curr.next;
		}
		return dummy.next;
	}

	public static Node sort2(Node n1) {// 归并排序
		if (n1 == null || n1.next == null) {
			return n1;
		}

		// 使用快慢指针查找链表的中间节点
		Node slow = n1;
		Node fast = n1.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;// 每次移动一步
			fast = fast.next.next;// 每次移动两步
		}
		// 此时中间节点即为slow，但为了断开链表需要后移一位
		Node mid = slow.next;
		slow.next = null;

		Node left = sort2(n1);
		Node right = sort2(mid);
		return merge(left, right);
	}

	private static Node merge(Node n1, Node n2) {// 合并两个有序链表
		if (n1 == null)
			return n2;
		if (n2 == null)
			return n1;
		Node dummy = new Node(0);
		Node curr = dummy;
		while (n1 != null && n2 != null) {
			if (n1.val <= n2.val) {
				curr.next = n1;
				n1 = n1.next;
			} else {
				curr.next = n2;
				n2 = n2.next;
			}
			curr = curr.next;
		}
		curr.next = n1 != null ? n1 : n2;
		return dummy.next;
	}

	public static class Node implements Comparable<Node> {
		private int val;
		private Node next;

		private Node(int val) {
			this.val = val;
		}

		@Override
		public String toString() {
			return "" + val;
		}

		@Override
		public int compareTo(Node o) {
			return this.val - o.val;
		}
	}
}
