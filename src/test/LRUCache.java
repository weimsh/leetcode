package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LRUCache<K, V> {
	// 要求以HashMap和双向链表实现，并且put和get的时间复杂度均为O(1)
	private int capacity;
	private Node head;
	private Node tail;
	private int size;
	private Map<K, Node> map = new HashMap<K, Node>();

	@Override
	public String toString() {
		Node node = head != null ? head.next : null;
		List<V> list = new ArrayList<V>();
		while (node != null && node != tail) {
			list.add(node.value);
			node = node.next;
		}
		return list.toString();
	}

	public static void main(String[] args) {
		LRUCache<Integer, String> lru = new LRUCache<Integer, String>(3);
		lru.put(1, "Node1");
		System.out.println(lru);
		lru.put(2, "Node2");
		System.out.println(lru);
		lru.put(3, "Node3");
		System.out.println(lru);
		System.out.println(lru.get(2));
		System.out.println(lru);
		lru.put(4, "Node4");
		System.out.println(lru);
		System.out.println(lru.size());
		System.out.println(lru.map);
	}

	private class Node {
		private Node prev;
		private Node next;
		private K key;
		private V value;

		private Node(K key, V value, Node prev, Node next) {
			this.key = key;
			this.value = value;
			this.prev = prev;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [key=" + key + ", value=" + value + "]";
		}
	}

	public LRUCache(int capacity) {
		this.capacity = capacity;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public V get(Object key) {
		Node node = map.get(key);
		if (node != null) {
			moveToTail(node);
			return node.value;
		}
		return null;
	}

	private void moveToTail(Node node) {
		if (node.prev != null) {
			node.prev.next = node.next;
		}
		if (node.next != null) {
			node.next.prev = node.prev;
		}

		if (tail != null && tail.prev != null) {
			tail.prev.next = node;
			node.prev = tail.prev;
			node.next = tail;
			tail.prev = node;
		}

		if (tail == null) {
			tail = new Node(null, null, null, null);
			tail.prev = node;
			node.next = tail;
		}

		if (head == null) {
			head = new Node(null, null, null, null);
			head.next = node;
			node.prev = head;
		}
	}

	public V put(K key, V value) {
		Node node = map.get(key);
		V oldValue = null;
		if (node != null) {
			oldValue = node.value;
			node.value = value;
			moveToTail(node);
		} else {
			size++;
			node = new Node(key, value, null, null);
			map.put(key, node);
			moveToTail(node);
			if (size > capacity) {
				removeFromHead();
			}
		}
		return oldValue;
	}

	private void removeFromHead() {
		if (head != null && head.next != null) {
			removeNode(head.next);
		}
	}

	public V remove(Object key) {
		Node node = map.get(key);
		V oldValue = null;
		if (node != null) {
			oldValue = node.value;
			removeNode(node);
		}
		return oldValue;
	}

	private void removeNode(Node node) {
		if (node == null)
			return;
		if (node.prev != null) {
			node.prev.next = node.next;
		}
		if (node.next != null) {
			node.next.prev = node.prev;
		}
		map.remove(node.key);
		node = null;
		size--;
	}

	public void clear() {
		map.clear();
		size = 0;
		head = tail = null;
	}
}
