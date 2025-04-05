package hw5;

import org.apache.commons.math3.primes.Primes;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OpenAddressingHashMap<K, V> implements Map<K, V> {
  private int capacity;
  private int slotsFilled;
  private int numElements;
  private Node<K, V>[] map;
  private final double loadRehash = 0.75;
  private final int[] primes =
      {3, 5, 11, 23, 47, 97, 197, 397, 797, 1597, 3203, 6421, 12853, 25717, 51437,102877, 205759,
          411527, 823117, 1646237,3292489, 6584983, 13169977};
  private int primeIdx;

  /**
   * OpenAddressingHashMap constructor
   * Sets initial capacity and initialized map.
   */
  public OpenAddressingHashMap() {
    primeIdx = 1;
    capacity = primes[primeIdx];
    map = (Node<K, V>[]) (Array.newInstance(Node.class, capacity));
  }

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException("key is null");
    }

    // insert key at calculated index
    int idx = getInsertIndex(k);
    map[idx] = new Node<>(k, v);

    numElements++;
    slotsFilled++;

    // if threshold passed rehash map
    if (loadFactor() >= loadRehash) {
      rehash();
    }
  }

  // helper function to determine where to insert key
  private int getInsertIndex(K k) {
    int idx;
    int firstTomb = capacity;

    // double hash probe through map
    for (int i = 0; i < capacity; i++) {
      idx = (getIndex(k) + i * doubleHash(k)) % capacity;

      // end of search, key not found
      if (map[idx] == null) {
        // tomb replacement if encountered
        if (firstTomb != capacity) {
          idx = firstTomb;
          slotsFilled--;
        }
        return idx;
      // key already mapped
      } else if (k.equals(map[idx].key) && !map[idx].dead) {
        throw new IllegalArgumentException("key is already mapped");
      // record first tomb
      } else if (firstTomb == capacity
              && map[idx].dead) {
        firstTomb = idx;
      }
    }
    return -1;
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
    if (k == null) {
      return false;
    }
    return find(k) != null;
  }

  private Node<K, V> find(K k) {
    int idx;
    // double hash probe until found
    for (int i = 0; i < capacity; i++) {
      idx = (getIndex(k) + i * doubleHash(k)) % capacity;
      if (map[idx] != null
              && k.equals(map[idx].key)
              && !map[idx].dead) {
        return map[idx];
      }
    }
    // not found returns null
    return null;
  }

  @Override
  public int size() {
    return numElements;
  }

  // Calculate the index of a key by hashing
  private int getIndex(K k) {
    return Math.abs(k.hashCode()) % capacity;
  }

  // Calculate a second hash value for probing
  private int doubleHash(K k) {
    int q = primes[primeIdx - 1];
    return q - Math.abs(k.hashCode()) % q;
  }

  // Calculate the current load factor
  private double loadFactor() {
    return (double) slotsFilled / capacity;
  }

  // Resize and rehash entries in map
  private void rehash() {
    // either go to next prime sizing or double when finished
    if (primeIdx < primes.length - 1) {
      primeIdx++;
      capacity = primes[primeIdx];
    } else {
      capacity *= 2;
    }

    // save reference to old map and create new with updated capacity
    numElements = 0;
    slotsFilled = 0;
    Node<K, V>[] oldMap = map;
    map = (Node<K, V>[]) (Array.newInstance(Node.class, capacity));

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
      // find first occupied index
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

      // locate next occupied index
      do {
        index++;
      } while (index < map.length
              && (map[index] == null || map[index].dead));

      return n.key;
    }
  }
}
