package hw5;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OpenAddressingHashMap<K, V> implements Map<K, V> {
  private int capacity;
  private int numElements;
  private Node<K, V>[] map;
  private final double LOAD_REHASH = 0.75;
  private final int[] PRIMES =
          {5, 11, 23, 47, 97, 197, 397, 797, 1597, 3203, 6421, 12853, 25717, 51437,102877, 205759,
                  411527, 823117, 1646237,3292489, 6584983, 13169977};
  private int primeIdx;

  /**
   * OpenAddressingHashMap constructor
   * Sets initial capacity and initialized map.
   */
  public OpenAddressingHashMap() {
    primeIdx = 0;
    capacity = PRIMES[primeIdx];
    map = (Node<K, V>[]) (Array.newInstance(Node.class, capacity));
  }

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException("key is null");
    } else if (has(k)) {
      throw new IllegalArgumentException("key is already mapped");
    }

    int idx;
    Node<K, V> n = new Node<>(k, v);
    for (int i = 0; i < capacity; i++) {
      idx = (getIndex(k) + i * i) % capacity;
      if (map[idx] == null) {
        map[idx] = n;
        break;
      }
    }

    numElements++;

    if (loadFactor() >= LOAD_REHASH) {
      rehash();
    }
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException("key is null");
    }

    Node<K, V> toRemove = find(k);
    // throw err if not found
    if (toRemove == null || toRemove.dead) {
      throw new IllegalArgumentException("key is not mapped");
    }

    // remove and return value
    toRemove.dead = true;
    numElements--;
    return toRemove.value;
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException("key is null");
    }
    Node<K, V> n = find(k);
    if (n == null) {
      throw new IllegalArgumentException("key is not mapped");
    }
    n.value = v;
  }

  @Override
  public V get(K k) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException("key is null");
    }
    Node<K, V> n = find(k);
    if (n == null) {
      throw new IllegalArgumentException("key is not mapped");
    }
    return n.value;
  }

  @Override
  public boolean has(K k) {
    return find(k) != null;
  }

  private Node<K, V> find(K k) {
    int idx;
    // if table[index] is occupied, then
    for (int i = 0; i < capacity; i++) {
      idx = (getIndex(k) + i * i) % capacity;
      if (map[idx] != null && map[idx].key == k) {
        if (map[idx].dead) {
          continue;
        }
        return map[idx];
      }
    }
    return null;
  }

  @Override
  public int size() {
    return numElements;
  }

  // Calculate the index of a key by hashing
  private int getIndex(K k) {
    return k.hashCode() % capacity;
  }

  // Calculate the current load factor
  private double loadFactor() {
    return (double) numElements / capacity;
  }

  // Resize and rehash entries in map
  private void rehash() {
    // either go to next prime sizing or double when finished
    if (primeIdx < PRIMES.length - 1) {
      primeIdx++;
      capacity = PRIMES[primeIdx];
    } else {
      capacity *= 2;
    }

    // save reference to old map and create new with updated capacity
    Node<K, V>[] oldMap = map;
    map = (Node<K, V>[]) (Array.newInstance(Node.class, capacity));
    numElements = 0;

    // fill in resized map by rehashing entries
    for (Node<K, V> n : oldMap) {
      if (n == null) {
        continue;
      }
      insert(n.key, n.value);
    }
  }

  @Override
  public Iterator<K> iterator() {
    return new OpenAddressingHashMapIterator();
  }

  private static class Node<K, V> {
    K key;
    V value;
    boolean dead;

    // Constructor to make node creation easier to read.
    Node(K k, V v) {
      key = k;
      value = v;
      dead = false;
    }

    @Override
    public String toString() {
      return key + ":" + value;
    }
  }

  private class OpenAddressingHashMapIterator implements Iterator<K> {
    int index;

    private OpenAddressingHashMapIterator() {
      index = 0;
      while (index < map.length
              && (map[index] == null || map[index].dead)) {
        index++;
      }
    }

    @Override
    public boolean hasNext() {
      return index < map.length;
    }

    @Override
    public K next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      Node<K, V> n = map[index];
      do {
        index++;
      } while (index < map.length
              && (map[index] == null || map[index].dead));
      return n.key;
    }
  }
}
