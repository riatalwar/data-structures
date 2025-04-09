package hw6;

import exceptions.PositionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class GraphTest {

  protected Graph<String, String> graph;

  @BeforeEach
  public void setupGraph() {
    this.graph = createGraph();
  }

  protected abstract Graph<String, String> createGraph();

  @Test
  @DisplayName("insert(v) returns a vertex with given data")
  public void canGetVertexAfterInsert() {
    Vertex<String> v1 = graph.insert("v1");
    assertEquals(v1.get(), "v1");
  }

  @Test
  @DisplayName("insert(U, V, e) returns an edge with given data")
  public void canGetEdgeAfterInsert() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e1 = graph.insert(v1, v2, "v1-v2");
    assertEquals(e1.get(), "v1-v2");
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

  // TODO add more tests here.
  // TODO insert(V) tests
    // exception if vertex in graph
    // null vertex exception
    // inserted vertex has no outgoing
    // inserted vertex has no incoming
    // vertex has null label after creation
    // insert multiple elements all accessible

  // TODO insert(V, V, E) tests
    // exception invalid first vertex
    // exception invalid second vertex
    // exception invalid both vertex
    // exception self loo
    // exception duplicate edge
    // insert one edge succeeds
    // insert multiple edges succeeds
    // insert both directions

  // TODO remove(Vertex)
    // exception null vertex
    // exception null data
    // exception incident edges
    // remove one vertex returns data
    // vertex no longer in vertices
    // remove multiple vertices

  // TODO remove(Edge)
    // exception null edge
    // exception null data
    // remove one edge returns data
    // edge no longer in edges
    // remove multiple edges

  // TODO vertices()
    // iterator no vertices
    // iterator one vertex
    // iterator multiple vertices

  // TODO edges()
    // iterator no vertices
    // iterator one vertex
    // iterator multiple vertices

  // TODO outgoing(Vertex)
    // exception null vertex
    // no outgoing/incoming
    // no outgoing/yes incoming
    // one outgoing/no incoming
    // multiple outgoing/no incoming
    // multiple outgoing/incoming

  // TODO incoming(Vertex)
    // exception null vertex
    // no outgoing/incoming
    // no incoming/yes outgoing
    // one incoming/no outgoing
    // multiple incoming/no outgoing
    // multiple incoming/outgoing

  // TODO from(Edge)
    // exception invalid edge
    // returns correct vertex
    // listed as outgoing from vertex

  // TODO to(Edge)
    // exception invalid edge
    // returns correct vertex
    // listed as incoming to vertex

  // TODO label(Vertex, Object)
    // exception invalid vertex
    // labels vertex correctly
    // label multiple objects same
    // label multiple objects different

  // TODO label(Edge, Object)
    // exception invalid edge
    // labels edge correctly
    // label multiple objects same
    // label multiple objects different

  // TODO label(Vertex)
    // exception invalid vertex
    // null for unlabeled vertex
    // correct label for vertex
    // diff vertices correct label

  // TODO label(Edge)
    // exception invalid edge
    // null for unlabeled edge
    // correct label for edge
    // diff edges correct label

  // TODO clearLabels()
    // no edges/vertices
    // only vertices, unlabeled
    // only vertices, labeled
    // multiple edges and vertices, labeled and unlabeled
}
