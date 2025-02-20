package test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LFUCache {
	private int capacity;

	private Map<Integer, Node> map = new HashMap<Integer, Node>();

	private PriorityQueue<Node> queue = new PriorityQueue<Node>();

	public LFUCache(int capacity) {
		this.capacity = capacity;
	}

	public void put(Integer k, Integer v) {
		Node node = map.get(k);
		if (node != null) {
			node.access += 1;
			queue.remove(node);
			queue.offer(node);
		} else {
			if (queue.size() >= capacity) {
				node = queue.poll();
				map.remove(node.key);
			}
			node = new Node(k, v);
			node.access = 1;
			queue.offer(node);
			map.put(k, node);
		}
	}

	public Integer get(Integer k) {
		Node node = map.get(k);
		if (node != null) {
			node.access += 1;
			queue.remove(node);
			queue.offer(node);
			return node.val;
		} else {
			return null;
		}
	}

	public Integer remove(Integer k) {
		Node node = map.get(k);
		if (node != null) {
			queue.remove(node);
			map.remove(k);
			return node.val;
		} else {
			return null;
		}
	}

	private static class Node implements Comparable<Node> {
		private Integer key;
		private Integer val;
		private int access;

		private Node(Integer key, Integer val) {
			this.key = key;
			this.val = val;
		}

		@Override
		public String toString() {
			return key + "=" + val;
		}

		@Override
		public int compareTo(Node o) {
			return this.access - o.access;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o instanceof Node) {
				Integer key = ((Node) o).key;
				return this.key == key || (this.key != null && this.key.equals(key));
			}
			return false;
		}

	}

	public static void main(String[] args) {
		LFUCache cache = new LFUCache(3);
		cache.put(1, 1);
		cache.put(2, 2);
		cache.put(3, 3);
		System.out.println(cache.get(2));
		System.out.println(cache.get(1));
		cache.put(4, 4);
		System.out.println(cache.map);
	}
}
