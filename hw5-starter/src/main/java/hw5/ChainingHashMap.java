package hw5;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ChainingHashMap<K, V> implements Map<K, V> {

  private int capacity;
  private int numElements;
  private LinkedList<Node<K, V>>[] map;
  private final double LOAD_REHASH = 0.75;
  private final int[] PRIMES =
      {5, 11, 23, 47, 97, 197, 397, 797, 1597, 3203, 6421, 12853, 25717, 51437,102877, 205759,
       411527, 823117, 1646237,3292489, 6584983, 13169977};
  private int primeIdx;


  /**
   * ChainingHashMap constructor
   * Sets initial capacity and initialized map.
   */
  public ChainingHashMap() {
    primeIdx = 0;
    capacity = PRIMES[primeIdx];
    map = (LinkedList<Node<K, V>>[]) (Array.newInstance(LinkedList.class, capacity));
  }

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException("key is null");
    } else if (has(k)) {
      throw new IllegalArgumentException("key is already mapped");
    }

    int idx = getIndex(k);
    if (map[idx] == null) {
      map[idx] = new LinkedList<>();
    }
    Node<K, V> n = new Node<>(k, v);
    map[idx].add(n);

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

    // get bucket for key
    int idx = getIndex(k);
    LinkedList<Node<K, V>> bucket = map[idx];
    if (bucket == null) {
      throw new IllegalArgumentException("key is not mapped");
    }
    Node<K, V> toRemove = findInBucket(bucket, k);

    // throw err if not found
    if (toRemove == null) {
      throw new IllegalArgumentException("key is not mapped");
    }

    // remove and return value
    bucket.remove(toRemove);
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
    // get bucket based on hash
    int idx = getIndex(k);
    LinkedList<Node<K, V>> bucket = map[idx];
    if (bucket == null) {
      return null;
    }

    return findInBucket(bucket, k);
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

  // Determine if key is in bucket and return corresponding node if found
  private Node<K, V> findInBucket(LinkedList<Node<K, V>> bucket, K k) {
    for (Node<K, V> n : bucket) {
      if (k.equals(n.key)) {
        return n;
      }
    }

    return null;
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
    LinkedList<Node<K, V>>[] oldMap = map;
    map = (LinkedList<Node<K, V>>[]) (Array.newInstance(LinkedList.class, capacity));
    numElements = 0;

    // fill in resized map by rehashing entries
    for (LinkedList<Node<K, V>> bucket : oldMap) {
      if (bucket == null) {
        continue;
      }
      for (Node<K, V> n : bucket) {
        insert(n.key, n.value);
      }
    }
  }

  @Override
  public Iterator<K> iterator() {
    return new ChainingHashMapIterator();
  }

  private static class Node<K, V> {
    K key;
    V value;

    // Constructor to make node creation easier to read.
    Node(K k, V v) {
      key = k;
      value = v;
    }

    @Override
    public String toString() {
      return key + ":" + value;
    }
  }

  private class ChainingHashMapIterator implements Iterator<K> {
    private int index;
    private Iterator<Node<K, V>> bucketIt;

    ChainingHashMapIterator() {
      index = 0;
      // find first full bucket
      while (index < capacity && (map[index] == null
              || (map[index] != null && map[index].isEmpty()))) {
        index++;
      }
      if (index < capacity) {
        bucketIt = map[index].iterator();
      }
    }

    @Override
    public boolean hasNext() {
      if (index >= capacity) {
        return false;
      }
      return bucketIt != null && bucketIt.hasNext();
    }

    @Override
    public K next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      K k = bucketIt.next().key;

      // if end of bucket go to next
      if (!bucketIt.hasNext()) {
        // skip over empty buckets
        do {
          index++;
        } while (index < capacity && (map[index] == null
                || (map[index] != null && map[index].isEmpty())));

        // get next iterator
        if (index < capacity) {
          bucketIt = map[index].iterator();
        }
      }
      return k;
    }
  }
}
