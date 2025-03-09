package hw4;

import exceptions.EmptyException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Priority queue implemented as a binary heap with a ranked array representation.
 *
 * @param <T> Element type.
 */
public class BinaryHeapPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T> {
  private final List<T> heap;
  private Comparator<T> cmp;

  /**
   * Make a BinaryHeapPriorityQueue.
   */
  public BinaryHeapPriorityQueue() {
    this(new DefaultComparator<>());
  }

  /**
   * Make a BinaryHeapPriorityQueue with a custom comparator.
   *
   * @param cmp Comparator to use.
   */
  public BinaryHeapPriorityQueue(Comparator<T> cmp) {
    this.cmp = cmp;
    heap = new ArrayList<>();
    heap.add(null); // Add a dummy element at index 0 to simplify arithmetic
  }

  @Override
  public void insert(T t) {
    // TODO Implement Me!
  }

  @Override
  public void remove() throws EmptyException {
    // TODO Implement Me!
  }

  @Override
  public T best() throws EmptyException {
    if (empty()) {
      throw new EmptyException("Heap is empty");
    }
    return heap.get(1);
  }

  @Override
  public boolean empty() {
    return heap.size() == 1;
  }

  private int leftChild(int i) {
    return 2 * i;
  }

  private int rightChild(int i) {
    return 2 * i + 1;
  }

  private int parent(int i) {
    return i / 2;
  }

  @Override
  public Iterator<T> iterator() {
    return null;
  }

  public String toString() {
    return heap.toString().replaceAll("null, ", "");
  }
  // Default comparator is the natural order of elements that are Comparable.
  private static class DefaultComparator<T extends Comparable<T>> implements Comparator<T> {
    public int compare(T t1, T t2) {
      return t1.compareTo(t2);
    }
  }
}
