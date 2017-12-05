import java.util.*;

import static java.util.Objects.requireNonNull;

public class DefaultKShortestPathFinder<V> implements AbstractKShortestPathFinder<V> {

    @Override
    public List<Path<V>> findShortestPaths(V source, V target, Graph<V> graph, int k) {
        requireNonNull(source, "The source node is null.");
        requireNonNull(target, "The target node is null.");
        requireNonNull(graph, "The graph is null.");
        checkK(k);

        List<Path<V>> paths = new ArrayList<>(k);
        Map<V, Integer> countMap = new HashMap<>();
        Queue<Path<V>> HEAP = new PriorityQueue<>(
                Comparator.comparingDouble(Path::pathCost));

        HEAP.add(new Path<>(source));

        while (!HEAP.isEmpty() && countMap.getOrDefault(target, 0) < k) {
            Path<V> currentPath = HEAP.remove();
            V endNode = currentPath.getEndNode();

            countMap.put(endNode, countMap.getOrDefault(endNode, 0) + 1);

            if (endNode.equals(target)) {
                paths.add(currentPath);
            }

            if (countMap.get(endNode) <= k) {
                for (Edge<V> edge : graph.get(endNode)) {
                    Path<V> path = currentPath.append(edge);
                    HEAP.add(path);
                }
            }
        }

        return paths;
    }
}