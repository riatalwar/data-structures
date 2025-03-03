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
    // insert(n);
    insert(n, root);
  }

  private void insert(Node<T> n) { // iterative
    Node<T> cur = root;
    while (!n.data.equals(cur.data)) {
      if (n.data.compareTo(cur.data) < 0) {
        if (cur.left == null) {
          cur.left = n;
          numElements++;
          return;
        }
        cur = cur.left;
      } else {
        if (cur.right == null) {
          cur.right = n;
          numElements++;
          return;
        }
        cur = cur.right;
      }
    }
  }

  private void insert(Node<T> n, Node<T> tree) { // recursive
    int comp = n.data.compareTo(tree.data);
    if (comp < 0) { // go left
      if (tree.left == null) {
        tree.left = n;
        numElements++;
      } else {
        insert(n, tree.left);
      }
    } else if (comp > 0) {  // go right
      if (tree.right == null) {
        tree.right = n;
        numElements++;
      } else {
        insert(n, tree.right);
      }
    } // if equal do nothing
  }

  @Override
  public void remove(T t) {
    // TODO Implement me!
    if (root == null) {
      return;
    }
    if (root.data == t) {
      if (root.left != null && root.right != null) {
        replaceWithSuccessor(root);
      } else {
        root = getReplacement(root);
      }
    } else {
      remove(root, t);
    }
  }

  private void remove(Node<T> parent, T t) {
    int comp = t.compareTo(parent.data);
    if (comp < 0) {
      Node<T> left = parent.left;
      if (left == null) {
        return;
      } else if (left.data == t) {
        if (left.left != null && left.right != null) {
          replaceWithSuccessor(left);
        } else {
          parent.left = getReplacement(left);
        }
      } else {
        remove(parent.left, t);
      }
    } else {
      Node<T> right = parent.right;
      if (right == null) {
        return;
      } else if (right.data == t) {
        if (right.left != null && right.right != null) {
          replaceWithSuccessor(right);
        } else {
          parent.right = getReplacement(right);
        }
      } else {
        remove(parent.right, t);
      }
    }
  }

  private Node<T> getReplacement(Node<T> node) {
    if (node.left != null) {
      return node.left;
    } else if (node.right != null) {
      return node.right;
    }
    return null;
  }

  private void replaceWithSuccessor(Node<T> n) {
    Node<T> cur = n.right;
    Node<T> parent = n;
    while (cur.left != null) {
      parent = cur;
      cur = cur.left;
    }
    if (parent == n) {
      n.right = null;
    } else {
      parent.left = null;
    }
    n.data = cur.data;
  }

  @Override
  public boolean has(T t) {
    // TODO Implement me!
    //return find(t) != null;
    return has(t, root);
  }

  private boolean has(T t, Node<T> tree) {  //recursive vers
    if (tree == null) {
      return false;
    } else if (t.equals(tree.data)) {
      return true;
    } else if (t.compareTo(tree.data) < 0) {
      return has(t, tree.left);
    } else {
      return has(t, tree.right);
    }
  }

  private Node<T> find(T t) {
    Node<T> cur = root;
    while (cur != null) {
      if (t.equals(cur.data)) {
        return cur;
      }
      if (t.compareTo(cur.data) < 0) {
        cur = cur.left;
      } else {
        cur = cur.right;
      }
    }
    return null;
  }

  @Override
  public int size() {
    // TODO Implement me!
    return numElements;
  }

  @Override
  public Iterator<T> iterator() {
    // TODO Implement me!
    return new BSTIterator();
  }

  private class BSTIterator implements Iterator<T> {
    Node<T> cur;

    public BSTIterator () {
      cur = root;
    }

    @Override
    public boolean hasNext() {
      return cur != null;
    }

    @Override
    public T next() {
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

    bst.remove(13);
    bst.remove(10);
    bst.remove(0);
  }
}
