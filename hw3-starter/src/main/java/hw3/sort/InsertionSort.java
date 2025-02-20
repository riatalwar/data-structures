package hw3.sort;


/**
 * The Insertion Sort algorithm, with minimizing swaps optimization.
 *
 * @param <T> Element type.
 */
public final class InsertionSort<T extends Comparable<T>>
    implements SortingAlgorithm<T> {

  @Override
  public void sort(IndexedList<T> indexedList) {
    // TODO: Implement me
    int len = indexedList.length();
    int i = 1;  // track beginning of unsorted portion
    while (i < len) {
      T x = indexedList.get(i);
      // insert x into sorted order
      int j = i;
      while (j > 0 && indexedList.get(j - 1).compareTo(x) > 0) {
        indexedList.put(j, indexedList.get(j - 1));
        j--;
      }
      indexedList.put(j, x);
      i++;
    }
  }

  @Override
  public String name() {
    return "Insertion Sort";
  }
}
