package test;

import java.util.HashMap;
import java.util.Map;

public class LinkedList5 {
	// 算法1：删除有序链表中重复的元素
	// 如：{-1,1,1,2,2,3} -> {-1,1,2,3}
	// 要求：时间复杂度为O(n)，空间复杂度为O(1)
	public static Node removeDuplicated(Node n1) {// 算法1
		Node head = n1;
		Node prev = null;
		while (head != null) {
			if (prev != null && head.val == prev.val) {
				prev.next = head.next;
				head = head.next;
			} else {
				prev = head;
				head = head.next;
			}
		}
		return n1;
	}

	public static void main(String[] args) {
		Node n1 = toNode(-1, 1);

		// Node n2 = removeDuplicated(n1);
		Node n2 = removeDuplicated21(n1);
		println(n2);
	}

	// 算法2：删除有序链表中重复的元素，只保留出现一次的元素
	// 如：{-1,1,1,2,2,3} -> {-1,3}
	// 要求：时间复杂度为O(n)，空间复杂度为O(n)。进阶：空间复杂度为O(1)
	public static Node removeDuplicated2(Node n1) {// 算法2：空间复杂度O(n)
		if (n1 == null || n1.next == null)
			return n1;

		// 如：{-1,1,1,2,2,3} -> {-1,3}
		// 如：{1,1,2,2,3} -> {3}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Node head = n1;
		while (head != null) {
			Integer count = map.get(head.val);
			if (count == null) {
				map.put(head.val, count = 1);
			} else {
				map.put(head.val, count + 1);
			}
			head = head.next;
		}
		Node dummy = new Node(0);
		Node curr = dummy;
		head = n1;
		while (head != null) {
			Integer count = map.get(head.val);
			if (count > 1) {
				head = head.next;
			} else {
				curr.next = new Node(head.val);
				curr = curr.next;
				head = head.next;
			}
		}
		return dummy.next;
	}

	public static Node removeDuplicated21(Node n1) {// 算法2：空间复杂度O(1)
		if (n1 == null || n1.next == null)
			return n1;

		Node dummy = new Node(0);
		dummy.next = n1;
		Node curr = n1;
		Node prev = dummy;
		while (curr != null) {
			if (curr.next != null && curr.next.val == curr.val) {
				int duplicatedVal = curr.val;
				while (curr != null && curr.val == duplicatedVal) {// 这里整体的时间复杂度为O(n)
					curr = curr.next;
				}
				prev.next = curr;
			} else {
				prev = curr;
				curr = curr.next;
			}
		}
		return dummy.next;
	}

	private static Node toNode(int... values) {
		Node dummy = new Node(0);
		Node curr = dummy;
		for (int val : values) {
			curr.next = new Node(val);
			curr = curr.next;
		}
		return dummy.next;
	}

	private static void println(Node n) {
		while (n != null) {
			System.out.println(n.val);
			n = n.next;
		}
	}

	public static class Node {
		private int val;
		private Node next;

		private Node(int val) {
			this.val = val;
		}

		@Override
		public String toString() {
			return "" + val;
		}
	}
}
