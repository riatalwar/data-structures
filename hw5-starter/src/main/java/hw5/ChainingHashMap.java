package hw5;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ChainingHashMap<K, V> implements Map<K, V> {

  private int capacity;
  private int numElements;
  private LinkedList<Node<K, V>>[] map;

  public ChainingHashMap() {
    capacity = 13;
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
    Node<K, V> toRemove = null;

    // search for key
    for (Node<K, V> n : bucket) {
      if (n.key == k) {
        toRemove = n;
      }
    }

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
    return find(k) != null;
  }

  private Node<K, V> find(K k) {
    // get bucket based on hash
    int idx = getIndex(k);
    LinkedList<Node<K, V>> bucket = map[idx];
    if (bucket == null) {
      return null;
    }

    // search bucket for key
    for (Node<K, V> n : bucket) {
      if (n.key == k) {
        return n;
      }
    }

    return null;
  }

  @Override
  public int size() {
    return numElements;
  }

  private int getIndex(K k) {
    return k.hashCode() % capacity;
  }

  private double loadFactor() {
    return (double) numElements / capacity;
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
      while (index < capacity && map[index] == null) {
        index++;
      }
      if (index < capacity && map[index] != null) {
        bucketIt = map[index].iterator();
      }
    }

    @Override
    public boolean hasNext() {
      return index < capacity && bucketIt.hasNext();
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
        } while (index < capacity && map[index] == null);

        // get next iterator
        if (index < capacity && map[index] != null) {
          bucketIt = map[index].iterator();
        }
      }
      return k;
    }
  }
}
