package starter;

import java.util.Iterator;

// TODO some of the sanity checks are convoluted and need to be refactored!
public class LinkedList<T> implements Iterable<T> {
  private Node<T> head;
  private int numElements;

  public LinkedList() {
    head = null;
    numElements = 0;
  }

  public void addFirst(T t) {
    // TODO Implement me!
  }

  public void addLast(T t) {
    // TODO Implement me!
  }

  public void traverse() {
    // TODO Implement me!
  }

  public T get(int index) {
    return null;     // TODO Implement me!
  }


  public void insert(int index, T t) {
    // TODO Implement me!
  }

  public void delete(int index) {
    // TODO Implement me!
  }

  @Override
  public Iterator<T> iterator() {
    return new LinkedListIterator();
  }

  public int length() {
    return 0;  // TODO Implement me!
  }

  private static class Node<E> {
    E data;
    Node<E> next;

    Node(E data) {
      this.data = data;
    }
  }

  private class LinkedListIterator implements Iterator<T> {

    @Override
    public T next() {
      return null;     // TODO Implement me!
    }

    @Override
    public boolean hasNext() {
      return false;     // TODO Implement me!
    }
  }

}
