package hw2;

import exceptions.IndexException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for any class implementing the IndexedList interface.
 */
public abstract class IndexedListTest {
  protected static final int LENGTH = 10;
  protected static final int INITIAL = 7;
  private IndexedList<Integer> indexedList;

  public abstract IndexedList<Integer> createArray();

  @BeforeEach
  public void setup() {
    indexedList = createArray();
  }

  @Test
  @DisplayName("get() returns the default value after IndexedList is instantiated.")
  void testGetAfterConstruction() {
    for (int index = 0; index < indexedList.length(); index++) {
      assertEquals(INITIAL, indexedList.get(index));
    }
  }

  @Test
  @DisplayName("length() returns the specified length after IndexedList is instantiated.")
  void testLengthAfterConstruction() {
    assertEquals(10, indexedList.length());
  }

  @Test
  @DisplayName("get() throws exception if index is below the valid range.")
  void testGetWithIndexBelowRangeThrowsException() {
    try {
      indexedList.get(-1);
      fail("IndexException was not thrown for index < 0");
    } catch (IndexException ex) {
      return;
    }
  }

  // TODO Add more tests!
  @Test
  @DisplayName("get() throws exception if index is above the valid range.")
  void testGetWithIndexAboveRangeThrowsException() {
    try {
      indexedList.get(10);
      fail("IndexException was not thrown for index = length");
    } catch (IndexException ex) {
      return;
    }

    try {
      indexedList.get(11);
      fail("IndexException was not thrown for index > length");
    } catch (IndexException ex) {
      return;
    }
  }

  @Test
  @DisplayName("put() throws exception if index is below the valid range.")
  void testPutWithIndexBelowRangeThrowsException() {
    try {
      indexedList.put(-1, 0);
      fail("IndexException was not thrown for index < 0");
    } catch (IndexException ex) {
      return;
    }
  }

  @Test
  @DisplayName("put() throws exception if index is above the valid range.")
  void testPutWithIndexAboveRangeThrowsException() {
    try {
      indexedList.put(10, 0);
      fail("IndexException was not thrown for index = length");
    } catch (IndexException ex) {
      return;
    }

    try {
      indexedList.put(11, 0);
      fail("IndexException was not thrown for index > length");
    } catch (IndexException ex) {
      return;
    }
  }

  @Test
  @DisplayName("put() changes the value at a designated index.")
  void testPutChangesDesiredValue() {
    indexedList.put(0, 0);
    assertEquals(0, indexedList.get(0));

    indexedList.put(4, 3);
    assertEquals(3, indexedList.get(4));

    indexedList.put(9, 5);
    assertEquals(5, indexedList.get(9));
  }

  @Test
  @DisplayName("iterator() returns valid iterator.")
  void testIteratorDirectAccess() {
    Iterator<Integer> it = indexedList.iterator();
    for (int i = 0; i < LENGTH; i++) {
      assertTrue(it.hasNext());
      assertEquals(INITIAL, it.next());
    }
    assertFalse(it.hasNext());

    try {
      it.next();
      fail("Exception expected");
    } catch (NoSuchElementException e) {
      // expected
    }
  }

  @Test
  @DisplayName("iterator() for each loop functions properly.")
  void testIteratorForEach() {
    int count = 0;
    for (Integer i : indexedList) {
      assertEquals(INITIAL, i);
      count++;
    }
    assertEquals(LENGTH, count);
  }

}
