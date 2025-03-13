package hw4;

import exceptions.EmptyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class PriorityQueueTest {

  private PriorityQueue<Integer> pq;

  @BeforeEach
  void setUp() {
    pq = this.createQueue();
  }

  abstract protected PriorityQueue<Integer> createQueue();

  @Test
  @DisplayName("PriorityQueue is empty after construction")
  void newQueueEmpty() {
    assertTrue(pq.empty());
  }

  @Test
  @DisplayName("insert() successfully adds an element")
  public void insertOneElement() {
    pq.insert(1);
    assertEquals("[1]", pq.toString());
  }

  @Test
  @DisplayName("insert() successfully adds multiple ordered elements")
  public void insertMultipleElements() {
    pq.insert(4);
    pq.insert(3);
    pq.insert(2);
    pq.insert(1);
    assertEquals("[4, 3, 2, 1]", pq.toString());
  }

  @Test
  @DisplayName("insert() fully swims up new element")
  public void insertFullSwimUp() {
    pq.insert(3);
    pq.insert(2);
    pq.insert(1);
    pq.insert(4);
    assertEquals("[4, 3, 1, 2]", pq.toString());
  }

  @Test
  @DisplayName("insert() partially swims up new element")
  public void insertPartialSwimUp() {
    pq.insert(4);
    pq.insert(2);
    pq.insert(1);
    pq.insert(3);
    assertEquals("[4, 3, 1, 2]", pq.toString());
  }


  @Test
  @DisplayName("remove() throws exception when empty")
  public void removeEmpty() {
    try {
      pq.remove();
      fail("Expected EmptyException");
    } catch (EmptyException e) {
      // success
    }
  }

  @Test
  @DisplayName("remove() removes one element")
  public void removeOneElement() {
    pq.insert(1);
    pq.remove();
    assertEquals("[]", pq.toString());
  }

  @Test
  @DisplayName("remove() removes multiple elements")
  public void removeMultipleElements() {
    pq.insert(4);
    pq.insert(3);
    pq.insert(2);
    pq.insert(1);

    pq.remove();
    pq.remove();
    assertEquals("[2, 1]", pq.toString());
  }

  @Test
  @DisplayName("remove() fully swims down element")
  public void removeFullSwimDown() {
    pq.insert(4);
    pq.insert(3);
    pq.insert(2);
    pq.insert(1);

    pq.remove();
    assertEquals("[3, 1, 2]", pq.toString());
  }

  @Test
  @DisplayName("remove() partially swims down element")
  public void removePartialSwimDown() {
    pq.insert(8);
    pq.insert(4);
    pq.insert(7);
    pq.insert(3);
    pq.insert(2);
    pq.insert(1);
    pq.insert(5);

    pq.remove();
    assertEquals("[7, 4, 5, 3, 2, 1]", pq.toString());
  }


  @Test
  @DisplayName("best() throws exception when empty")
  public void bestEmpty() {
    try {
      pq.best();
      fail("Expected EmptyException");
    } catch (EmptyException e) {
      // success
    }
  }

  @Test
  @DisplayName("best() returns first element when one element")
  public void bestOneNode() {
    pq.insert(1);
    assertEquals(1, pq.best());
  }

  @Test
  @DisplayName("best() returns first element when multiple elements")
  public void bestMultipleNodes() {
    pq.insert(3);
    pq.insert(4);
    pq.insert(2);

    assertEquals(4, pq.best());
  }


  @Test
  @DisplayName("empty() returns false when multiple elements")
  public void emptyMultipleElements() {
    pq.insert(4);
    pq.insert(3);
    pq.insert(2);
    pq.insert(1);

    assertFalse(pq.empty());
  }

  @Test
  @DisplayName("empty() returns true when no elements")
  public void emptyNoElements() {
    pq.insert(1);
    pq.remove();

    assertTrue(pq.empty());
  }


  @Test
  @DisplayName("iterator correctly traverses by level")
  public void levelOrderIterator() {
    pq.insert(4);
    pq.insert(3);
    pq.insert(2);
    pq.insert(1);

    List<Integer> ordered = new ArrayList<>();
    ordered.add(4);
    ordered.add(3);
    ordered.add(2);
    ordered.add(1);

    int count = 0;
    for (Integer i : pq) {
      assertEquals(ordered.get(count), i);
      count++;
    }

    assertEquals(4, count);
  }

  @Test
  @DisplayName("iterator works correctly on empty list")
  public void emptyIterator() {
    int count = 0;
    for (Integer i : pq) {
      count++;
    }
    assertEquals(0, count);
  }

  // TODO: comparator
  // TODO: iterator exception test

  @Test
  @DisplayName("iterator throws exception past end")
  public void iteratorNextNoSuchElement() {
    Iterator<Integer> it = pq.iterator();
    while (it.hasNext()) {
      it.next();
    }
    try {
      it.next();
      fail("Expected NoSuchElementException");
    } catch (NoSuchElementException e) {
      // success
    }
  }
}