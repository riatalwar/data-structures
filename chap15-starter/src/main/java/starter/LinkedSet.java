package starter;

import java.util.Iterator;

public class LinkedSet<T> implements Set<T> {
  Node head;

  public LinkedSet() {
    head = null;
  }

  private class Node {
    T data;
    Node next;
  }
  @Override
  public void insert(T t) {
    // TODO: Implement me!
    // cant compareTo generic T

  }

  @Override
  public void remove(T t) {
    // TODO: Implement me!
  }

  @Override
  public boolean has(T t) {
    // binary search is not efficient for linked list O(nlgn)
    // TODO: Implement me!
    for (Node cur = head; cur != null; cur = cur.next) {
      if (cur.data.equals(t)) return true;
    }
    return false;
  }

  @Override
  public int size() {
    // TODO: Implement me!
    return 0;
  }

  @Override
  public Iterator<T> iterator() {
    // TODO: Implement me!
    return null;
  }
}
