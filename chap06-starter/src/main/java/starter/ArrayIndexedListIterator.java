package starter;

import java.util.Iterator;
import java.util.NoSuchElementException;

// copy this whole class and dump it inside the arrayindexedlist
// make private-->encapsulation
// fully hiding implementation and access while maintaining the functionality
// will always be associated w instance of outer class, can see outer class attr
public class ArrayIndexedListIterator<T> implements Iterator<T> {
  private int pos;
  private T[] data;

  public ArrayIndexedListIterator(T[] data) {
    this.data = data;
    this.pos = 0;
  }

  @Override
  public boolean hasNext() {
    return pos < data.length;
  }

  @Override
  public T next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    T elem = data[pos];
    pos++;
    return elem;
  }
}
