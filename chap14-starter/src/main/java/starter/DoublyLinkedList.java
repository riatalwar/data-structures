package starter;

public class DoublyLinkedList<T> {
  private Node<T> head;
  private Node<T> tail;
  private int numElements;

  public static class Node<E> { // public exposes to user, not fun
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

  public Node<T> get(int index) {
    if (index <= numElements / 2) {
      Node<T> cur = head;
      int count = 0;
      while (cur != null) {
        if (count == index) {
          return cur;
        }
        count++;
        cur = cur.next;
      }
    }
    Node<T> cur = tail;
    int count = numElements - 1;
    while (cur != null) {
      if (count == index) {
        return cur;
      }
      count--;
      cur = cur.prev;
    }
    return null;
  }

  public void delete(Node<T> target) {
    if (target == null) {
      return;
    }
    if (target.prev != null) {
      target.prev.next = target.next;
    }
    if (target.next != null) {
      target.next.prev = target.prev;
    }
    numElements--;
  }

  public void insertAfter(Node<T> target, T data) {
    if (target == null) {
      return;
    }
    Node<T> n = new Node<>(data);
    n.next = target.next;
    n.prev = target;
    target.next = n;
    if (n.next != null) {
      n.next.prev = n;
    }
  }
}