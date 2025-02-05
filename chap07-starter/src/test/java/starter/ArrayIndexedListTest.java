package starter;

public class ArrayIndexedListTest extends IndexedListTest {
  @Override
  protected IndexedList<Integer> createUnit(int length, int value) {
    return new ArrayIndexedList<>(size, defaultValue);
  }
}
