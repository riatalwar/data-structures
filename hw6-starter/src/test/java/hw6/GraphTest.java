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

    assertEquals("e1", e1.get());
    assertEquals("e2", e2.get());
    assertEquals("e3", e3.get());
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

    assertEquals("e1", e1.get());
    assertEquals("e2", e2.get());
    assertEquals("e3", e3.get());
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

    assertEquals("e1", e1.get());
    assertEquals("e2", e2.get());
    assertEquals("e3", e3.get());
    assertEquals("e4", e4.get());
  }

  @Test
  @DisplayName("insert(V, V, e) both directions between two vertices")
  public void insertEdgesBothDirections() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");

    Edge<String> e1 = graph.insert(v1, v2, "e1");
    Edge<String> e2 = graph.insert(v2, v1, "e2");

    assertEquals("e1", e1.get());
    assertEquals("e2", e2.get());
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

  // remove(Edge) tests
  @Test
  @DisplayName("remove(E) null throws exception")
  public void removeNullEdgeThrowsException() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v1, v2, "e");

    try {
      graph.remove((Vertex<String>) null);
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
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
    Vertex<String> v3 = graph.insert("v3");
    Vertex<String> v4 = graph.insert("v4");
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

  // vertices() tests
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

  // edges() tests
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

  // outgoing(Vertex) tests
  @Test
  @DisplayName("outgoing(V) throws exception past end")
  public void outgoingThrowsExceptionPastEnd() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    Vertex<String> v4 = graph.insert("v4");
    graph.insert(v1, v2, "e1");
    graph.insert(v1, v3, "e2");
    graph.insert(v1, v4, "e3");

    Iterable<Edge<String>> out = graph.outgoing(v1);
    Iterator<Edge<String>> it = out.iterator();
    int count = 0;
    while (it.hasNext()) {
      count++;
      it.next();
    }
    assertEquals(3, count);
    try {
      it.next();
      fail("The expected exception was not thrown");
    } catch (NoSuchElementException e) {
      return;
    }
  }

  @Test
  @DisplayName("outgoing(V) throws exception for vertex in wrong graph")
  public void outgoingThrowsExceptionVertexFromDifferentGraph() {
    Graph<String, String> g = createGraph();
    Vertex<String> v1 = g.insert("v1");

    try {
      Iterable<Edge<String>> out = graph.outgoing(v1);
      fail("The expected exception was not thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("outgoing(V) throws exception for null vertex")
  public void outgoingThrowsExceptionNullVertex() {
    try {
      Iterable<Edge<String>> out = graph.outgoing(null);
      fail("The expected exception was not thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("outgoing(V) throws exception for removal")
  public void outgoingThrowsExceptionForRemoval() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    Vertex<String> v4 = graph.insert("v4");
    graph.insert(v1, v2, "e1");
    graph.insert(v1, v3, "e2");
    graph.insert(v1, v4, "e3");

    Iterable<Edge<String>> out = graph.outgoing(v1);
    Iterator<Edge<String>> it = out.iterator();
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
    assertEquals(3, count);
  }

  @Test
  @DisplayName("outgoing(V) succeeds when no edges")
  public void outgoingVertexNoEdges() {
    Vertex<String> v1 = graph.insert("v1");

    int count = 0;
    for (Edge<String> e : graph.outgoing(v1)) {
      count++;
    }
    assertEquals(0, count);
  }

  @Test
  @DisplayName("outgoing(V) succeeds when one outgoing")
  public void outgoingVertexOneOutgoingEdge() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    graph.insert(v1, v2, "e");

    int count = 0;
    for (Edge<String> e : graph.outgoing(v1)) {
      count++;
    }
    assertEquals(1, count);
  }

  @Test
  @DisplayName("outgoing(V) succeeds when only incoming edges")
  public void outgoingVertexOnlyIncoming() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    graph.insert(v2, v1, "e");
    graph.insert(v3, v1, "e");

    int count = 0;
    for (Edge<String> e : graph.outgoing(v1)) {
      count++;
    }
    assertEquals(0, count);
  }

  @Test
  @DisplayName("outgoing(V) succeeds when only outgoing edges")
  public void outgoingVertexOnlyOutgoing() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    graph.insert(v1, v2, "e");
    graph.insert(v1, v3, "e");

    int count = 0;
    for (Edge<String> e : graph.outgoing(v1)) {
      count++;
    }
    assertEquals(2, count);
  }

  @Test
  @DisplayName("outgoing(V) succeeds when both outgoing and incoming edges")
  public void outgoingVertexOutgoingAndIncoming() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    graph.insert(v2, v1, "e");
    graph.insert(v3, v1, "e");
    graph.insert(v1, v2, "e");
    graph.insert(v1, v3, "e");

    int count = 0;
    for (Edge<String> e : graph.outgoing(v1)) {
      count++;
    }
    assertEquals(2, count);
  }
  // TODO outgoing(Vertex)
    // exception null vertex +
    // no outgoing/incoming +
    // no outgoing/yes incoming +
    // one outgoing/no incoming +
    // multiple outgoing/no incoming +
    // multiple outgoing/incoming +
    // exception past end +
    // node is part of different graph +

  // incoming(Vertex) tests
  @Test
  @DisplayName("incoming(V) throws exception past end")
  public void incomingThrowsExceptionPastEnd() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    Vertex<String> v4 = graph.insert("v4");
    graph.insert(v2, v1, "e1");
    graph.insert(v3, v1, "e2");
    graph.insert(v4, v1, "e3");

    Iterable<Edge<String>> in = graph.incoming(v1);
    Iterator<Edge<String>> it = in.iterator();
    int count = 0;
    while (it.hasNext()) {
      count++;
      it.next();
    }
    assertEquals(3, count);
    try {
      it.next();
      fail("The expected exception was not thrown");
    } catch (NoSuchElementException e) {
      return;
    }
  }

  @Test
  @DisplayName("incoming(V) throws exception for vertex in wrong graph")
  public void incomingThrowsExceptionVertexFromDifferentGraph() {
    Graph<String, String> g = createGraph();
    Vertex<String> v1 = g.insert("v1");

    try {
      Iterable<Edge<String>> out = graph.incoming(v1);
      fail("The expected exception was not thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("incoming(V) throws exception for null vertex")
  public void incomingThrowsExceptionNullVertex() {
    try {
      Iterable<Edge<String>> out = graph.incoming(null);
      fail("The expected exception was not thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("incoming(V) throws exception for removal")
  public void incomingThrowsExceptionForRemoval() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    Vertex<String> v4 = graph.insert("v4");
    graph.insert(v2, v1, "e1");
    graph.insert(v3, v1, "e2");
    graph.insert(v4, v1, "e3");

    Iterable<Edge<String>> out = graph.incoming(v1);
    Iterator<Edge<String>> it = out.iterator();
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
    assertEquals(3, count);
  }

  @Test
  @DisplayName("incoming(V) succeeds when no edges")
  public void incomingVertexNoEdges() {
    Vertex<String> v1 = graph.insert("v1");

    int count = 0;
    for (Edge<String> e : graph.incoming(v1)) {
      count++;
    }
    assertEquals(0, count);
  }

  @Test
  @DisplayName("incoming(V) succeeds when one incoming")
  public void incomingVertexOneIncomingEdge() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    graph.insert(v2, v1, "e");

    int count = 0;
    for (Edge<String> e : graph.incoming(v1)) {
      count++;
    }
    assertEquals(1, count);
  }

  @Test
  @DisplayName("incoming(V) succeeds when only incoming edges")
  public void incomingVertexOnlyIncoming() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    graph.insert(v2, v1, "e");
    graph.insert(v3, v1, "e");

    int count = 0;
    for (Edge<String> e : graph.incoming(v1)) {
      count++;
    }
    assertEquals(2, count);
  }

  @Test
  @DisplayName("incoming(V) succeeds when only outgoing edges")
  public void incomingVertexOnlyOutgoing() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    graph.insert(v1, v2, "e");
    graph.insert(v1, v3, "e");

    int count = 0;
    for (Edge<String> e : graph.incoming(v1)) {
      count++;
    }
    assertEquals(0, count);
  }

  @Test
  @DisplayName("incoming(V) succeeds when both outgoing and incoming edges")
  public void incomingVertexOutgoingAndIncoming() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    graph.insert(v2, v1, "e");
    graph.insert(v3, v1, "e");
    graph.insert(v1, v2, "e");
    graph.insert(v1, v3, "e");

    int count = 0;
    for (Edge<String> e : graph.incoming(v1)) {
      count++;
    }
    assertEquals(2, count);
  }
  // TODO incoming(Vertex)
    // exception null vertex +
    // no outgoing/incoming +
    // no incoming/yes outgoing +
    // one incoming/no outgoing +
    // multiple incoming/no outgoing +
    // multiple incoming/outgoing +
    // exception past end +
    // node is part of different graph +

  // from(Edge) tests
  @Test
  @DisplayName("from(E) throws exception null edge")
  public void fromExceptionNullEdge() {
    try {
      graph.from(null);
      fail("The expected exception was not thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("from(E) throws exception edge from different graph")
  public void fromExceptionEdgeFromDifferentGraph() {
    Graph<String, String> g = createGraph();
    Vertex<String> v1 = g.insert("v1");
    Vertex<String> v2 = g.insert("v2");
    Edge<String> e = g.insert(v1, v2, "e");
    try {
      graph.from(e);
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("from(E) returns correct origin")
  public void fromReturnsCorrectVertex() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v1, v2, "e");
    assertEquals(v1, graph.from(e));
  }
  // TODO from(Edge)
    // exception null edge +
    // returns correct vertex +
    // node is part of different graph +

  // to(Edge) tests
  @Test
  @DisplayName("to(E) throws exception null edge")
  public void toExceptionNullEdge() {
    try {
      graph.to(null);
      fail("The expected exception was not thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("to(E) throws exception edge from different graph")
  public void toExceptionEdgeFromDifferentGraph() {
    Graph<String, String> g = createGraph();
    Vertex<String> v1 = g.insert("v1");
    Vertex<String> v2 = g.insert("v2");
    Edge<String> e = g.insert(v1, v2, "e");
    try {
      graph.to(e);
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("to(E) returns correct origin")
  public void toReturnsCorrectVertex() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v1, v2, "e");
    assertEquals(v2, graph.to(e));
  }
  // TODO to(Edge)
    // exception null edge +
    // returns correct vertex +
    // node is part of different graph +

  // label(Vertex, Object) tests
  @Test
  @DisplayName("label(V, O) throws exception null edge")
  public void addLabelExceptionNullVertex() {
    try {
      graph.label((Vertex<String>) null, "");
      fail("The expected exception was not thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("label(V, O) throws exception vertex from different graph")
  public void addLabelExceptionVertexFromDifferentGraph() {
    Graph<String, String> g = createGraph();
    Vertex<String> v1 = g.insert("v1");
    try {
      graph.label(v1, "v1");
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("label(V, O) returns correct origin")
  public void addLabelCorrectlyAddsLabelToVertex() {
    Vertex<String> v1 = graph.insert("v1");
    graph.label(v1, "label");
    assertEquals("label", graph.label(v1));
  }

  @Test
  @DisplayName("label(V, O) can give multiple objects same label")
  public void addLabelMultipleVerticesSame() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    graph.label(v1, "label");
    graph.label(v2, "label");
    graph.label(v3, "label");
    assertEquals("label", graph.label(v1));
    assertEquals("label", graph.label(v2));
    assertEquals("label", graph.label(v3));
  }

  @Test
  @DisplayName("label(V, O) can give multiple objects different label")
  public void addLabelMultipleVerticesDifferent() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    graph.label(v1, "label");
    graph.label(v2, 1);
    graph.label(v3, 4.7);
    assertEquals("label", graph.label(v1));
    assertEquals(1, graph.label(v2));
    assertEquals(4.7, graph.label(v3));
  }
  // TODO label(Vertex, Object)
    // exception null vertex +
    // labels vertex correctly +
    // label multiple objects same +
    // label multiple objects different +
    // node is part of different graph +

  // label(Edge, Object) tests
  @Test
  @DisplayName("label(E, O) throws exception null edge")
  public void addLabelExceptionNullEdge() {
    try {
      graph.label((Edge<String>) null, "");
      fail("The expected exception was not thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("label(E, O) throws exception vertex from different graph")
  public void addLabelExceptionEdgeFromDifferentGraph() {
    Graph<String, String> g = createGraph();
    Vertex<String> v1 = g.insert("v1");
    Vertex<String> v2 = g.insert("v2");
    Edge<String> e = g.insert(v1, v2, "e");
    try {
      graph.label(e, "v1");
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("label(E, O) returns correct origin")
  public void addLabelCorrectlyAddsLabelToEdge() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v1, v2, "e");
    graph.label(e, "label");
    assertEquals("label", graph.label(e));
  }

  @Test
  @DisplayName("label(E, O) can give multiple objects same label")
  public void addLabelMultipleEdgesSame() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    Edge<String> e1 = graph.insert(v1, v2, "e1");
    Edge<String> e2 = graph.insert(v2, v3, "e2");
    Edge<String> e3 = graph.insert(v3, v1, "e3");
    graph.label(e1, "label");
    graph.label(e2, "label");
    graph.label(e3, "label");
    assertEquals("label", graph.label(e1));
    assertEquals("label", graph.label(e2));
    assertEquals("label", graph.label(e3));
  }

  @Test
  @DisplayName("label(E, O) can give multiple objects different label")
  public void addLabelMultipleEdgesDifferent() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    Edge<String> e1 = graph.insert(v1, v2, "e1");
    Edge<String> e2 = graph.insert(v2, v3, "e2");
    Edge<String> e3 = graph.insert(v3, v1, "e3");
    graph.label(e1, "label");
    graph.label(e2, 1);
    graph.label(e3, 4.7);
    assertEquals("label", graph.label(e1));
    assertEquals(1, graph.label(e2));
    assertEquals(4.7, graph.label(e3));
  }
  // TODO label(Edge, Object)
    // exception null edge +
    // labels edge correctly +
    // label multiple objects same +
    // label multiple objects different +
    // node is part of different graph +

  // label(Vertex) tests
  @Test
  @DisplayName("label(V) throws exception null vertex")
  public void getLabelExceptionNullVertex() {
    try {
      graph.label((Vertex<String>) null);
      fail("The expected exception was not thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("label(V) throws exception vertex from different graph")
  public void getLabelExceptionVertexFromDifferentGraph() {
    Graph<String, String> g = createGraph();
    Vertex<String> v1 = g.insert("v1");
    g.label(v1, "v1");
    try {
      graph.label(v1);
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("label(V) of unlabeled vertex is null")
  public void getLabelUnlabeledVertexNull() {
    Vertex<String> v1 = graph.insert("v1");
    assertNull(graph.label(v1));
  }

  @Test
  @DisplayName("label(V) of vertex is correct")
  public void getLabelVertexCorrectResult() {
    Vertex<String> v1 = graph.insert("v1");
    graph.label(v1, "labelv1");
    assertEquals("labelv1", graph.label(v1));
  }
  // TODO label(Vertex)
    // exception null vertex +
    // null for unlabeled vertex +
    // correct label for vertex +
    // diff vertices correct label
    // node is part of different graph +

  // label(Edge) tests
  @Test
  @DisplayName("label(V) throws exception null vertex")
  public void getLabelExceptionNullEdge() {
    try {
      graph.label((Edge<String>) null);
      fail("The expected exception was not thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("label(V) throws exception vertex from different graph")
  public void getLabelExceptionEdgeFromDifferentGraph() {
    Graph<String, String> g = createGraph();
    Vertex<String> v1 = g.insert("v1");
    Vertex<String> v2 = g.insert("v2");
    Edge<String> e = g.insert(v1, v2, "e");
    g.label(e, "v1");
    try {
      graph.label(e);
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("label(V) of unlabeled edge is null")
  public void getLabelUnlabeledEdgeNull() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v1, v2, "e");
    assertNull(graph.label(e));
  }

  @Test
  @DisplayName("label(E) of edge is correct")
  public void getLabelEdgeCorrectResult() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v1, v2, "e");
    graph.label(e, "labele");
    assertEquals("labele", graph.label(e));
  }
  // TODO label(Edge)
    // exception null edge +
    // null for unlabeled edge +
    // correct label for edge +
    // diff edges correct label
    // node is part of different graph +

  // clearLabels() tests
  @Test
  @DisplayName("clearLabels() works on empty graph")
  public void clearLabelsEmptyGraph() {
    graph.clearLabels();
    int count = 0;
    for (Vertex<String> v : graph.vertices()) {
      count++;
    }
    for (Edge<String> e : graph.edges()) {
      count++;
    }
    assertEquals(0, count);
  }

  @Test
  @DisplayName("clearLabels() works on unlabeled graph")
  public void clearLabelsAllUnlabeled() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    graph.insert(v1, v2, "e1");
    graph.insert(v2, v1, "e2");
    graph.clearLabels();
    int count = 0;
    for (Vertex<String> v : graph.vertices()) {
      count++;
      assertNull(graph.label(v));
    }
    for (Edge<String> e : graph.edges()) {
      count++;
      assertNull(graph.label(e));
    }
    assertEquals(4, count);
  }

  @Test
  @DisplayName("clearLabels() clears labeled vertices")
  public void clearLabelsLabeledVertices() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    graph.label(v1, "label1");
    graph.label(v2, 2);

    graph.insert(v1, v2, "e1");
    graph.insert(v2, v1, "e2");

    graph.clearLabels();
    int count = 0;
    for (Vertex<String> v : graph.vertices()) {
      count++;
      assertNull(graph.label(v));
    }
    for (Edge<String> e : graph.edges()) {
      count++;
      assertNull(graph.label(e));
    }
    assertEquals(4, count);
  }

  @Test
  @DisplayName("clearLabels() clears labeled edges")
  public void clearLabelsLabeledEdges() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");

    Edge<String> e1 = graph.insert(v1, v2, "e1");
    Edge<String> e2 = graph.insert(v2, v1, "e2");
    graph.label(e1, "label1");
    graph.label(e2, 2);

    graph.clearLabels();
    int count = 0;
    for (Vertex<String> v : graph.vertices()) {
      count++;
      assertNull(graph.label(v));
    }
    for (Edge<String> e : graph.edges()) {
      count++;
      assertNull(graph.label(e));
    }
    assertEquals(4, count);
  }

  @Test
  @DisplayName("clearLabels() clears all labels of different types")
  public void clearLabelsMultipleOfDifferentTypes() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    graph.label(v1, 4.6);
    graph.label(v2, true);

    Edge<String> e1 = graph.insert(v1, v2, "e1");
    Edge<String> e2 = graph.insert(v2, v1, "e2");
    graph.label(e1, "label1");
    graph.label(e2, 2);

    graph.clearLabels();
    int count = 0;
    for (Vertex<String> v : graph.vertices()) {
      count++;
      assertNull(graph.label(v));
    }
    for (Edge<String> e : graph.edges()) {
      count++;
      assertNull(graph.label(e));
    }
    assertEquals(4, count);
  }
  // TODO clearLabels()
    // no edges/vertices +
    // only vertices, unlabeled +
    // only vertices, labeled +
    // multiple edges and vertices, labeled and unlabeled +
}
