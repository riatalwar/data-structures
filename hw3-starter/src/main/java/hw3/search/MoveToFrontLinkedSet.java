package hw3.search;

/**
 * Set implemented using a doubly linked list and move-to-front heuristic.
 *
 * @param <T> Element type.
 */
public class MoveToFrontLinkedSet<T> extends LinkedSet<T> {

  // move-to-front heuristic
  @Override
  protected Node<T> find(T t) {
    // super find method still holds
    Node<T> n = super.find(t);

    // node not found or already head do nothing
    if (n == null || n == head) {
      return n;
    }

    // node found remove and add to front
    super.remove(n);
    n.next = head;
    if (head != null) {
      head.prev = n;
    }
    head = n;
    return n;
  }

}
