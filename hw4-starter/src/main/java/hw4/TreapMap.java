package hw4;

import java.util.Iterator;
import java.util.Random;

/**
 * Map implemented as a Treap.
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class TreapMap<K extends Comparable<K>, V> implements OrderedMap<K, V> {

  /*** Do not change variable name of 'rand'. ***/
  private static Random rand;
  /*** Do not change variable name of 'root'. ***/
  private Node<K, V> root;

  /**
   * Make a TreapMap.
   */
  public TreapMap() {
    rand = new Random();
  }

  /**
   * Make a TreapMap.
   *
   * @param r optionally seeded random generator.
   */
  public TreapMap(Random r) {
    rand = r;
  }

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
      return new Node<>(k, v);
    }

    int cmp = k.compareTo(n.key);
    if (cmp < 0) {
      n.left = insert(n.left, k, v);
    } else if (cmp > 0) {
      n.right = insert(n.right, k, v);
    } else {
      throw new IllegalArgumentException("duplicate key " + k);
    }

    n = orderPriorites(n);
    return n;
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    // TODO Implement Me!
    return null;
  }

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

  private Node<K, V> orderPriorites(Node<K, V> tree) {
    int rightPrior = Integer.MAX_VALUE;
    int leftPrior = Integer.MAX_VALUE;
    if (tree.right != null) {
      rightPrior = tree.right.priority;
    }
    if (tree.left != null) {
      leftPrior = tree.left.priority;
    }

    if (tree.left != null && leftPrior < rightPrior
              && leftPrior < tree.priority) {
      return rightRotation(tree);
    } else if (tree.right != null && rightPrior < tree.priority) {
      return leftRotation(tree);
    }

    return tree;
  }

  private Node<K, V> rightRotation(Node<K, V> n) {
    Node<K, V> rotated = n.left;
    Node<K, V> shift = rotated.right;
    rotated.right = n;
    n.left = shift;
    return rotated;
  }

  private Node<K, V> leftRotation(Node<K, V> n) {
    Node<K, V> rotated = n.right;
    Node<K, V> shift = rotated.left;
    rotated.left = n;
    n.right = shift;
    return rotated;
  }

  @Override
  public Iterator<K> iterator() {
    return new InorderIterator();
  }

  /*** Do not change this function's name or modify its code. ***/
  @Override
  public String toString() {
    return BinaryTreePrinter.printBinaryTree(root);
  }


  // Iterative in-order traversal over the keys
  private class InorderIterator implements Iterator<K> {
    private final Stack<Node<K, V>> stack;

    InorderIterator() {
      stack = new Stack<>();
      pushLeft(root);
    }

    private void pushLeft(Node<K, V> curr) {
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
      Node<K, V> top = stack.pop();
      pushLeft(top.right);
      return top.key;
    }
  }

  /**
   * Inner node class, each holds a key (which is what we sort the
   * BST by) as well as a value. We don't need a parent pointer as
   * long as we use recursive insert/remove helpers. Since this is
   * a node class for a Treap we also include a priority field.
   *
   * @param <K> Type for keys.
   * @param <V> Type for values.
   **/
  private static class Node<K, V> implements BinaryTreeNode {
    Node<K, V> left;
    Node<K, V> right;
    K key;
    V value;
    int priority;

    // Constructor to make node creation easier to read.
    Node(K k, V v) {
      // left and right default to null
      key = k;
      value = v;
      priority = generateRandomInteger();
    }

    // Use this function to generate random values
    // to use as node priorities as you insert new
    // nodes into your TreapMap.
    private int generateRandomInteger() {
      // Note: do not change this function!
      return rand.nextInt();
    }

    @Override
    public String toString() {
      return key + ":" + value + ":" + priority;
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
