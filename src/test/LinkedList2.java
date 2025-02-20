package test;

public class LinkedList2 {
	// 合并两个已排序的链表
	// 如：[{1,2,4}, {1,3,4}] -> {1,1,2,3,4,4}
	// 要求：时间和空间复杂度为O(m+n)，m和n分别为两个链表的长度

	public static void main(String[] args) {
		Node n1 = new Node(1);
		n1.next = new Node(2);
		n1.next.next = new Node(4);

		Node n2 = new Node(1);
		n2.next = new Node(3);
		n2.next.next = new Node(4);

		Node n = merge2(n1, n2);
		println(n);
	}

	private static void println(Node n) {
		while (n != null) {
			System.out.println(n.val);
			n = n.next;
		}
	}

	private static Node merge(Node n1, Node n2) {// 方法1：递归
		if (n1 == null)
			return n2;
		if (n2 == null)
			return n1;
		if (n1.val <= n2.val) {
			n1.next = merge(n1.next, n2);
			return n1;
		} else {
			n2.next = merge(n1, n2.next);
			return n2;
		}
	}

	private static Node merge2(Node n1, Node n2) {// 方法2：迭代
		Node dummy = new Node(0);
		Node curr = dummy;
		while (n1 != null && n2 != null) {
			if (n1.val <= n2.val) {
				Node next = n1.next;
				curr.next = n1;
				// n1.next = n2;
				n1 = next;
			} else {
				Node next = n2.next;
				curr.next = n2;
				// n2.next = n1;
				n2 = next;
			}
			curr = curr.next;
		}
		curr.next = n1 != null ? n1 : n2;
		return dummy.next;
	}

	private static class Node {
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
