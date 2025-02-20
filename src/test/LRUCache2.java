package test;

import java.util.LinkedHashMap;

public class LRUCache2<K, V> extends LinkedHashMap<K, V> {
	private int capacity;

	public static void main(String[] args) {
		LRUCache2<Integer, String> lru = new LRUCache2<Integer, String>(3);
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
	}

	public LRUCache2(int capacity) {
		super(16, 0.75f, true);
		this.capacity = capacity;
	}

	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
		return size() > capacity;
	}
}
