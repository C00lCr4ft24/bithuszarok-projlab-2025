package fungorium;

import java.util.*;

public class Graph<T> {

    private final HashMap<T, Set<T>> adjacencyList;

    public Graph() { this.adjacencyList = new HashMap<>(); }

    public void addVertex(T vertex) { if (!adjacencyList.containsKey(vertex)) { adjacencyList.put(vertex, new HashSet<>()); } }

    public void addEdge(T v1, T v2) {
        if (!adjacencyList.containsKey(v1)) { addVertex(v1); }
        if (!adjacencyList.containsKey(v2)) { addVertex(v2); }
        adjacencyList.get(v1).add(v2);
        adjacencyList.get(v2).add(v1);
    }

    public void removeVertex(T v) {
        if (adjacencyList.containsKey(v)) {
            for (T neighbor : adjacencyList.get(v)) {
                adjacencyList.get(neighbor).remove(v);
            }
            adjacencyList.remove(v);
        }
    }

    public void removeEdge(T v1, T v2) {
        if (adjacencyList.containsKey(v1)) { adjacencyList.get(v1).remove(v2); }
        if (adjacencyList.containsKey(v2)) { adjacencyList.get(v2).remove(v1); }
    }

    public Set<T> getAllVertices() { return adjacencyList.keySet(); }

    public Set<T> getNeighbors(T v) {
        return adjacencyList.getOrDefault(v, Collections.emptySet());
    }

    public boolean isAdjacent(T v1, T v2) { return adjacencyList.containsKey(v1) && adjacencyList.get(v1).contains(v2); }

    public void printGraph() {
        for (T vertex : adjacencyList.keySet()) {
            System.out.print("Vertex " + vertex + ": ");
            for (T neighbor : adjacencyList.get(vertex)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
}
