package starter;

import java.util.Iterator;
import java.util.NoSuchElementException;

// TODO some of the sanity checks are convoluted and need to be refactored!
public class LinkedList<T> implements Iterable<T> {
  private Node<T> head;
  private int numElements;

  public LinkedList() {
    head = null;
    numElements = 0;
  }

  public void addFirst(T t) {
    Node<T> n = new Node<>(t);
    n.next = head;
    head = n;
    numElements++;
  }

  public void addLast(T t) {
    Node<T> n = new Node<>(t);

    Node<T> cur = head;
    for (int i = 0; i < numElements; i++) {
      cur = cur.next;
    }
    cur.next = n;
    numElements++;
  }

  public void traverse() {
    // TODO Implement me!
    Node<T> cur = head;
    while (cur != null) {
      System.out.println(cur.data);
      cur = cur.next;
    }
  }

  public T get(int index) {
    if (index >= numElements || index < 0) {
      throw new NoSuchElementException();
    }
    Node<T> cur = head;
    for (int i = 0; i <= index; i++) {
      cur = cur.next; // can do this inside the for loop decl
    }
    return cur.data;     // TODO Implement me!
  }


  public void insert(int index, T t) {
    // TODO Implement me!
    if (index > numElements || index < 0) {
      throw new NoSuchElementException();
    }
    Node<T> n = new Node<>(t);
    Node<T> cur = head;
    // handle insert a the beginning
    for (int i = 0; i < index - 1; i++) {
      cur = cur.next;
    }
    n.next = cur.next;
    cur.next = n;
  }

  public void delete(int index) {
    // TODO Implement me!
    if (index >= numElements || index < 0) {
      throw new NoSuchElementException();
    }

    if (index == 0) {
      head = head.next;
    }

    Node<T> cur = head;
    for (int i = 0; i < index - 1; i++) {
      cur = cur.next;
    }

    cur.next = cur.next.next;
    numElements--;
  }

  @Override
  public Iterator<T> iterator() {
    return new LinkedListIterator();
  }

  public int length() {
    return numElements;  // TODO Implement me!
  }

  private static class Node<E> {
    E data;
    Node<E> next;

    Node(E data) {
      this.data = data;
    }
  }

  private class LinkedListIterator implements Iterator<T> {
    Node<T> cur;

    public LinkedListIterator() {
      cur = head;
    }

    @Override
    public T next() {
      return cur.data;     // TODO Implement me!
    }

    @Override
    public boolean hasNext() {
      return cur != null;     // TODO Implement me!
    }
  }

}
