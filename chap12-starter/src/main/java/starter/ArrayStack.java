package starter;

import exceptions.EmptyException;

/**
 * Stack ADT implemented using an array.
 *
 * @param <T> base type.
 */
public class ArrayStack<T> implements Stack<T> {
  private T[] data;
  private int numElements;

  /**
   * Construct an ArrayStack.
   */
  public ArrayStack() {
  data = (T[]) new Object[10];
  }

  @Override
  public boolean empty() {
    // TODO: Implement me!
    return data[0] == null;
  }

  @Override
  public T top() throws EmptyException {
    // TODO: Implement me!
    if (empty()) {
      throw new EmptyException();
    }
    return data[numElements - 1];
  }

  @Override
  public void pop() throws EmptyException {
    // TODO: Implement me!
    if (empty()) {
      throw new EmptyException();
    }
    data[numElements - 1] = null;
    numElements--;
  }

  @Override
  public void push(T value) {
    // TODO: Implement me!
    if (numElements == data.length) {
      grow();
    }
    data[numElements] = value;
    numElements++;
  }

  private void grow() {
    T[] newArr = (T[]) new Object[data.length * 2];
    for (int i = 0; i < numElements; i++) {
      newArr[i] = data[i];
    }

    data = newArr;
    }
}
