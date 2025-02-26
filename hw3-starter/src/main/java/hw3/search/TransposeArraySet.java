package hw3.search;

/**
 * Set implemented using plain Java arrays and transpose-sequential-search heuristic.
 *
 * @param <T> Element type.
 */
public class TransposeArraySet<T> extends ArraySet<T> {

  /**
   * Execute series of operations on set.
   * @param args - main
   */
  public static void main(String[] args) {
    TransposeArraySet<Integer> ls = new TransposeArraySet<>();

    ls.insert(2);
    ls.insert(3);
    ls.insert(8);
    ls.insert(4);
    ls.insert(6);

    ls.remove(1);
    ls.remove(3);

    ls.insert(5);

    ls.has(3);
    ls.has(8);
    ls.has(5);
  }

  // transpose-sequential-search heuristic
  @Override
  protected int find(T t) {
    int idx = super.find(t);
    // if not found or already front
    if (idx <= 0) {
      return idx;
    }

    // swap with prev if not front
    data[idx] = data[idx - 1];
    data[idx - 1] = t;
    return idx - 1;
  }

}
