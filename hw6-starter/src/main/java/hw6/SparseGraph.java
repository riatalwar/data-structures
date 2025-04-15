package hw6;

import exceptions.InsertionException;
import exceptions.PositionException;
import exceptions.RemovalException;
import java.util.Collections;
import java.util.HashMap;

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
  HashMap<Integer, Edge<E>> edges;
  Integer key;

  /**
   * A constructor for the SparseGraph class
   * initializes member variables of the class.
   */
  public SparseGraph() {
    vertices = new HashMap<>();
    edges = new HashMap<>();
    key = 0;
  }

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

    // create node and add to graph
    VertexNode<V> vertex = new VertexNode<>(v);
    vertex.owner = this;
    vertices.put(v, vertex);

    return vertex;
  }

  @Override
  public Edge<E> insert(Vertex<V> from, Vertex<V> to, E e)
      throws PositionException, InsertionException {
    VertexNode<V> f = convert(from);
    VertexNode<V> t = convert(to);

    // check for self loop and duplicate edges
    if (f.get().equals(t.get())) {
      throw new InsertionException("insertion creates self loop");
    } else if (f.outgoing.containsKey(t.get())) {
      throw new InsertionException("insertion creates duplicate edge");
    }

    // create new edge
    EdgeNode<E> edge = new EdgeNode<>(f, t, e);
    edge.owner = this;
    edge.index = key++;

    // add edge to graph
    f.outgoing.put(t.get(), edge);
    t.incoming.put(f.get(), edge);
    edges.put(edge.index, edge);

    return edge;
  }

  @Override
  public V remove(Vertex<V> v) throws PositionException, RemovalException {
    VertexNode<V> toRemove = convert(v);
    // check for incident edges
    if (!toRemove.incoming.isEmpty() || !toRemove.outgoing.isEmpty()) {
      throw new RemovalException("vertex has incident edges");
    }
    toRemove.owner = null;

    vertices.remove(v.get());
    return v.get();
  }

  @Override
  public E remove(Edge<E> e) throws PositionException {
    // get edge information
    EdgeNode<E> toRemove = convert(e);
    toRemove.owner = null;
    VertexNode<V> f = toRemove.from;
    VertexNode<V> t = toRemove.to;

    // remove edge
    f.outgoing.remove(t.get());
    t.incoming.remove(f.get());
    edges.remove(toRemove.index);

    return toRemove.data;
  }

  @Override
  public Iterable<Vertex<V>> vertices() {
    return Collections.unmodifiableCollection(vertices.values());
  }

  @Override
  public Iterable<Edge<E>> edges() {
    return Collections.unmodifiableCollection(edges.values());
  }

  @Override
  public Iterable<Edge<E>> outgoing(Vertex<V> v) throws PositionException {
    VertexNode<V> vertex = convert(v);
    return Collections.unmodifiableCollection(vertex.outgoing.values());
  }

  @Override
  public Iterable<Edge<E>> incoming(Vertex<V> v) throws PositionException {
    VertexNode<V> vertex = convert(v);
    return Collections.unmodifiableCollection(vertex.incoming.values());
  }

  @Override
  public Vertex<V> from(Edge<E> e) throws PositionException {
    EdgeNode<E> edge = convert(e);
    return edge.from;
  }

  @Override
  public Vertex<V> to(Edge<E> e) throws PositionException {
    EdgeNode<E> edge = convert(e);
    return edge.to;
  }

  @Override
  public void label(Vertex<V> v, Object l) throws PositionException {
    VertexNode<V> vertex = convert(v);
    vertex.label = l;
  }

  @Override
  public void label(Edge<E> e, Object l) throws PositionException {
    EdgeNode<E> edge = convert(e);
    edge.label = l;
  }

  @Override
  public Object label(Vertex<V> v) throws PositionException {
    VertexNode<V> vertex = convert(v);
    return vertex.label;
  }

  @Override
  public Object label(Edge<E> e) throws PositionException {
    EdgeNode<E> edge = convert(e);
    return edge.label;
  }

  @Override
  public void clearLabels() {
    // for each vertex clear label and all outgoing labels
    for (Vertex<V> v : vertices.values()) {
      VertexNode<V> vn = convert(v);
      vn.label = null;
      for (Edge<E> e : outgoing(vn)) {
        (convert(e)).label = null;
      }
    }
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
    HashMap<V, EdgeNode<E>> incoming;
    HashMap<V, EdgeNode<E>> outgoing;
    // TODO You may need to add fields/methods here!

    VertexNode(V v) {
      this.data = v;
      this.label = null;
      incoming = new HashMap<>();
      outgoing = new HashMap<>();
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
    int index;
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
