package hw3.search;

/**
 * Set implemented using plain Java arrays and transpose-sequential-search heuristic.
 *
 * @param <T> Element type.
 */
public class TransposeArraySet<T> extends ArraySet<T> {

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
    return idx;
  }

}
