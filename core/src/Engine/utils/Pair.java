package Engine.utils;

import java.util.Map;

/**
 * Pair<K , V>
 * I created this to make it easy to store a value that has a one key and one value in a map.
 * This should only be used if the key should contain one value.
 * @author Tyrant's Fire
 *
 * @param <K> is the key 
 * @param <V> is the value
 */
public class Pair<K, V> implements Map.Entry<K, V>{

	private K key;
	private V value;
	
	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public K getKey() {
		return key;
	}
	
	public K setKey(K key) {
		return this.key = key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V value) {
		return this.value = value;
	}
	
}
