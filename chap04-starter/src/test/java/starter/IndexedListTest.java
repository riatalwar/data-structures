package starter;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class IndexedListTest {
// no main method
  // tests will pass by default
  IndexedList<Integer> numbers;
  @BeforeEach
  public void setup() {
    numbers = new ArrayIndexedList<>(10, 0);
  }

  @Test
  @DisplayName("get() returns the default value after IndexedList is instantiated.")
  void testGetAfterConstruction() {
    // TODO Implement me!
    // Assertions.fail("not implemented");
    IndexedList<Integer> numbers = new ArrayIndexedList<>(10, 0);
    for (int i = 0; i < numbers.length(); i++) {
      assertEquals(0, numbers.get(i));
    } // don't really need to check all values--do ends and maybe 1 middle
  }

  @Test
  @DisplayName("put() changes the default value after IndexedList is instantiated.")
  void testPutChangesValueAfterConstruction() {
    // TODO Implement me!
    IndexedList<Integer> numbers = new ArrayIndexedList<>(10, 0);
    numbers.put(0, 10);
    numbers.put(4, -1);
    numbers.put(9, 2);
    assertEquals(10, numbers.get(0));
    assertEquals(-1, numbers.get(4));
    assertEquals(2, numbers.get(9));
  }

  @Test
  @DisplayName("put() overwrites the existing value at given index to provided value.")
  void testPutUpdatesValueAtGivenIndex() {
    // TODO Implement me!
  }
}