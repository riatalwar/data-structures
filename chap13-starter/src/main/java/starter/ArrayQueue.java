package starter;

import exceptions.EmptyException;

/**
 * Array Implementation of the Queue ADT.
 *
 * @param <T> base type.
 */
public class ArrayQueue<T> implements Queue<T> {
  int front, back;
  T[] queue;

  @Override
  public void enqueue(T value) {
    // TODO: Implement me!
    
  }

  @Override
  public void dequeue() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    queue[front] = null;
    front = (front + 1) % queue.length;
  }

  @Override
  public T front() throws EmptyException {
    if (empty()) {
      throw new EmptyException();
    }
    return queue[front]; // TODO: Implement me!
  }

  @Override
  public boolean empty() {
    return queue[front] == null; // TODO: Implement me!
  }
}
