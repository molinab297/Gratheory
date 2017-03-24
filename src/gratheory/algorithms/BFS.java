package gratheory.algorithms;
import gratheory.main.Graph;
import gratheory.main.Vertex;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


/*******************************************************************
 *  CLASS BFS(Breadth First Search)
 *
 *  OVERVIEW: Class that performs a breadth first traversal on a given
 *  graph and start vertex.
 *
 *  CONSTRUCTOR PARAMETERS:
 *      graph<Graph>  : graph to perform BFS on.
 *      start<Vertex> : starting vertex to perform algorithm.
 *
 ********************************************************************/
public class BFS {

    public BFS(Graph graph, Vertex start){

        /* BFS algorithm */
        Queue<Vertex> queue = new LinkedList<>();
        HashSet<Vertex> visited = new HashSet<>();
        queue.add(start);
        while(!queue.isEmpty()){
            int row = queue.peek().id();
            for(int i = 0; i < graph.columns(); i++){
                if(graph.getIndex(row,i) == 1 && !visited.contains(graph.getVertex(i)) && !queue.contains(graph.getVertex(i))) {
                    queue.add(graph.getVertex(i));
                    int highlight = graph.parent().color(0,191,255); /* Set highlight color to sky blue */
                    graph.getEdge(queue.peek(), graph.getVertex(i)).setColor(highlight); /* Indicate which edges were used */
                }
            }
            visited.add(queue.peek());
            queue.remove();
        }
    }
}
