package starter;

import exception.IndexException;

/**
 * IndexedList ADT.
 *
 * @param <T> the base type of the items in the IndexedList.
 */
public interface IndexedList<T> extends Iterable<T> {

  /**
   * Change the value at the given index.
   *
   * @param index representing a position in this list.
   * @param value to be written at the given index.
   *              Post: this.get(index) == value
   * @throws IndexException when index < 0 or index >= length.
   */
  void put(int index, T value) throws IndexException;

  /**
   * Retrieve the value stored at the given index.
   *
   * @param index representing a position in this list.
   * @return value at the given index.
   * @throws IndexException when index < 0 or index >= length.
   */
  T get(int index) throws IndexException;

  /**
   * Get the declared capacity of this list.
   *
   * @return the length
   *         Inv: length() >= 0
   */
  int length();
}
