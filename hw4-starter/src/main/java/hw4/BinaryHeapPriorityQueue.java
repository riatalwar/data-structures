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
    heap.add(t);
    // no swim if only element
    if (heap.size() == 2) {
      return;
    }

    // track current index and parent as swimming
    int index = heap.size() - 1;
    int parentIdx = parent(index);
    T parent = heap.get(parentIdx);

    // while t is less than parent, swap
    while (cmp.compare(t, parent) < 0) {
      heap.set(index, parent);
      heap.set(parentIdx, t);

      index = parentIdx;
      parentIdx = parent(index);
      parent = heap.get(parentIdx);

      // stop when at the top
      if (parentIdx == 0) {
        return;
      }
    }
  }

  @Override
  public void remove() throws EmptyException {
    if (empty()) {
      throw new EmptyException("Heap is empty");
    }

    int size = heap.size();
    if (size == 2) {
      heap.remove(1);
      return;
    }

    heap.set(1, heap.get(size - 1));
    heap.remove(size - 1);
    swimDown(1);
  }

  private void swimDown(int index) {
    int leftIdx = leftChild(index);
    int rightIdx = rightChild(index);
    int size = heap.size();

    if (leftIdx < size && rightIdx < size) {
      swimDown(index, leftIdx, rightIdx);
    } else if (leftIdx < size) {
      swimDown(index, leftIdx);
    }
  }

  private void swimDown(int index, int leftIdx, int rightIdx) {
    T current = heap.get(index);
    T left = heap.get(leftIdx);
    T right = heap.get(rightIdx);

    if (cmp.compare(left, right) < 0
            && cmp.compare(left, current) < 0) {
      heap.set(leftIdx, current);
      heap.set(index, left);

      if (leftChild(leftIdx) < heap.size()) {
        swimDown(leftIdx);
      }
    } else if (cmp.compare(right, current) < 0) {
      heap.set(rightIdx, current);
      heap.set(index, right);

      if (rightChild(rightIdx) < heap.size()) {
        swimDown(rightIdx);
      }
    }
  }

  private void swimDown(int index, int leftIdx) {
    T current = heap.get(index);
    T left = heap.get(leftIdx);

    if (cmp.compare(left, current) < 0) {
      heap.set(leftIdx, current);
      heap.set(index, left);
    }
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
    return new LevelOrderIterator();
  }

  public String toString() {
    if (empty()) {
      return "[]";
    }
    return heap.toString().replaceAll("null, ", "");
  }
  // Default comparator is the natural order of elements that are Comparable.
  private static class DefaultComparator<T extends Comparable<T>> implements Comparator<T> {
    public int compare(T t1, T t2) {
      return t1.compareTo(t2);
    }
  }
}
