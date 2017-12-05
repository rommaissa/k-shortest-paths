import java.util.*;

import static java.lang.String.*;

public class Graph<V> {

    private Map<V,Map<V,Edge<V>>> vertexEdgeMap = new HashMap<>();

    @SafeVarargs
    public Graph(Edge<V> ... edges) {
        for (Edge<V> edge : edges) {
            addEdge(edge);
        }
    }

    private void addEdge(Edge<V> edge) {
        vertexEdgeMap.putIfAbsent(edge.from, new HashMap<>());
        Map<V, Edge<V>> fromMap = vertexEdgeMap.get(edge.from);
        if(fromMap.containsKey(edge.to)) {
            throw new IllegalArgumentException(format("Edge between %s and %s was added twice", edge.from, edge.to));
        }
        fromMap.put(edge.to, edge);
    }

    public Edge<V> get(V from, V to) {
        return vertexEdgeMap.get(from).get(to);
    }

    public Collection<Edge<V>> get(V from) {
        return vertexEdgeMap.getOrDefault(from, Collections.emptyMap()).values();
    }
    
    public HashSet<V> getVertices() {
    	HashSet<V> f = new HashSet<V>(); 
    	for(Map.Entry<V, Map<V,Edge<V>>> data : vertexEdgeMap.entrySet()) {
    		f.add(data.getKey());
    		for(Map.Entry<V, Edge<V>> missedNode : data.getValue().entrySet()) {
    			f.add((V) missedNode.getKey());
    		}
    	}
    	
    	return f;
    	
    }

}