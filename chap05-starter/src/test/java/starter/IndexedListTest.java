package starter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class IndexedListTest {
  private IndexedList<Integer> numbers;
  private final static int size = 5;
  private final static int defaultValue = 10;

  @BeforeEach
  void setUp() {
    numbers = new ArrayIndexedList<>(size, defaultValue);
  }

  @Test
  @DisplayName("get() returns the default value after IndexedList is instantiated.")
  void testGetAfterConstruction() {
    for (int index = 0; index < numbers.length(); index++) {
      try {
        assertEquals(defaultValue, numbers.get(index));
      } catch (IndexException e) {
        throw new RuntimeException(e);
      }
    }
  }

  @Test
  void testBadSizeFailsConstruction() {
    try {
      IndexedList<Integer> invalid = new ArrayIndexedList<>(-1, 0);
      fail("Expected error");
    } catch (LengthException le) {
      // constinue
    }
  }

  @Test
  @DisplayName("put() changes the default value after IndexedList is instantiated.")
  void testPutChangesValueAfterConstruction() {
    numbers.put(2, 7);
    try {
      assertEquals(7, numbers.get(2));
    } catch (IndexException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  @DisplayName("put() overwrites the existing value at given index to provided value.")
  void testPutUpdatesValueAtGivenIndex() {
    numbers.put(1, 8);
    try {
      assertEquals(8, numbers.get(1));
    } catch (IndexException e) {
      throw new RuntimeException(e);
    }
    numbers.put(1, -5);
    try {
      assertEquals(-5, numbers.get(1));
    } catch (IndexException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  @DisplayName("length() returns the correct size after IndexedList is instantiated.")
  void testLengthAfterConstruction() {
    assertEquals(size, numbers.length());
  }

  @Test
  public void testIllegalIndexesRejected() {
    try {
      numbers.get(-1);
      fail("Exception expected");
    } catch (IndexException ioobe) {
      // carry on
    }
  }
}
