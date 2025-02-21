package starter;

import java.util.Iterator;

public class ArraySet<T> implements Set<T> {

  private int numElements;
  private T[] data;

  public ArraySet() {
    data = (T[]) new Object[10];
  }

  @Override
  public void insert(T t) {
    // TODO Implement Me!
  }

  @Override
  public void remove(T t) {
    // TODO Implement Me!
    for (int i = 0; i < numElements; i++) {
      if (data[i].equals(t)) {
        data[i] = data[--numElements];
        data[numElements] = null;
        return;
      }
    }
  }

  @Override
  public boolean has(T t) {
    // TODO Implement Me!
    return false;
  }

  @Override
  public int size() {
    // TODO Implement Me!
    return 0;
  }

  @Override
  public Iterator<T> iterator() {
    // TODO Implement Me!
    return null;
  }
}
