package hw4;

import java.util.Iterator;

/**
 * Map implemented as an AVL Tree.
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class AvlTreeMap<K extends Comparable<K>, V> implements OrderedMap<K, V> {

  /*** Do not change variable name of 'root'. ***/
  private Node<K, V> root;

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException("cannot handle null key");
    }

    root = insert(root, k, v);
    size++;
    root = rebalance(root);
  }

  private Node<K, V> insert(Node<K, V> n, K k, V v) {
    if (n == null) {
      return new AvlTreeMap.Node<>(k, v);
    }

    int cmp = k.compareTo(n.key);
    if (cmp < 0) {
      n.left = insert(n.left, k, v);
    } else if (cmp > 0) {
      n.right = insert(n.right, k, v);
    } else {
      throw new IllegalArgumentException("duplicate key " + k);
    }

    return n;
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    // TODO Implement Me!
    return null;
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    // TODO Implement Me!
    Node<K, V> n = findForSure(k);
    n.value = v;
  }

  @Override
  public V get(K k) throws IllegalArgumentException {
    Node<K, V> n = find(k);
    if (n != null) {
      return n.value;
    }
    return null;
  }

  @Override
  public boolean has(K k) {
    if (k == null) {
      return false;
    }
    return find(k) != null;
  }

  private Node<K, V> find(K k) {
    if (k == null) {
      throw new IllegalArgumentException("cannot handle null key");
    }

    return find(root, k);
  }

  private Node<K, V> find(Node<K, V> n, K k) {
    if (n == null) {
      return null;
    }

    int cmp = k.compareTo(n.key);
    if (cmp < 0) {
      return find(n.left, k);
    } else if (cmp > 0) {
      return find(n.right, k);
    } else {
      return n;
    }
  }

  private Node<K, V> findForSure(K k) {
    Node<K, V> n = find(k);
    if (n == null) {
      throw new IllegalArgumentException("cannot find key " + k);
    }
    return n;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<K> iterator() {
    return new AvlTreeMap.InorderIterator();
  }

  // Iterative in-order traversal over the keys
  // TODO: do we have to write tests for the iterator given that it is copied?
  private class InorderIterator implements Iterator<K> {
    private final Stack<AvlTreeMap.Node<K, V>> stack;

    InorderIterator() {
      stack = new Stack<>();
      pushLeft(root);
    }

    private void pushLeft(AvlTreeMap.Node<K, V> curr) {
      while (curr != null) {
        stack.push(curr);
        curr = curr.left;
      }
    }

    @Override
    public boolean hasNext() {
      return !stack.isEmpty();
    }

    @Override
    public K next() {
      AvlTreeMap.Node<K, V> top = stack.pop();
      pushLeft(top.right);
      return top.key;
    }
  }

  /*** Do not change this function's name or modify its code. ***/
  @Override
  public String toString() {
    return BinaryTreePrinter.printBinaryTree(root);
  }

  /**
   * Inner node class, each holds a key (which is what we sort the
   * BST by) as well as a value. We don't need a parent pointer as
   * long as we use recursive insert/remove helpers.
   *
   * @param <K> Type for keys.
   * @param <V> Type for values.
   **/
  private static class Node<K, V> implements BinaryTreeNode {
    Node<K, V> left;
    Node<K, V> right;
    K key;
    V value;

    // Constructor to make node creation easier to read.
    Node(K k, V v) {
      // left and right default to null
      key = k;
      value = v;
    }

    @Override
    public String toString() {
      return key + ":" + value;
    }

    @Override
    public BinaryTreeNode getLeftChild() {
      return left;
    }

    @Override
    public BinaryTreeNode getRightChild() {
      return right;
    }

    // Feel free to add whatever you want to the Node class (e.g. new fields).
    // Just avoid changing any existing names, deleting any existing variables, or modifying the overriding methods.
  }
}
