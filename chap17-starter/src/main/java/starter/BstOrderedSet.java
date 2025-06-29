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

  /**
   * Driver program to visualize/test this implementation.
   *
   * @param args command-line arguments.
   */
  public static void main(String[] args) {
    BstOrderedSet<Integer> bst = new BstOrderedSet<>();
    bst.insert(7);
    bst.insert(2);
    bst.insert(13);
    bst.insert(4);
    bst.insert(10);
    bst.insert(15);
    bst.insert(5);
    bst.insert(8);
    bst.insert(11);
    bst.insert(14);
    bst.insert(17);

    bst.remove(5);
    bst.remove(2);
    bst.remove(13);

    bst.printInOrder();

    for (Integer i : bst) {
      System.out.println(i);
    }
  }

  /* recursive find */
  private Node<T> find(Node<T> node, T data) {
    if (node == null) {
      return null;
    }
    if (node.data.compareTo(data) > 0) {
      return find(node.left, data);
    } else if (node.data.compareTo(data) < 0) {
      return find(node.right, data);
    } else {
      return node;
    }
  }

  @Override
  public void insert(T t) {
    // Uses a recursive (private) helper insert
    root = insert(root, t);
  }

  /* inserts given value into subtree rooted at given node
      and returns changed subtree with new node added. */
  private Node<T> insert(Node<T> node, T data) {
    if (node == null) {
      /* If the subtree is empty, return a new node */
      numElements++;
      return new Node<>(data);
    }

    /* Otherwise, recur down the tree */
    if (node.data.compareTo(data) > 0) {
      node.left = insert(node.left, data);
    } else if (node.data.compareTo(data) < 0) {
      node.right = insert(node.right, data);
    }

    /* return the (unchanged) node pointer */
    return node;
  }

  @Override
  public void remove(T t) {
    // TODO Implement me!
  }

  @Override
  public boolean has(T t) {
    return find(root, t) != null;
  }

  @Override
  public int size() {
    return numElements;
  }

  public void printInOrder() {
    printInOrder(root);
  }

  private void printInOrder(Node<T> node) {
    if (node == null) {
      return;
    }
    // simply change to root, left, right or left, right, root for pre or post order
    printInOrder(node.left);
    System.out.println(node.data);
    printInOrder(node.right);
  }

  @Override
  public Iterator<T> iterator() {
    // TODO Implement me!
    return null;
  }

  private class BstIterator implements Iterator<T> {

    T[] ordered;
    int length;
    int index;

    public BstIterator() {
      ordered = (T[]) new Object[numElements];
      length = 0;
      fillArray(root);
      index = 0;
    }

    @Override
    public boolean hasNext() {
      if (index < length) {
        return true;
      }
      return false;
    }

    public void fillArray(Node<T> node) {
      if (node == null) {
        return;
      }
      fillArray(node.left);
      ordered[length] = node.data;
      length++;
      fillArray(node.right);
    }

    @Override
    public T next() {
      if (hasNext()) {
        index++;
        return ordered[index - 1];
      }
      throw new IndexOutOfBoundsException();
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
}
