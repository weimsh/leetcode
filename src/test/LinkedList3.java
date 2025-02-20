package test;

public class LinkedList3 {
	// 两个链表相加
	// 如：[{9,3,7}, {6,3}] -> {1,0,0,0}
	// 要求：时间和空间复杂度为O(n)

	public static void main(String[] args) {
		Node n1 = new Node(9);
		n1.next = new Node(3);
		n1.next.next = new Node(7);
		Node n2 = new Node(6);
		n2.next = new Node(3);
		Node n = sum(n1, n2);
		n = reverse(n);
		println(n);
	}

	private static void println(Node n) {
		while (n != null) {
			System.out.println(n.val);
			n = n.next;
		}
	}

	private static Node sum(Node n1, Node n2) {
		n1 = reverse(n1);
		n2 = reverse(n2);
		// println(n1);
		// println(n2);

		Node dummy = new Node(0);
		Node curr = dummy;
		int carry = 0;
		while (n1 != null || n2 != null || carry != 0) {
			int sum = (n1 == null ? 0 : n1.val) + (n2 == null ? 0 : n2.val) + carry;
			curr.next = new Node(sum % 10);
			carry = sum / 10;
			curr = curr.next;
			n1 = n1 == null ? null : n1.next;
			n2 = n2 == null ? null : n2.next;
		}
		return dummy.next;
	}

	private static Node reverse(Node n1) {
		Node prev = null;
		Node curr = n1;
		while (curr != null) {
			Node next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
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
