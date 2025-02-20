package hw3.search;

/**
 * Set implemented using plain Java arrays and transpose-sequential-search heuristic.
 *
 * @param <T> Element type.
 */
public class TransposeArraySet<T> extends ArraySet<T> {

  // TODO: incorporate the transpose-sequential-search heuristic
  //  each time a value is accessed. Override the relevant method(s) from ArraySet.
  @Override
  protected int find(T t) {
    int idx = super.find(t);
    if (idx <= 0) {
      return idx;
    }
    data[idx] = data[idx - 1];
    data[idx - 1] = t;
    return idx;
  }

}
