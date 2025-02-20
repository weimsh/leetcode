package test;

import java.util.PriorityQueue;

public class LinkedList {
	// 合并k个已排序的链表
	// 如：[{1,2,3}, {4,5,6,7}] -> {1,2,3,4,5,6,7}
	// 如：[{1,2}, {1,4,5}, {6}] -> {1,1,2,4,5,6}
	// 要求：时间复杂度为O(nlogn)，提示用最小堆

	public static void main(String[] args) {
		Node[] nodes = new Node[3];
		Node n1 = new Node(1);
		n1.next = new Node(2);
		Node n2 = new Node(1);
		n2.next = new Node(4);
		n2.next.next = new Node(5);
		Node n3 = new Node(6);
		nodes[0] = n1;
		nodes[1] = n2;
		nodes[2] = n3;
		Node n = merge(nodes);
		while (n != null) {
			System.out.println(n.val);
			n = n.next;
		}
	}

	private static Node merge(Node[] nodes) {
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		for (Node node : nodes) {
			queue.offer(node);
		}
		Node head = new Node(-1);
		Node curr = head;
		while (!queue.isEmpty()) {
			System.out.println("before: " + queue);
			Node n = queue.poll();
			System.out.println("after: " + queue);
			if (n.next != null) {
				queue.offer(n.next);
			}
			curr.next = n;
			curr = curr.next;
		}
		return head.next;
	}

	private static class Node implements Comparable<Node> {
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
