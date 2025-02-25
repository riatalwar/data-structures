package starter;

import java.util.Iterator;

/**
 * A Binary Search Tree implementation of OrderedSet ADT.
 *
 * @param <T> Element type.
 */
public class BstOrderedSet<T extends Comparable<T>> implements OrderedSet<T> {

  private Node<T> root;
  private int numElements;

  /**
   * Construct an empty BstOrderedSet.
   */
  public BstOrderedSet() {
    root = null;
    numElements = 0;
  }

  @Override
  public void insert(T t) {
    // TODO Implement me!
    Node<T> n = new Node<>(t);
    if (root == null) {
      root = n;
      numElements++;
      return;
    }
    Node<T> cur = root;
    while (!t.equals(cur.data)) {
      if (t.compareTo(cur.data) < 0) {
        if (cur.left == null) {
          cur.left = n;
          numElements++;
          return;
        } else {
          cur = cur.left;
        }
      } else {
        if (cur.right == null) {
          cur.right = n;
          numElements++;
          return;
        } else {
          cur = cur.right;
        }
      }
    }
  }

  @Override
  public void remove(T t) {
    // TODO Implement me!
  }

  @Override
  public boolean has(T t) {
    // TODO Implement me!
    Node<T> cur = root;
    while (cur != null) {
      if (t.equals(cur.data)) {
        return true;
      }
      if (t.compareTo(cur.data) < 0) {
        cur = cur.left;
      } else {
        cur = cur.right;
      }
    }
    return false;

    // return has(t, root);
  }

  private boolean has(T t, Node<T> root) {
    if (t.equals(root.data)) {
      return true;
    } else if (t.compareTo(root.data) < 0) {
      return has(t, root.left);
    } else {
      return has(t, root.right);
    }
  }

  @Override
  public int size() {
    // TODO Implement me!
    return numElements;
  }

  @Override
  public Iterator<T> iterator() {
    // TODO Implement me!
    return null;
  }

  private class BSTIterator implements Iterator{
    Node<T> tree;

    public BSTIterator () {
      tree = root;
    }

    @Override
    public boolean hasNext() {
      return false;
    }

    @Override
    public Object next() {
      return null;
    }
  }

  private static class Node<E> {
    E data;
    Node<E> left;
    Node<E> right;

    Node(E data) {
      this.data = data;
    }
  }

  /**
   * Driver program to visualize/test this implementation.
   *
   * @param args command-line arguments.
   */
  public static void main(String[] args) {
    BstOrderedSet<Integer> bst = new BstOrderedSet<>();
    bst.insert(7);
    bst.insert(4);
    bst.insert(13);
    bst.insert(1);
    bst.insert(6);
    bst.insert(10);
    bst.insert(15);
    bst.insert(3);

    System.out.println(bst.size());
    System.out.println(bst.has(13));
    System.out.println(bst.has(23));
  }
}
