package hw6;

import exceptions.InsertionException;
import exceptions.PositionException;
import exceptions.RemovalException;

import java.util.HashMap;
import java.util.List;

/**
 * An implementation of Graph ADT using incidence lists
 * for sparse graphs where most nodes aren't connected.
 *
 * @param <V> Vertex element type.
 * @param <E> Edge element type.
 */
public class SparseGraph<V, E> implements Graph<V, E> {

  // TODO You may need to add fields/constructor here!
  HashMap<V, Vertex<V>> vertices;

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
    if (v == null) {
      throw new InsertionException("vertex is null");
    } else if (vertices.containsKey(v)) {
      throw new InsertionException("vertex already in graph");
    }
    // TODO Implement me!
    return null;
  }

  @Override
  public Edge<E> insert(Vertex<V> from, Vertex<V> to, E e)
      throws PositionException, InsertionException {
    VertexNode<V> f = convert(from);
    VertexNode<V> t = convert(to);

    if (f.data == t.data) {
      throw new InsertionException("insertion creates self loop");
    } else if (true/*f.outgoing.contains(f-t)*/) {
      throw new InsertionException("insertion creates duplicate edge");
    }
    // TODO Implement me!
    return null;
  }

  @Override
  public V remove(Vertex<V> v) throws PositionException, RemovalException {
    // TODO Implement me!
    VertexNode<V> toRemove = convert(v);
    if (true /* vertex.incoming or vertex.outgoing != null */) {
      throw new RemovalException("vertex has incident edges");
    }
    return null;
  }

  @Override
  public E remove(Edge<E> e) throws PositionException {
    // TODO Implement me!
    EdgeNode<E> toRemove = convert(e);
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
    VertexNode<V> vertex = convert(v);
    // TODO Implement me!
    return null;
  }

  @Override
  public Iterable<Edge<E>> incoming(Vertex<V> v) throws PositionException {
    VertexNode<V> vertex = convert(v);
    // TODO Implement me!
    return null;
  }

  @Override
  public Vertex<V> from(Edge<E> e) throws PositionException {
    EdgeNode<E> edge = convert(e);
    // TODO Implement me!
    return edge.from;
  }

  @Override
  public Vertex<V> to(Edge<E> e) throws PositionException {
    EdgeNode<E> edge = convert(e);
    // TODO Implement me!
    return edge.to;
  }

  @Override
  public void label(Vertex<V> v, Object l) throws PositionException {
    VertexNode<V> vertex = convert(v);
    // TODO Implement me!
    vertex.label = l;
  }

  @Override
  public void label(Edge<E> e, Object l) throws PositionException {
    EdgeNode<E> edge = convert(e);
    // TODO Implement me!
    edge.label = l;
  }

  @Override
  public Object label(Vertex<V> v) throws PositionException {
    VertexNode<V> vertex = convert(v);
    // TODO Implement me!
    return vertex.label;
  }

  @Override
  public Object label(Edge<E> e) throws PositionException {
    EdgeNode<E> edge = convert(e);
    // TODO Implement me!
    return edge.label;
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
    HashMap<V, Edge<E>> incoming;
    HashMap<V, Edge<E>> outgoing;
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
