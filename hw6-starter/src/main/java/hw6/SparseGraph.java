package hw6;

import exceptions.InsertionException;
import exceptions.PositionException;
import exceptions.RemovalException;

/**
 * An implementation of Graph ADT using incidence lists
 * for sparse graphs where most nodes aren't connected.
 *
 * @param <V> Vertex element type.
 * @param <E> Edge element type.
 */
public class SparseGraph<V, E> implements Graph<V, E> {

  // TODO You may need to add fields/constructor here!

  // Converts the vertex back to a VertexNode to use internally
  private VertexNode<V> convert(Vertex<V> v) throws PositionException {
    try {
      VertexNode<V> gv = (VertexNode<V>) v;
      if (gv.owner != this) {
        throw new PositionException();
      }
      return gv;
    } catch (NullPointerException | ClassCastException ex) {
      throw new PositionException();
    }
  }

  // Converts and edge back to a EdgeNode to use internally
  private EdgeNode<E> convert(Edge<E> e) throws PositionException {
    try {
      EdgeNode<E> ge = (EdgeNode<E>) e;
      if (ge.owner != this) {
        throw new PositionException();
      }
      return ge;
    } catch (NullPointerException | ClassCastException ex) {
      throw new PositionException();
    }
  }

  @Override
  public Vertex<V> insert(V v) throws InsertionException {
    // TODO Implement me!
    return null;
  }

  @Override
  public Edge<E> insert(Vertex<V> from, Vertex<V> to, E e)
      throws PositionException, InsertionException {
    // TODO Implement me!
    return null;
  }

  @Override
  public V remove(Vertex<V> v) throws PositionException, RemovalException {
    // TODO Implement me!
    return null;
  }

  @Override
  public E remove(Edge<E> e) throws PositionException {
    // TODO Implement me!
    return null;
  }

  @Override
  public Iterable<Vertex<V>> vertices() {
    // TODO Implement me!
    return null;
  }

  @Override
  public Iterable<Edge<E>> edges() {
    // TODO Implement me!
    return null;
  }

  @Override
  public Iterable<Edge<E>> outgoing(Vertex<V> v) throws PositionException {
    // TODO Implement me!
    return null;
  }

  @Override
  public Iterable<Edge<E>> incoming(Vertex<V> v) throws PositionException {
    // TODO Implement me!
    return null;
  }

  @Override
  public Vertex<V> from(Edge<E> e) throws PositionException {
    // TODO Implement me!
    return null;
  }

  @Override
  public Vertex<V> to(Edge<E> e) throws PositionException {
    // TODO Implement me!
    return null;
  }

  @Override
  public void label(Vertex<V> v, Object l) throws PositionException {
    // TODO Implement me!
  }

  @Override
  public void label(Edge<E> e, Object l) throws PositionException {
    // TODO Implement me!
  }

  @Override
  public Object label(Vertex<V> v) throws PositionException {
    // TODO Implement me!
    return null;
  }

  @Override
  public Object label(Edge<E> e) throws PositionException {
    // TODO Implement me!
    return null;
  }

  @Override
  public void clearLabels() {
    // TODO Implement me!
  }

  @Override
  public String toString() {
    GraphPrinter<V, E> gp = new GraphPrinter<>(this);
    return gp.toString();
  }

  // Class for a vertex of type V
  private final class VertexNode<V> implements Vertex<V> {
    V data;
    Graph<V, E> owner;
    Object label;
    // TODO You may need to add fields/methods here!

    VertexNode(V v) {
      this.data = v;
      this.label = null;
    }

    @Override
    public V get() {
      return this.data;
    }
  }

  //Class for an edge of type E
  private final class EdgeNode<E> implements Edge<E> {
    E data;
    Graph<V, E> owner;
    VertexNode<V> from;
    VertexNode<V> to;
    Object label;
    // TODO You may need to add fields/methods here!

    // Constructor for a new edge
    EdgeNode(VertexNode<V> f, VertexNode<V> t, E e) {
      this.from = f;
      this.to = t;
      this.data = e;
      this.label = null;
    }

    @Override
    public E get() {
      return this.data;
    }
  }
}
