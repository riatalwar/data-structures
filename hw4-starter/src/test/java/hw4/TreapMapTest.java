package hw4;

/**
 * In addition to the tests in BinarySearchTreeMapTest (and in OrderedMapTest & MapTest),
 * we add tests specific to Treap.
 */
@SuppressWarnings("All")
public class TreapMapTest extends BinarySearchTreeMapTest {

  @Override
  protected Map<String, String> createMap() {
    return new TreapMap<>();
  }

  // insert right rotation
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
  // insert left rotation
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
  // insert multiple rotations
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

  // remove right rotation
  // remove left rotation
  // remove multiple rotations

  // TODO Add tests
  //  (think about how you might write tests while randomness is involved in TreapMap implementation!)

}