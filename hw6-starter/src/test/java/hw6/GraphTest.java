package hw6;

import exceptions.InsertionException;
import exceptions.PositionException;
import exceptions.RemovalException;
import org.apache.commons.math3.util.OpenIntToDoubleHashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public abstract class GraphTest {

  protected Graph<String, String> graph;

  @BeforeEach
  public void setupGraph() {
    this.graph = createGraph();
  }

  protected abstract Graph<String, String> createGraph();

  // insert(V) tests
  @Test
  @DisplayName("insert(v) returns a vertex with given data")
  public void canGetVertexAfterInsert() {
    Vertex<String> v1 = graph.insert("v1");
    assertEquals("v1", v1.get());
  }

  @Test
  @DisplayName("insert(v) null throws exception")
  public void insertNullVertexThrowsInsertionException() {
    try {
      graph.insert(null);
      fail("The expected exception was not thrown");
    } catch (InsertionException e) {
      return;
    }
  }

  @Test
  @DisplayName("insert(v) duplicate throws exception")
  public void insertDuplicateVertexThrowsInsertionException() {
    Vertex<String> v1 = graph.insert("v1");
    assertEquals("v1", v1.get());
    try {
      graph.insert("v1");
      fail("The expected exception was not thrown");
    } catch (InsertionException e) {
      return;
    }
  }

  @Test
  @DisplayName("insert(v) new vertex has no outgoing edges")
  public void insertedVertexHasNoOutgoingEdgesAfterInsertion() {
    Vertex<String> v1 = graph.insert("v1");
    assertEquals("v1", v1.get());
    Iterable<Edge<String>> outgoing = graph.outgoing(v1);

    int count = 0;
    for (Edge<String> e : outgoing) {
      count++;
    }

    assertEquals(0, count);
  }

  @Test
  @DisplayName("insert(v) new vertex has no incoming edges")
  public void insertedVertexHasNoIncomingEdgesAfterInsertion() {
    Vertex<String> v1 = graph.insert("v1");
    assertEquals("v1", v1.get());
    Iterable<Edge<String>> incoming = graph.incoming(v1);

    int count = 0;
    for (Edge<String> e : incoming) {
      count++;
    }

    assertEquals(0, count);
  }

  @Test
  @DisplayName("insert(v) new vertex has null label")
  public void insertedVertexHasNullLabel() {
    Vertex<String> v1 = graph.insert("v1");
    assertEquals("v1", v1.get());

    assertNull(graph.label(v1));
  }

  @Test
  @DisplayName("insert(v) multiple vertices succeeds")
  public void insertMultipleVertices() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    Vertex<String> v4 = graph.insert("v4");

    assertEquals("v1", v1.get());
    assertEquals("v2", v2.get());
    assertEquals("v3", v3.get());
    assertEquals("v4", v4.get());

    Iterable<Vertex<String>> vertices = graph.vertices();

    int count = 0;
    for (Vertex<String> v : vertices) {
      count++;
    }

    assertEquals(4, count);
  }

  // insert(Vertex, Vertex, Edge) tests
  @Test
  @DisplayName("insert(U, V, e) returns an edge with given data")
  public void canGetEdgeAfterInsert() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e1 = graph.insert(v1, v2, "v1-v2");
    assertEquals("v1-v2", e1.get());
  }

  @Test
  @DisplayName("insert(null, V, e) throws exception")
  public void insertEdgeThrowsPositionExceptionWhenfirstVertexIsNull() {
    try {
      Vertex<String> v = graph.insert("v");
      graph.insert(null, v, "e");
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("insert(V, null, e) throws exception")
  public void insertEdgeThrowsPositionExceptionWhenSecondVertexIsNull() {
    try {
      Vertex<String> v = graph.insert("v");
      graph.insert(v, null, "e");
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("insert(null, null, e) throws exception")
  public void insertEdgeThrowsPositionExceptionWhenBothVertexNull() {
    try {
      graph.insert(null, null, "e");
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("insert(V, V, e) self loop throws exception")
  public void insertEdgeThrowsInsertionExceptionWhenSelfLoop() {
    try {
      Vertex<String> v = graph.insert("v");
      graph.insert(v, v, "e");
      fail("The expected exception was not thrown");
    } catch (InsertionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("insert(V, V, e) duplicate throws exception")
  public void insertEdgeThrowsInsertionExceptionWhenDuplicate() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    graph.insert(v1, v2, "e");
    try {
      graph.insert(v1, v2, "e");
      fail("The expected exception was not thrown");
    } catch (InsertionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("insert(V, V, e) first vertex wrong graph throws exception")
  public void insertEdgeThrowsExceptionWhenFirstVertexWrongGraph() {
    Graph<String, String> g = createGraph();
    Vertex<String> v1 = g.insert("v1");
    Vertex<String> v2 = graph.insert("v2");

    try {
      graph.insert(v1, v2, "e");
      fail("The expected exception was not thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("insert(V, V, e) second vertex wrong graph throws exception")
  public void insertEdgeThrowsExceptionWhenSecondVertexWrongGraph() {
    Graph<String, String> g = createGraph();
    Vertex<String> v1 = g.insert("v1");
    Vertex<String> v2 = graph.insert("v2");

    try {
      graph.insert(v2, v1, "e");
      fail("The expected exception was not thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("insert(V, V, e) both vertex wrong graph throws exception")
  public void insertEdgeThrowsExceptionWhenBothVertexWrongGraph() {
    Graph<String, String> g = createGraph();
    Vertex<String> v1 = g.insert("v1");
    Vertex<String> v2 = g.insert("v2");

    try {
      graph.insert(v1, v2, "e");
      fail("The expected exception was not thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("insert(V, V, e) multiple to same vertex")
  public void insertMultipleEdgesToSameVertex() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    Vertex<String> v4 = graph.insert("v4");

    Edge<String> e1 = graph.insert(v2, v1, "e1");
    Edge<String> e2 = graph.insert(v3, v1, "e2");
    Edge<String> e3 = graph.insert(v4, v1, "e3");

    assertEquals("v2-v1", e1.get());
    assertEquals("v3-v1", e2.get());
    assertEquals("v4-v1", e3.get());
  }

  @Test
  @DisplayName("insert(V, V, e) multiple from same vertex")
  public void insertMultipleEdgesFromSameVertex() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    Vertex<String> v4 = graph.insert("v4");

    Edge<String> e1 = graph.insert(v1, v2, "e1");
    Edge<String> e2 = graph.insert(v1, v3, "e2");
    Edge<String> e3 = graph.insert(v1, v4, "e3");

    assertEquals("v1-v2", e1.get());
    assertEquals("v1-v3", e2.get());
    assertEquals("v1-v4", e3.get());
  }

  @Test
  @DisplayName("insert(V, V, e) multiple interconnecting vertices")
  public void insertMultipleEdgesInterconnectingVertices() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    Vertex<String> v4 = graph.insert("v4");

    Edge<String> e1 = graph.insert(v1, v2, "e1");
    Edge<String> e2 = graph.insert(v2, v3, "e2");
    Edge<String> e3 = graph.insert(v3, v4, "e3");
    Edge<String> e4 = graph.insert(v4, v1, "e4");

    assertEquals("v1-v2", e1.get());
    assertEquals("v2-v3", e2.get());
    assertEquals("v3-v4", e3.get());
    assertEquals("v4-v1", e4.get());
  }

  @Test
  @DisplayName("insert(V, V, e) both directions between two vertices")
  public void insertEdgesBothDirections() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");

    Edge<String> e1 = graph.insert(v1, v2, "e1");
    Edge<String> e2 = graph.insert(v2, v1, "e2");

    assertEquals("v1-v2", e1.get());
    assertEquals("v2-v1", e2.get());
  }

  // remove(Vertex) tests
  @Test
  @DisplayName("remove(V) null throws exception")
  public void removeVertexThrowsExceptionWhenNull() {
    try {
      graph.remove((Vertex<String>) null);
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("remove(V) vertex from different graph throws exception")
  public void removeVertexThrowsExceptionWhenWrongGraph() {
    Graph<String, String> g = createGraph();
    Vertex<String> v1 = g.insert("v1");
    try {
      graph.remove(v1);
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("remove(V) incoming edge throws exception")
  public void removeVertexThrowsExceptionWhenIncomingEdge() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    graph.insert(v1, v2, "e");
    try {
      graph.remove(v2);
      fail("The expected exception was not thrown");
    } catch (RemovalException ex) {
      return;
    }
  }

  @Test
  @DisplayName("remove(V) outgoing edge throws exception")
  public void removeVertexThrowsExceptionWhenOutgoingEdge() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    graph.insert(v1, v2, "e");
    try {
      graph.remove(v1);
      fail("The expected exception was not thrown");
    } catch (RemovalException ex) {
      return;
    }
  }

  @Test
  @DisplayName("remove(V) only vertex returns data")
  public void removeOnlyVertexReturnsCorrectValue() {
    Vertex<String> v1 = graph.insert("v1");
    assertEquals("v1", graph.remove(v1));

    int count = 0;
    for (Vertex<String> v : graph.vertices()) {
      count++;
    }
    assertEquals(0, count);
  }

  @Test
  @DisplayName("remove(V) single vertex returns data")
  public void removeSingleVertexReturnsCorrectValue() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    assertEquals("v1", graph.remove(v1));

    int count = 0;
    for (Vertex<String> v : graph.vertices()) {
      count++;
    }
    assertEquals(1, count);
  }

  @Test
  @DisplayName("remove(V) multiple vertices correct number vertices")
  public void removeMultipleVerticesResultsCorrectNumberOfVertices() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    Vertex<String> v4 = graph.insert("v4");
    graph.insert(v1, v2, "e");

    assertEquals("v3", graph.remove(v3));
    assertEquals("v4", graph.remove(v4));

    int count = 0;
    for (Vertex<String> v : graph.vertices()) {
      count++;
    }
    assertEquals(2, count);
  }

  // TODO add more tests here.
  // TODO insert(V) tests
    // exception if vertex in graph +
    // null vertex exception +
    // inserted vertex has no outgoing +
    // inserted vertex has no incoming +
    // vertex has null label after creation +
    // insert multiple elements all accessible +

  // TODO insert(V, V, E) tests
    // exception invalid first vertex +
    // exception invalid second vertex +
    // exception invalid both vertex +
    // exception self loop +
    // exception duplicate edge +
    // insert one edge succeeds +
    // insert multiple edges succeeds +
    // insert both directions +
    // node is part of different graph +

  // TODO remove(Vertex)
    // exception null vertex +
    // exception incident edges +
    // remove one vertex returns data +
    // vertex no longer in vertices +
    // remove multiple vertices +
    // node is part of different graph +

  @Test
  @DisplayName("remove(E) null throws exception")
  public void removeNullEdgeThrowsException() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v1, v2, "e");

    graph.remove((Vertex<String>) null);
  }

  @Test
  @DisplayName("remove(E) from wrong graph throws exception")
  public void removeEdgeFromWrongGraphThrowsException() {
    Graph<String, String> g = createGraph();
    Vertex<String> v1 = g.insert("v1");
    Vertex<String> v2 = g.insert("v2");
    Edge<String> e = g.insert(v1, v2, "e");

    try {
      graph.remove(e);
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("remove(E) single edge returns correct data")
  public void removeSingleEdgeReturnsData() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v1, v2, "e");

    assertEquals("e", graph.remove(e));

    int count = 0;
    for (Edge<String> edge : graph.edges()) {
      count++;
    }
    assertEquals(0, count);
  }

  @Test
  @DisplayName("remove(E) multiple edges leaves the correct number remaining")
  public void removeMultipleEdgesResultsCorrectNumberOfEdges() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v2");
    Vertex<String> v4 = graph.insert("v2");
    Edge<String> e1 = graph.insert(v1, v2, "e1");
    Edge<String> e2 = graph.insert(v1, v3, "e2");
    Edge<String> e3 = graph.insert(v3, v4, "e3");
    Edge<String> e4 = graph.insert(v2, v4, "e4");

    assertEquals("e1", graph.remove(e1));
    assertEquals("e3", graph.remove(e3));

    int count = 0;
    for (Edge<String> edge : graph.edges()) {
      count++;
    }
    assertEquals(2, count);
  }

  // TODO remove(Edge)
    // exception null edge +
    // remove one edge returns data +
    // edge no longer in edges +
    // remove multiple edges +
    // node is part of different graph +

  @Test
  @DisplayName("vertices() empty graph")
  public void verticesEmptyGraph() {
    int count = 0;
    for (Vertex<String> v : graph.vertices()) {
      count++;
    }
    assertEquals(0, count);
  }

  @Test
  @DisplayName("vertices() single vertex")
  public void verticesSingleVertex() {
    graph.insert("v");
    int count = 0;
    for (Vertex<String> v : graph.vertices()) {
      count++;
    }
    assertEquals(1, count);
  }

  @Test
  @DisplayName("vertices() multiple vertices")
  public void verticesMultipleVertices() {
    graph.insert("v1");
    graph.insert("v2");
    graph.insert("v3");
    graph.insert("v4");

    int count = 0;
    for (Vertex<String> v : graph.vertices()) {
      count++;
    }
    assertEquals(4, count);
  }

  @Test
  @DisplayName("vertices() throws exception past end")
  public void verticesThrowsExceptionPastEnd() {
    graph.insert("v1");
    graph.insert("v2");
    graph.insert("v3");
    graph.insert("v4");

    Iterable<Vertex<String>> vertices = graph.vertices();
    Iterator<Vertex<String>> it = vertices.iterator();
    int count = 0;
    while (it.hasNext()) {
      count++;
      it.next();
    }
    assertEquals(4, count);
    try {
      it.next();
      fail("The expected exception was not thrown");
    } catch (NoSuchElementException e) {
      return;
    }
  }

  @Test
  @DisplayName("vertices() throws exception for removal")
  public void verticesThrowsExceptionForRemoval() {
    graph.insert("v1");
    graph.insert("v2");
    graph.insert("v3");
    graph.insert("v4");

    Iterable<Vertex<String>> vertices = graph.vertices();
    Iterator<Vertex<String>> it = vertices.iterator();
    int count = 0;
    while (it.hasNext()) {
      count++;
      it.next();
      try {
        it.remove();
        fail("The expected exception was not thrown");
      } catch (UnsupportedOperationException e) {
        return;
      }
    }
    assertEquals(4, count);
  }
  // TODO vertices()
    // iterator no vertices +
    // iterator one vertex +
    // iterator multiple vertices +
    // breaks if wander past end +
    // does not allow removal +


  @Test
  @DisplayName("edges() empty graph")
  public void edgesEmptyGraph() {
    int count = 0;
    for (Edge<String> e : graph.edges()) {
      count++;
    }
    assertEquals(0, count);
  }

  @Test
  @DisplayName("edges() single edge")
  public void edgesSingleVertex() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    graph.insert(v1, v2, "e1");
    int count = 0;
    for (Edge<String> e : graph.edges()) {
      count++;
    }
    assertEquals(1, count);
  }

  @Test
  @DisplayName("edges() multiple edges")
  public void edgesMultipleVertices() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    Vertex<String> v4 = graph.insert("v4");
    graph.insert(v1, v2, "e1");
    graph.insert(v2, v3, "e2");
    graph.insert(v1, v4, "e3");
    graph.insert(v3, v4, "e4");

    int count = 0;
    for (Edge<String> e : graph.edges()) {
      count++;
    }
    assertEquals(4, count);
  }

  @Test
  @DisplayName("edges() throws exception past end")
  public void edgesThrowsExceptionPastEnd() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    Vertex<String> v4 = graph.insert("v4");
    graph.insert(v1, v2, "e1");
    graph.insert(v2, v3, "e2");
    graph.insert(v1, v4, "e3");
    graph.insert(v3, v4, "e4");

    Iterable<Edge<String>> edges = graph.edges();
    Iterator<Edge<String>> it = edges.iterator();
    int count = 0;
    while (it.hasNext()) {
      count++;
      it.next();
    }
    assertEquals(4, count);
    try {
      it.next();
      fail("The expected exception was not thrown");
    } catch (NoSuchElementException e) {
      return;
    }
  }

  @Test
  @DisplayName("edges() throws exception for removal")
  public void edgesThrowsExceptionForRemoval() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    Vertex<String> v4 = graph.insert("v4");
    graph.insert(v1, v2, "e1");
    graph.insert(v2, v3, "e2");
    graph.insert(v1, v4, "e3");
    graph.insert(v3, v4, "e4");

    Iterable<Edge<String>> edges = graph.edges();
    Iterator<Edge<String>> it = edges.iterator();
    int count = 0;
    while (it.hasNext()) {
      count++;
      it.next();
      try {
        it.remove();
        fail("The expected exception was not thrown");
      } catch (UnsupportedOperationException e) {
        return;
      }
    }
    assertEquals(4, count);
  }
  // TODO edges()
    // iterator no vertices +
    // iterator one vertex +
    // iterator multiple vertices +
    // breaks if wander past end +
    // does not allow removal +

  // TODO outgoing(Vertex)
    // exception null vertex
    // no outgoing/incoming
    // no outgoing/yes incoming
    // one outgoing/no incoming
    // multiple outgoing/no incoming
    // multiple outgoing/incoming
    // TODO node is part of different graph

  // TODO incoming(Vertex)
    // exception null vertex
    // no outgoing/incoming
    // no incoming/yes outgoing
    // one incoming/no outgoing
    // multiple incoming/no outgoing
    // multiple incoming/outgoing
    // TODO node is part of different graph

  // TODO from(Edge)
    // exception invalid edge
    // returns correct vertex
    // listed as outgoing from vertex
    // TODO node is part of different graph

  // TODO to(Edge)
    // exception invalid edge
    // returns correct vertex
    // listed as incoming to vertex
    // TODO node is part of different graph

  // TODO label(Vertex, Object)
    // exception invalid vertex
    // labels vertex correctly
    // label multiple objects same
    // label multiple objects different
    // TODO node is part of different graph

  // TODO label(Edge, Object)
    // exception invalid edge
    // labels edge correctly
    // label multiple objects same
    // label multiple objects different
    // TODO node is part of different graph

  // TODO label(Vertex)
    // exception invalid vertex
    // null for unlabeled vertex
    // correct label for vertex
    // diff vertices correct label
    // TODO node is part of different graph

  // TODO label(Edge)
    // exception invalid edge
    // null for unlabeled edge
    // correct label for edge
    // diff edges correct label
    // TODO node is part of different graph

  // TODO clearLabels()
    // no edges/vertices
    // only vertices, unlabeled
    // only vertices, labeled
    // multiple edges and vertices, labeled and unlabeled
}
