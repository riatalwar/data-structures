package starter;

import java.sql.Array;

/**
 * An implementation of IndexedList that takes a default value
 * to plaster over the entire data structure.
 */
public class ArrayIndexedList<E> implements IndexedList<E> {
  private E[] data;

  /**
   * Construct an ArrayIndexedList with given size and default value.
   *
   * @param size         the capacity of this list.
   * @param defaultValue an integer to plaster over the entire list.
   */
  @SuppressWarnings("unchecked")
  public ArrayIndexedList(int size, E defaultValue) {
    data = (E[]) new Object[size]; // new E[size] does not work
    for (int i = 0; i < size; i++) {
      data[i] = defaultValue;
    }
  }

  @Override
  public void put(int index, E value) {
    if (index >= data.length) {
      return;
    }
    data[index] = value;
  }

  @Override
  public E get(int index) {
    return data[index]; // TODO Implement me!
  }

  @Override
  public int length() {
    return data.length; // TODO Implement me!
  }
}