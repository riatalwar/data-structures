package hw4;

public class BinaryHeapPriorityQueueTest extends PriorityQueueTest {

  @Override
  protected PriorityQueue<Integer> createQueue() {
    return new BinaryHeapPriorityQueue<>();
  }

  private static class TestComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
      if (o1.length() > o2.length()) {
        return 1;
      } else if (o1.length() < o2.length()) {
        return -1;
      }
      return 0;
    }
  }

  @Test
  @DisplayName("insert works correctly with comparator")
  public void insertComparator() {
    Comparator<String> cmp = new TestComparator();
    PriorityQueue<String> pqC = new BinaryHeapPriorityQueue<>(cmp);

    pqC.insert("123");
    pqC.insert("23");
    pqC.insert("1");
    pqC.insert("0123");

    assertEquals("[0123, 123, 1, 23]", pqC.toString());
  }

  @Test
  @DisplayName("remove works correctly with comparator")
  public void removeComparator() {
    Comparator<String> cmp = new TestComparator();
    PriorityQueue<String> pqC = new BinaryHeapPriorityQueue<>(cmp);

    pqC.insert("01234");
    pqC.insert("567");
    pqC.insert("23");
    pqC.insert("8");

    pqC.remove();
    pqC.remove();
    assertEquals("[23, 8]", pqC.toString());
  }
}
