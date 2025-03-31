package starter;

import exceptions.EmptyException;

/**
 * Stack ADT implemented using linked nodes.
 *
 * @param <T> base type.
 */
public class LinkedStack<T> implements Stack<T> {

  private Node<T> list;

  /**
   * Construct an ListStack.
   */
  public LinkedStack() {
    list = null;
  }

  @Override
  public boolean empty() {
    return list == null; // TODO: Implement me!
  }

  @Override
  public T top() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    return list.data; // TODO: Implement me!
  }

  @Override
  public void pop() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    list = list.next;
  }

  @Override
  public void push(T t) {
    Node<T> n = new Node(t);
    n.next = list;
    list = n;
  }

  private static class Node<T> {
    T data;
    Node<T> next;

    Node(T t) {
      data = t;
    }
  }
}
