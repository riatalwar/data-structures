package hw4;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Map implemented as an AVL Tree.
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class AvlTreeMap<K extends Comparable<K>, V> implements OrderedMap<K, V> {

  /*** Do not change variable name of 'root'. ***/
  private Node<K, V> root;
  private int size;

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException("cannot handle null key");
    }

    root = insert(root, k, v);
    size++;
  }

  private Node<K, V> insert(Node<K, V> n, K k, V v) {
    if (n == null) {
      return new AvlTreeMap.Node<>(k, v);
    }

    // recursively determine where to insert key
    int cmp = k.compareTo(n.key);
    if (cmp < 0) {
      n.left = insert(n.left, k, v);
    } else if (cmp > 0) {
      n.right = insert(n.right, k, v);
    } else {
      throw new IllegalArgumentException("duplicate key " + k);
    }

    // calculate modified height and balance
    n = balance(n);
    n.height = calculateHeight(n);

    return n;
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    Node<K, V> n = findForSure(k);
    V value = n.value;
    root = remove(root, n);
    size--;
    return value;
  }

  private Node<K, V> remove(Node<K, V> tree, Node<K, V> toRemove) {
    // recursively find and remove node
    int cmp = tree.key.compareTo(toRemove.key);
    if (cmp == 0) {
      tree = remove(tree);
    } else if (cmp > 0) {
      tree.left = remove(tree.left, toRemove);
    } else {
      tree.right = remove(tree.right, toRemove);
    }

    // rebalance after removal and calculate modified height
    if (tree != null) {
      tree = balance(tree);
      tree.height = calculateHeight(tree);
    }

    return tree;
  }

  // Remove given node and return the remaining tree (structural change).
  private Node<K, V> remove(Node<K, V> n) {
    // Easy if the node has 0 or 1 child.
    if (n.right == null) {
      return n.left;
    } else if (n.left == null) {
      return n.right;
    }

    // If it has two children, find the predecessor (max in left subtree),
    Node<K, V> toReplaceWith = max(n);
    // then copy its data to the given node (value change),
    n.key = toReplaceWith.key;
    n.value = toReplaceWith.value;
    // then remove the predecessor node (structural change).
    n.left = remove(n.left, toReplaceWith);

    return n;
  }

  private Node<K, V> max(Node<K, V> n) {
    Node<K, V> curr = n.left;
    while (curr.right != null) {
      curr = curr.right;
    }
    return curr;
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    Node<K, V> n = findForSure(k);
    n.value = v;
  }

  @Override
  public V get(K k) throws IllegalArgumentException {
    Node<K, V> n = findForSure(k);
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

  private int calculateHeight(Node<K, V> n) {
    // children heights -1 if null
    int right = -1;
    int left = -1;

    // if children not null get actual heights
    if (n.right != null) {
      right = n.right.height;
    }
    if (n.left != null) {
      left = n.left.height;
    }

    // calculate height
    return Math.max(right, left) + 1;
  }

  private Node<K, V> balance(Node<K, V> n) {
    int bf = balanceFactor(n);

    // if left heavy
    if (bf > 1) {
      int bfLeft = balanceFactor(n.left);
      if (bfLeft < 0) {
        n.left = leftRotation(n.left);
      }
      n = rightRotation(n);
    } else if (bf < -1) {
      // if right heavy
      int bfRight = balanceFactor(n.right);
      if (bfRight > 0) {
        n.right = rightRotation(n.right);
      }
      n = leftRotation(n);
    }

    return n;
  }

  private int balanceFactor(Node<K, V> n) {
    // height -1 if null
    int right = -1;
    int left = -1;

    // if not null get actual height
    if (n.right != null) {
      right = n.right.height;
    }
    if (n.left != null) {
      left = n.left.height;
    }

    // calculate bf
    return left - right;
  }

  private Node<K, V> rightRotation(Node<K, V> n) {
    // right structural rotation
    Node<K, V> rotated = n.left;
    Node<K, V> shift = rotated.right;
    rotated.right = n;
    n.left = shift;
    n.height = calculateHeight(n);
    rotated.height = calculateHeight(n);
    return rotated;
  }

  private Node<K, V> leftRotation(Node<K, V> n) {
    // left structural rotation
    Node<K, V> rotated = n.right;
    Node<K, V> shift = rotated.left;
    rotated.left = n;
    n.right = shift;
    n.height = calculateHeight(n);
    rotated.height = calculateHeight(n);
    return rotated;
  }

  @Override
  public Iterator<K> iterator() {
    return new InorderIterator();
  }

  // Iterative in-order traversal over the keys
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
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
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
    int height;

    // Constructor to make node creation easier to read.
    Node(K k, V v) {
      // left and right default to null
      key = k;
      value = v;
      height = 0;
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
