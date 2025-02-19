package hw3;

import exceptions.IndexException;
import hw3.sort.MeasuredIndexedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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

  @Test
  @DisplayName("Count correctly increments access counter")
  void countCorrectlyIncrementsAccesses() {
    measuredIndexedList.count(DEFAULT_VALUE);
    assertEquals(15, measuredIndexedList.accesses());
    measuredIndexedList.count(20);
    assertEquals(30, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("Count returns correct value with no previous changes made")
  void countReturnsCorrectValue() {
    assertEquals(15, measuredIndexedList.count(DEFAULT_VALUE));
    assertEquals(0, measuredIndexedList.count(20));
  }

  @Test
  @DisplayName("Count returns correct value after modifications")
  void countReturnsCorrectAfterModifications() {
    measuredIndexedList.put(0, 4);
    measuredIndexedList.put(4, 4);
    measuredIndexedList.put(14, 2);
    assertEquals(1, measuredIndexedList.count(2));
    assertEquals(0, measuredIndexedList.count(20));
    assertEquals(2, measuredIndexedList.count(4));
    assertEquals(12, measuredIndexedList.count(DEFAULT_VALUE));
    assertEquals(60, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("Reset sets correct value after modifications")
  void resetAfterModifications() {
    measuredIndexedList.put(0, 4);
    measuredIndexedList.put(4, 4);
    measuredIndexedList.put(14, 2);
    measuredIndexedList.count(2);
    measuredIndexedList.get(0);
    measuredIndexedList.get(12);

    assertEquals(17, measuredIndexedList.accesses());
    assertEquals(3, measuredIndexedList.mutations());

    measuredIndexedList.reset();

    assertEquals(0, measuredIndexedList.accesses());
    assertEquals(0, measuredIndexedList.mutations());
  }
}
