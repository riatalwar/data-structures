package hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * In addition to the tests in BinarySearchTreeMapTest (and in OrderedMapTest & MapTest),
 * we add tests specific to Treap.
 */
@SuppressWarnings("All")
public class TreapMapTest extends BinarySearchTreeMapTest {

  @Override
  protected Map<String, String> createMap() {
    Random rand = new Random();
    rand.setSeed(0);
    /*  0 -1155484576 (4)
        1  -723955400 (6)
        2  1033096058 (8)
        3 -1690734402 (2)
        4 -1557280266 (3)
        5  1327362106 (9)
        6 -1930858313 (0)
        7   502539523 (7)
        8 -1728529858 (1)
        9  -938301587 (5)
    */
    return new TreapMap<>(rand);
  }


  @Test
  @DisplayName("insert() results in right rotations")
  public void insertRightRotation() {
    map.insert("2", "a");
    map.insert("1", "b");
    map.insert("3", "c");
    map.insert("0", "d");

    String[] expected = new String[]{
            "0:d:-1690734402",
            "null 2:a:-1155484576",
            "null null 1:b:-723955400 3:c:1033096058"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  @DisplayName("insert() results in left rotations")
  public void insertLeftRotation() {
    map.insert("2", "a");
    map.insert("1", "b");
    map.insert("3", "c");
    map.insert("4", "d");

    String[] expected = new String[]{
            "4:d:-1690734402",
            "2:a:-1155484576 null",
            "1:b:-723955400 3:c:1033096058 null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  @DisplayName("insert() results in multiple rotations")
  public void insertMultipleRotation() {
    map.insert("2", "a");
    map.insert("1", "b");
    map.insert("4", "c");
    map.insert("3", "d");

    String[] expected = new String[]{
            "3:d:-1690734402",
            "2:a:-1155484576 4:c:1033096058",
            "1:b:-723955400 null null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  @DisplayName("insert() works in complex case with multiple rotations")
  public void insertComplex() {
    map.insert("8", "a");
    map.insert("6", "b");
    map.insert("1", "c");
    map.insert("3", "d");
    map.insert("7", "e");
    map.insert("2", "f");

    String[] expected = new String[]{
            "3:d:-1690734402",
            "1:c:1033096058 7:e:-1557280266",
            "null 2:f:1327362106 6:b:-723955400 8:a:-1155484576"
    };

    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }


  @Test
  @DisplayName("remove() results in right rotations")
  public void removeOneChildRightRotation() {
    map.insert("2", "a");
    map.insert("1", "b");
    map.insert("3", "c");
    map.insert("4", "d");

    map.remove("4");

    String[] expected = new String[]{
            "2:a:-1155484576",
            "1:b:-723955400 3:c:1033096058"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  @DisplayName("remove() results in left rotations")
  public void removeOneChildLeftRotation() {
    map.insert("2", "a");
    map.insert("1", "b");
    map.insert("3", "c");
    map.insert("0", "d");

    map.remove("0");

    String[] expected = new String[]{
            "2:a:-1155484576",
            "1:b:-723955400 3:c:1033096058"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  @DisplayName("remove() node with two children results in left rotations")
  public void removeTwoChildrenLeftRotation() {
    map.insert("2", "a");
    map.insert("3", "b");
    map.insert("1", "c");

    map.remove("2");

    String[] expected = new String[]{
            "3:b:-723955400",
            "1:c:1033096058 null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  @DisplayName("remove() node with two children results in right rotation")
  public void removeTwoChildrenRightRotation() {
    map.insert("2", "a");
    map.insert("3", "b");
    map.insert("1", "c");
    map.insert("0", "d");

    map.remove("2");

    String[] expected = new String[]{
            "0:d:-1690734402",
            "null 3:b:-723955400",
            "null null 1:c:1033096058 null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  @DisplayName("remove() correctly executes multiple rotations")
  public void removeMultipleRotations() {
    map.insert("8", "a");
    map.insert("6", "b");
    map.insert("1", "c");
    map.insert("3", "d");
    map.insert("7", "e");
    map.insert("2", "f");

    map.remove("3");

    String[] expected = new String[]{
            "7:e:-1557280266",
            "6:b:-723955400 8:a:-1155484576",
            "1:c:1033096058 null null null",
            "null 2:f:1327362106 null null null null null null"
    };

    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
}