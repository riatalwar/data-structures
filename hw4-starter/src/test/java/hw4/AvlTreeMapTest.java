package hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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

    assertEquals(4, map.size());
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

    assertEquals(1, map.size());
  }

  @Test
  @DisplayName("insert() correctly adds nodes to tree.")
  public void insertCorrectlyAddsNode() {
    map.insert("2", "a");
    map.insert("1", "b");
    map.insert("3", "c");
    map.insert("4", "d");

    assertEquals(4, map.size());
    String[] expected = new String[]{
            "2:a",
            "1:b 3:c",
            "null null null 4:d"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  @DisplayName("insert() correctly executes a left rotation.")
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

  @Test
  @DisplayName("insert() correctly executes a right rotation.")
  public void insertRightRotation() {
    map.insert("3", "a");
    map.insert("2", "b");
    map.insert("1", "c");

    String[] expected = new String[]{
            "2:b",
            "1:c 3:a"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  @DisplayName("insert() correctly executes a left right rotation.")
  public void insertLeftRightRotation() {
    map.insert("3", "a");
    map.insert("1", "b");
    map.insert("2", "c");

    String[] expected = new String[]{
            "2:c",
            "1:b 3:a"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  @DisplayName("insert() correctly executes a right left rotation.")
  public void insertRightLeftRotation() {
    map.insert("1", "a");
    map.insert("3", "b");
    map.insert("2", "c");

    String[] expected = new String[]{
            "2:c",
            "1:a 3:b"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  @DisplayName("insert() accurately changes size of tree.")
  public void insertChangesSize() {
    assertEquals(0, map.size());

    map.insert("2", "a");
    map.insert("1", "b");

    assertEquals(2, map.size());

    map.insert("3", "c");
    map.insert("4", "d");

    assertEquals(4, map.size());
  }

  @Test
  @DisplayName("remove() throws IllegalArgumentException for null keys.")
  public void removeThrowsExceptionNullKey() {
    try {
      map.remove(null);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // success
    }
  }

  @Test
  @DisplayName("remove() throws IllegalArgumentException for nonexistent keys.")
  public void removeThrowsExceptionNotFoundKey() {
    map.insert("2", "a");
    map.insert("1", "b");
    map.insert("3", "c");
    map.insert("4", "d");

    try {
      map.remove("5");
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // success
    }

    assertEquals(4, map.size());
    String[] expected = new String[]{
            "2:a",
            "1:b 3:c",
            "null null null 4:d"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());

  }

  @Test
  @DisplayName("remove() correctly removes a node from the tree.")
  public void removeCorrectlyRemovesNode() {
    map.insert("2", "a");
    map.insert("1", "b");
    map.insert("3", "c");
    map.insert("4", "d");

    map.remove("4");

    assertEquals(3, map.size());
    String[] expected = new String[]{
            "2:a",
            "1:b 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  public void removeCorrectlyRemovesRoot(){}

  @Test
  @DisplayName("remove() correctly executes a left rotation.")
  public void removeLeftRotation() {
    map.insert("2", "a");
    map.insert("1", "b");
    map.insert("3", "c");
    map.insert("4", "d");

    map.remove("1");

    assertEquals(3, map.size());
    String[] expected = new String[]{
            "3:c",
            "2:a 4:d"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  @DisplayName("remove() correctly executes a right rotation.")
  public void removeRightRotation() {
    map.insert("3", "a");
    map.insert("2", "b");
    map.insert("1", "c");
    map.insert("4", "d");

    map.remove("4");

    assertEquals(3, map.size());
    String[] expected = new String[]{
            "2:b",
            "1:c 3:a"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  @DisplayName("remove() correctly executes a left right rotation.")
  public void removeLeftRightRotation() {
    map.insert("3", "a");
    map.insert("1", "b");
    map.insert("2", "c");
    map.insert("4", "d");

    map.remove("4");

    assertEquals(3, map.size());
    String[] expected = new String[]{
            "2:c",
            "1:b 3:a"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  @DisplayName("remove() correctly executes a right left rotation.")
  public void removeRightLeftRotation() {
    map.insert("2", "a");
    map.insert("1", "b");
    map.insert("4", "c");
    map.insert("3", "d");

    map.remove("1");

    assertEquals(3, map.size());
    String[] expected = new String[]{
            "3:d",
            "2:a 4:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

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
