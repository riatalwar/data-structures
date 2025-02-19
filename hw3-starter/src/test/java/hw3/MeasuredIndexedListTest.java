package hw3;

import hw3.sort.MeasuredIndexedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeasuredIndexedListTest {

  private static final int LENGTH = 15;
  private static final int DEFAULT_VALUE = 3;

  private MeasuredIndexedList<Integer> measuredIndexedList;

  @BeforeEach
  void setup() {
    measuredIndexedList = new MeasuredIndexedList<>(LENGTH, DEFAULT_VALUE);
  }

  @Test
  @DisplayName("MeasuredIndexedList starts with zero reads")
  void zeroReadsStart() {
    assertEquals(0, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("MeasuredIndexedList starts with zero writes")
  void zeroWritesStart() {
    assertEquals(0, measuredIndexedList.mutations());
  }

  // TODO Add more tests
  @Test
  @DisplayName("Get correctly increases access counter")
  void getIncreasesAccesses() {
    measuredIndexedList.get(0);
    measuredIndexedList.get(4);
    measuredIndexedList.get(7);
    measuredIndexedList.get(13);
    assertEquals(4, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("Invalid gets do not increase access counter")
  void badGetDoesNotIncreaseAccesses() {
    try {
      measuredIndexedList.get(15);
      fail("Expected error");
    } catch (IndexException e) {
      assertEquals(0, measuredIndexedList.accesses());
    }

    measuredIndexedList.get(4);
    measuredIndexedList.get(7);

    try {
      measuredIndexedList.get(32);
      fail("Expected error");
    } catch (IndexException e) {
      assertEquals(2, measuredIndexedList.accesses());
    }
  }

  @Test
  @DisplayName("Put correctly increases mutation counter")
  void putIncreasesMutations() {
    measuredIndexedList.put(0, 4);
    measuredIndexedList.put(4, 7);
    measuredIndexedList.put(12, 2);
    assertEquals(3, measuredIndexedList.mutations());
  }

  @Test
  @DisplayName("Invalid puts do not increase mutation counter")
  void badPutDoesNotIncreaseMutations() {
    try {
      measuredIndexedList.put(15, 10);
      fail("Expected error");
    } catch (IndexException e) {
      assertEquals(0, measuredIndexedList.mutations());
    }

    measuredIndexedList.put(4, 7);
    measuredIndexedList.put(12, 2);

    try {
      measuredIndexedList.put(53, 1);
      fail("Expected error");
    } catch (IndexException e) {
      assertEquals(2, measuredIndexedList.mutations());
    }
  }
}
