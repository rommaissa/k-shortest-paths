import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;



public class Execution {

    public static void main(String[] args) {
        execution();
    }

    private static void execution() {
 
        Graph<Character> graph = new Graph<>(
                new Edge<>('a', 'b', 1.0),
                new Edge<>('a', 'c', 1.0),
                new Edge<>('b', 'd', 2.0),
                new Edge<>('b', 'e', 1.0),
                new Edge<>('d', 'e', 0.0),
                new Edge<>('c', 'd', 1.0),
                new Edge<>('d', 'g', 1.0),
                new Edge<>('d', 'f', 1.0),
                new Edge<>('c', 'f', 1.0),
                new Edge<>('e', 'g', 1.0),
                new Edge<>('f', 'g', 1.0)
        );
        
        HashSet<Character> vertices = graph.getVertices();
        
        Character source = 'a';
        
        for(Character v:vertices) {
        	if(v != source) {
        		
        		List<Path<Character>> paths = new DefaultKShortestPathFinder<Character>().findShortestPaths(source, v.charValue(), graph, 100);
        		List<Path> ShortestPaths = new ArrayList<Path>();
                
                double min = paths.get(0).pathCost();
                
                System.out.println("Tous le chemins: du sommet [" + source + " vers " + v + "]");
                int j=0;
                for(Path<Character> path:paths) {
                	j++;
                	System.out.println("Chemin numero: "+ j + " " +path.toString() + " cout: " + path.pathCost());
                }
                
                System.out.println("En gros on a "+j);
                
                for(Path<Character> path:paths) {
                	if(path.pathCost() == min) {
                		ShortestPaths.add(path);
                	}
                }
                
                
                System.out.println("------------------------------");
                System.out.println("shortest paths are");
                int i = 0;
                for(Path p:ShortestPaths) {
                	i++;
                	System.out.println("Chemin numero: "+i + " " +p + " cout: " + p.pathCost());
                }
                
                System.out.println("En gros on a "+i);
                System.out.println("------------------------------");
            
           }
        		
        	}
       
    }
}

