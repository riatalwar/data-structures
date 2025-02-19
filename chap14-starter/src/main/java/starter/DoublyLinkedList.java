package starter;

public class DoublyLinkedList<T> {
  private Node<T> head;
  private Node<T> tail;
  private int numElements;

  private static class Node<E> {
    E data;
    Node<E> next;
    Node<E> prev;

    Node(E data) {
      this.data = data;
    }
  }

  public DoublyLinkedList() {
    head = null;
    tail = null;
    numElements = 0;
  }


  public void addFirst (T data) {
    Node<T> n = new Node<>(data);
    n.next = head;
    if (head != null) {
      head.prev = n;
    } else {
      tail = n;
    }
    head = n;
    numElements++;
  }

  public void addLast(T data) {
    Node<T> n = new Node<>(data);
    if (tail != null) {
      tail.next = n;
    } else {
      head = n;
    }
    n.prev = tail;
    tail = n;
    numElements++;
  }

  public T get(int index) {
    if (index < numElements / 2) {
      Node cur = head;
      while (cur != null) {}
    }
    return null; // TODO Implement me!
  }
}