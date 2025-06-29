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
    // check condition
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
    if (index < 0 || index >= length) {
      throw new IndexException();
    }

    // simply delete index if setting to default
    if (defaultValue.equals(value)) {
      delete(index);
      return;
    }

    Node<T> node = new Node<>(value, index);

    // handle edge cases
    if (head == null) {
      // empty list
      head = node;
    } else if (head.index > index) {
      // insert at beginning of list
      node.next = head;
      head = node;
    } else {
      // insert into middle if not edge case
      putMiddle(node);
    }


  }

  /**
   * Deletes non-default value at given index.
   *
   * @param index Index within list, expected: 0 <= index < length.
   */
  private void delete(int index) {
    if (head == null) {
      // no non default vals
      return;
    } else if (head.index == index) {
      // head edge case
      head = head.next;
      return;
    }

    // search for index
    Node<T> cur = head;
    while (cur.next != null) {
      if (cur.next.index == index) {
        // delete next
        cur.next = cur.next.next;
        return;
      } else if (cur.next.index > index) {
        // stop when past index
        return;
      }
      cur = cur.next;
    }
  }

  /**
   * Inserts a non-default node into its given index.
   *
   * @param node to insert, expected: 0 <= node.index < length,
   *             node.data != defaultValue.
   */
  private void putMiddle(Node<T> node) {
    Node<T> cur = head;
    while (cur.next != null) {
      if (cur.index == node.index) {
        // replace element
        cur.data = node.data;
        return;
      } else if (cur.next.index > node.index) {
        // insert element
        node.next = cur.next;
        cur.next = node;
        return;
      }
      cur = cur.next;
    }

    // replace if at last index
    if (cur.index == node.index) {
      cur.data = node.data;
      return;
    }
    // add to end if index not in middle of list
    cur.next = node;
  }

  @Override
  public Iterator<T> iterator() {
    return new SparseIndexedListIterator();
  }

  private static class Node<E> {
    E data;
    int index;
    SparseIndexedList.Node<E> next;

    Node(E data, int index) {
      this.data = data;
      this.index = index;
    }
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
      if (nextIndex >= length) {
        throw new NoSuchElementException();
      }

      T val = defaultValue;

      // check if nextIndex is non default
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
