package hw4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * In addition to the tests in BinarySearchTreeMapTest (and in OrderedMapTest & MapTest),
 * we add tests specific to AVL Tree.
 */
@SuppressWarnings("All")
public class AvlTreeMapTest extends BinarySearchTreeMapTest {

  @Override
  protected Map<String, String> createMap() {
    return new AvlTreeMap<>();
  }

  @Test
  public void insertLeftRotation() {
    map.insert("1", "a");
    // System.out.println(avl.toString());
    // must print
    /*
        1:a
     */

    map.insert("2", "b");
    // System.out.println(avl.toString());
    // must print
    /*
        1:a,
        null 2:b
     */

    map.insert("3", "c"); // it must do a left rotation here!
    // System.out.println(avl.toString());
    // must print
    /*
        2:b,
        1:a 3:c
     */

    String[] expected = new String[]{
        "2:b",
        "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  // TODO Add more tests

  @Test
  @DisplayName("insert() throws IllegalArgumentException for duplicate keys.")
  public void insertThrowsExceptionDuplicateKey() {
    map.insert("2", "a");
    map.insert("1", "b");
    map.insert("3", "c");
    map.insert("4", "d");

    try {
      map.insert("2", "e");
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // success
    }

    try {
      map.insert("1", "f");
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // success
    }

    try {
      map.insert("4", "g");
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // success
    }

    assertEquals(map.size(), 4);
  }

  @Test
  @DisplayName("insert() throws IllegalArgumentException for null keys.")
  public void insertThrowsExceptionNullKey() {
    try {
      map.insert(null, "a");
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // success
    }

    map.insert("2", "a");

    try {
      map.insert(null, "b");
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // success
    }

    assertEquals(map.size(), 1);
  }

  public void insertCorrectlyAddsNode(){}
  public void insertCorrectlyRemovesRoot(){}
  public void insertResultsInBalancedTree(){}
  public void insertChangesSize(){}

  public void removeThrowsExceptionNullKey(){}
  public void removeThrowsExceptionNotFoundKey(){}
  public void removeCorrectlyRemovesNode(){}
  public void removeCorrectlyRemovesRoot(){}
  public void removeResultsInBalancedTree(){}
  public void removeChangesSize(){}

  public void putThrowsExceptionNullKey(){}
  public void putThrowsExceptionNotFoundKey(){}
  public void putCorrectlyChangesValue(){}
  public void putDoesNotChangeSize(){}

  public void getThrowsExceptionNullKey(){}
  public void getThrowsExceptionNotFoundKey(){}
  public void getReturnsCorrectValue(){}
  public void getDoesNotChangeSize(){}

  public void hasCorrectlyFindsKey(){}

  public void sizeZeroOnConstruction(){}
  public void sizeAccurateAfterSeriesOperations(){}

}
