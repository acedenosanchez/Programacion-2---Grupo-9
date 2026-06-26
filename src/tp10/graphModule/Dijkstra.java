package tp10.graphModule;

import tp02.tp03.listModule.SimpleArrayList;
import tp02.tp03.listModule.SimpleList;
import tp07Dictionary.dictionary.SimpleArrayDictionary;

public class Dijkstra {

    private static final int INFINITY = 999999;

    public Gps shortestPath(Graph<String> graph, String origin, String destination) {
        SimpleArrayDictionary<String, Integer> distances = new SimpleArrayDictionary<String, Integer>();
        SimpleArrayDictionary<String, String> previous = new SimpleArrayDictionary<String, String>();
        SimpleArrayList<String> unvisited = new SimpleArrayList<String>();

        SimpleList<String> vertices = graph.vertices();

        for (int i = 0; i < vertices.size(); i++) {
            String vertex = vertices.get(i);
            distances.put(vertex, INFINITY);
            unvisited.add(vertex);
        }

        distances.put(origin, 0);

        while (unvisited.size() > 0) {
            String current = getVertexWithMinimumDistance(unvisited, distances);

            if (current == null) {
                break;
            }

            removeFromList(unvisited, current);

            if (current.equals(destination)) {
                break;
            }

            SimpleList<String> neighbors = graph.getNeighbors(current);

            for (int i = 0; i < neighbors.size(); i++) {
                String neighbor = neighbors.get(i);

                int currentDistance = distances.get(current);
                int edgeWeight = graph.getWeight(current, neighbor);

                if (currentDistance == INFINITY || edgeWeight < 0) {
                    continue;
                }

                int newDistance = currentDistance + edgeWeight;

                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    previous.put(neighbor, current);
                }
            }
        }

        int finalDistance = distances.get(destination);

        if (finalDistance == INFINITY) {
            return new Gps(new SimpleArrayList<String>(), -1);
        }

        SimpleList<String> path = buildPath(previous, origin, destination);

        return new Gps(path, finalDistance);
    }

    private String getVertexWithMinimumDistance(
            SimpleList<String> unvisited,
            SimpleArrayDictionary<String, Integer> distances
    ) {
        String minimumVertex = null;
        int minimumDistance = INFINITY;

        for (int i = 0; i < unvisited.size(); i++) {
            String vertex = unvisited.get(i);
            int distance = distances.get(vertex);

            if (distance < minimumDistance) {
                minimumDistance = distance;
                minimumVertex = vertex;
            }
        }

        return minimumVertex;
    }

    private void removeFromList(SimpleList<String> list, String value) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(value)) {
                list.remove(i);
                return;
            }
        }
    }

    private SimpleList<String> buildPath(
            SimpleArrayDictionary<String, String> previous,
            String origin,
            String destination
    ) {
        SimpleArrayList<String> reversedPath = new SimpleArrayList<String>();

        String current = destination;

        while (current != null) {
            reversedPath.add(current);

            if (current.equals(origin)) {
                break;
            }

            if (!previous.containsKey(current)) {
                current = null;
            } else {
                current = previous.get(current);
            }
        }

        SimpleArrayList<String> path = new SimpleArrayList<String>();

        for (int i = reversedPath.size() - 1; i >= 0; i--) {
            path.add(reversedPath.get(i));
        }

        return path;
    }
}