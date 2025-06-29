package hw4;

import org.junit.jupiter.api.DisplayName;
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

  @Test
  @DisplayName("remove() removes node with no children")
  public void removeNoChildren() {
    map.insert("2", "a");
    map.insert("1", "b");
    map.insert("3", "c");
    map.insert("4", "d");

    map.remove("4");

    String[] expected = new String[]{
            "2:a",
            "1:b 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  @DisplayName("remove() removes node with one child")
  public void removeOneChild() {
    map.insert("2", "a");
    map.insert("1", "b");
    map.insert("3", "c");
    map.insert("4", "d");

    map.remove("3");

    String[] expected = new String[]{
            "2:a",
            "1:b 4:d"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  @DisplayName("remove() removes node with two children")
  public void removeTwoChildren() {
    map.insert("2", "a");
    map.insert("1", "b");
    map.insert("3", "c");
    map.insert("4", "d");

    map.remove("2");

    String[] expected = new String[]{
            "3:c",
            "1:b 4:d"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  @DisplayName("remove() accurately changes the size of the tree.")
  public void removeChangesSize() {
    map.insert("2", "a");
    map.insert("1", "b");
    map.insert("3", "c");
    map.insert("4", "d");
    assertEquals(4, map.size());

    map.remove("4");
    assertEquals(3, map.size());

    map.remove("1");
    assertEquals(2, map.size());
  }

  @Test
  @DisplayName("get() correctly returns value of key.")
  public void hasCorrectlyFindsKey() {
    map.insert("2", "a");
    map.insert("1", "b");
    map.insert("3", "c");
    map.insert("4", "d");

    assertEquals("a", map.get("2"));
    assertEquals("b", map.get("1"));
    assertEquals("d", map.get("4"));
  }

  @Test
  @DisplayName("size is correct after series of operations.")
  public void sizeAccurateAfterSeriesOperations() {
    map.insert("3", "a");
    map.insert("2", "b");
    map.insert("1", "c");
    map.remove("1");
    map.insert("4", "d");
    map.remove("3");

    assertEquals(2, map.size());
  }

}
