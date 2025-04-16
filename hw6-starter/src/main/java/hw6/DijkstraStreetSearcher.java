package hw6;

import org.apache.commons.math3.util.Pair;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraStreetSearcher extends StreetSearcher {

  private HashMap<String, Pair<Vertex<String>, Double>> distance;


  /**
   * Creates a StreetSearcher object.
   *
   * @param graph an implementation of Graph ADT.
   */
  public DijkstraStreetSearcher(Graph<String, String> graph) {
    super(graph);
    distance = new HashMap<>();
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

    double totalDist = -1;
    // handle same start and endpoint
    if (start == end) {
      totalDist = 0;
    } else {
      totalDist = dijkstra(startName, endName);
    }

    print(end, start, totalDist);
  }

  private double dijkstra(String startName, String endName) {
    PriorityQueue<Pair<Vertex<String>, Double>> pq = new PriorityQueue<>(new DistanceComparator());
    HashMap<String, Boolean> explored = new HashMap<>();

    insertDistance(pq, vertices.get(startName), 0.0);

    for (int i = 0; i < vertices.size(); i++) {
      // find unexplored vertex with the smallest distance
      Pair<Vertex<String>, Double> min = pq.poll();

      // mark found vertex as explored
      explored.put(min.getFirst().get(), true);

      // loop over outgoing edges from vertex
      for (Edge<String> e : graph.outgoing(min.getFirst())) {
        Vertex<String> to = graph.to(e);
        // for unexplored vertices check if shorter path found
        if (!explored.containsKey(to.get())) {
          Double dist = min.getSecond() + (Double)(graph.label(e));

          // if found shorter path update distance and previous
          if (dist < distance.getOrDefault(to.get(), new Pair<>(to, MAX_DISTANCE)).getSecond()) {
            insertDistance(pq, to, dist);
            graph.label(to, e);           // update previous
          }
        }
      }
    }
    return distance.get(endName).getSecond();
  }

  // update distance of a given vertex
  private void insertDistance(PriorityQueue<Pair<Vertex<String>, Double>> pq, Vertex<String> v, Double dist) {
    pq.remove(distance.get(v.get()));    // remove if already in queue
    Pair<Vertex<String>, Double> p = new Pair<>(v, dist);
    distance.put(v.get(), p); // update distance
    pq.add(p);       // add by updated priority to queue
  }

  // contain print logic
  private void print(Vertex<String> end, Vertex<String> start, Double totalDist) {
    // These method calls will create and print the path for you
    List<Edge<String>> path = getPath(end, start);
    if (VERBOSE) {
      printPath(path, totalDist);
    }
  }

  static class DistanceComparator implements Comparator<Pair<Vertex<String>, Double>> {
    @Override
    public int compare(Pair<Vertex<String>, Double> obj1, Pair<Vertex<String>, Double> obj2) {
      return Double.compare(obj1.getSecond(), obj2.getSecond());
    }
  }
}
