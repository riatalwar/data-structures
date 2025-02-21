package starter;

public class Main {
  public static void main(String[] args) {
    DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
    DoublyLinkedList.Node head = dll.get(0);
    dll.insertAfter(head, 10);

  }
}
