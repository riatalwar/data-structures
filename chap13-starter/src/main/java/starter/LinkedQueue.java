package starter;

import exceptions.EmptyException;

/**
 * Linked implementation of Queue ADT.
 *
 * @param <T> base type.
 */
public class LinkedQueue<T> implements Queue<T> {
  Node<T> head, tail;

  @Override
  public void enqueue(T value) {
    // TODO: Implement me!
    if (empty()) {
      head = new Node<>(value);
      tail = head;
    } else {
      tail.next = new Node<>(value);
      tail = tail.next;
    }
  }

  @Override
  public void dequeue() throws EmptyException {
    // TODO: Implement me!
    if (empty()) {
      throw new EmptyException();
    }
    head = head.next;
    if (head == null) {
      tail = null;
    }
  }

  @Override
  public T front() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    return head.data; // TODO: Implement me!
  }

  @Override
  public boolean empty() {
    return head == null; // TODO: Implement me!
  }

  private static class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
      this.data = data;
    }
  }
}
