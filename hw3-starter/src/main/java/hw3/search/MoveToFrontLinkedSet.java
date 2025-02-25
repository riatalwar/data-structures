package hw3.search;

/**
 * Set implemented using a doubly linked list and move-to-front heuristic.
 *
 * @param <T> Element type.
 */
public class MoveToFrontLinkedSet<T> extends LinkedSet<T> {

  /**
   * Execute series of operations on set
   * @param args - main
   */
  public static void main(String[] args) {
    MoveToFrontLinkedSet<Integer> ls = new MoveToFrontLinkedSet<>();

    ls.insert(2);
    ls.insert(3);
    ls.insert(8);
    ls.insert(4);
    ls.insert(6);

    ls.remove(3);
    ls.remove(4);

    ls.insert(5);

    ls.has(3);
    ls.has(8);
    ls.has(5);
  }

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
    head.prev = null;
    return head;
  }

}
