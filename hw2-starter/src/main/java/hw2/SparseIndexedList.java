package hw2;

import exceptions.IndexException;
import exceptions.LengthException;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * An implementation of an IndexedList designed for cases where
 * only a few positions have distinct values from the initial value.
 *
 * @param <T> Element type.
 */
public class SparseIndexedList<T> implements IndexedList<T> {

  private SparseIndexedList.Node<T> head;
  private final int length;
  private final T defaultValue;

  /**
   * Constructs a new SparseIndexedList of length size
   * with default value of defaultValue.
   *
   * @param size Length of list, expected: size > 0.
   * @param defaultValue Default value to store in each slot.
   * @throws LengthException if size <= 0.
   */
  public SparseIndexedList(int size, T defaultValue) throws LengthException {
    // TODO
    if (size <= 0) {
      throw new LengthException();
    }

    length = size;
    this.defaultValue = defaultValue;
    head = null;
  }

  @Override
  public int length() {
    return length;
  }

  @Override
  public T get(int index) throws IndexException {
    if (index < 0 || index >= length) {
      throw new IndexException();
    }

    // check non default vals
    Node<T> cur = head;
    while (cur != null) {
      // if non default at index
      if (cur.index == index) {
        return cur.data;
      }
      // if passed index
      if (cur.index > index) {
        return defaultValue;
      }
      cur = cur.next;
    }

    return defaultValue;
  }

  @Override
  public void put(int index, T value) throws IndexException {
    // TODO
  }

  @Override
  public Iterator<T> iterator() {
    return new SparseIndexedListIterator();
  }

  private static class Node<T> {
    T data;
    int index;
    SparseIndexedList.Node<T> next;
  }

  private class SparseIndexedListIterator implements Iterator<T> {
    private int nextIndex;
    private Node<T> nextNode;

    private SparseIndexedListIterator() {
      nextIndex = 0;
      nextNode = head;
    }

    @Override
    public boolean hasNext() {
      return nextIndex < length;
    }

    @Override
    public T next() throws NoSuchElementException {
      T val = defaultValue;
      if (nextNode != null) {
        if (nextNode.index == nextIndex) {
          val = nextNode.data;
          nextNode = nextNode.next;
        }
      }
      nextIndex++;
      return val;
    }
  }
}
