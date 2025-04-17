package hw6;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import org.apache.commons.math3.util.Pair;

public class DijkstraStreetSearcher extends StreetSearcher {
  /**
   * Creates a StreetSearcher object.
   *
   * @param graph an implementation of Graph ADT.
   */
  public DijkstraStreetSearcher(Graph<String, String> graph) {
    super(graph);
  }

  @Override
  public void findShortestPath(String startName, String endName) {
    // handle invalid endpoints
    try {
      checkValidEndpoint(startName);
      checkValidEndpoint(endName);
    } catch (IllegalArgumentException e) {
      System.out.println("ERROR: " + e.getMessage());
      return;
    }

    Vertex<String> start = vertices.get(startName);
    Vertex<String> end = vertices.get(endName);

    double totalDist = 0;
    // handle same start and endpoint
    if (start != end) {
      totalDist = dijkstra(startName, endName);
    }

    print(end, start, totalDist);
  }

  // implement dijkstra's algorithm
  private double dijkstra(String startName, String endName) {
    // initialize tracker data structures
    HashMap<String, Pair<Vertex<String>, Double>> distance = new HashMap<>();
    PriorityQueue<Pair<Vertex<String>, Double>> pq = new PriorityQueue<>(new DistanceComparator());
    HashMap<String, Boolean> explored = new HashMap<>();

    // insert start node
    insertDistance(pq, distance, vertices.get(startName), 0.0);

    while (!pq.isEmpty()) {
      // find unexplored vertex with the smallest distance
      Pair<Vertex<String>, Double> min = pq.poll();
      if (explored.containsKey(min.getFirst().get())) {
        continue;
      }

      // mark found vertex as explored
      explored.put(min.getFirst().get(), true);
      checkOutgoingEdges(pq, distance, explored, min);
    }

    // default -1 if path not found
    return distance.getOrDefault(endName, new Pair<>(null, -1.0)).getSecond();
  }

  // check outgoing edges of vertex for shorter path
  private void checkOutgoingEdges(PriorityQueue<Pair<Vertex<String>, Double>> pq,
                                  HashMap<String, Pair<Vertex<String>, Double>> distance,
                                  HashMap<String, Boolean> explored,
                                  Pair<Vertex<String>, Double> v) {
    for (Edge<String> e : graph.outgoing(v.getFirst())) {
      Vertex<String> to = graph.to(e);
      // for unexplored vertices check if shorter path found
      if (!explored.containsKey(to.get())) {
        Double dist = v.getSecond() + (Double)(graph.label(e));

        // if found shorter path update distance and previous
        if (dist < distance.getOrDefault(to.get(), new Pair<>(null, MAX_DISTANCE)).getSecond()) {
          insertDistance(pq, distance, to, dist);
          graph.label(to, e);           // update previous
        }
      }
    }
  }

  // update distance of a given vertex
  private void insertDistance(PriorityQueue<Pair<Vertex<String>, Double>> pq,
                              HashMap<String, Pair<Vertex<String>, Double>> distance,
                              Vertex<String> v, Double dist) {
    Pair<Vertex<String>, Double> p = new Pair<>(v, dist);
    distance.put(v.get(), p);
    pq.add(p);
  }

  // contain print logic
  private void print(Vertex<String> end, Vertex<String> start, Double totalDist) {
    // These method calls will create and print the path for you
    List<Edge<String>> path = getPath(end, start);
    if (VERBOSE) {
      printPath(path, totalDist);
    }
  }

  // comparator for distances of two vertices
  static class DistanceComparator implements Comparator<Pair<Vertex<String>, Double>> {
    @Override
    public int compare(Pair<Vertex<String>, Double> obj1, Pair<Vertex<String>, Double> obj2) {
      return Double.compare(obj1.getSecond(), obj2.getSecond());
    }
  }
}
