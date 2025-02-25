package test;

public class LinkedList6 {
	// 算法：给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点
	// 要求：时间复杂度为O(n)，空间复杂度为O(1)。

	public static Node toNode(int... values) {
		Node dummy = new Node(0);
		Node curr = dummy;
		for (int val : values) {
			curr.next = new Node(val);
			curr = curr.next;
		}
		return dummy.next;
	}

	public static void main(String[] args) {
		Node n1 = toNode(1, 2, 3, 4, 5);
		//Node n3 = reverse(n1);
		//println(n3);

		Node n2 = swap2(n1);
		println(n2);
	}

	private static Node reverse(Node n) {
		Node curr = n;
		Node prev = null;
		while (curr != null) {
			Node next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}

	private static void println(Node n) {
		while (n != null) {
			System.out.println(n.val);
			n = n.next;
		}
	}

	private static Node swap(Node n1) {// 时间复杂度O(n)，空间复杂度O(n)
		Node dummy = new Node(0);
		Node curr = dummy;

		Node slow = n1;
		Node fast = n1.next;
		while (slow != null && fast != null) {
			Node node = new Node(fast.val);
			node.next = new Node(slow.val);
			curr.next = node;
			curr = node.next;
			slow = slow.next != null ? slow.next.next : null;
			fast = fast.next != null ? fast.next.next : null;
		}
		curr.next = slow != null ? slow : null;
		return dummy.next;
	}

	private static Node swap2(Node n1) {// 时间复杂度O(n)，空间复杂度O(1)
		Node dummy = new Node(0);
		dummy.next = n1;
		Node pre = dummy;
		Node cur = n1;
		while (cur != null && cur.next != null) {
			Node t = cur.next;
			cur.next = t.next;
			t.next = cur;
			
			pre.next = t;
			pre = cur;
			cur = cur.next;
		}
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
