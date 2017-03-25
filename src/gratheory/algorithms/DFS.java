package gratheory.algorithms;
import gratheory.main.Graph;
import gratheory.main.Vertex;
import java.util.ArrayList;
import java.util.Stack;

/*******************************************************************
 *  CLASS DFS(Depth First Search)
 *
 *  OVERVIEW: Class that performs a depth first traversal on a given
 *  graph and start vertex.
 *
 *  CONSTRUCTOR PARAMETERS:
 *      graph (Graph)  : graph to perform BFS on.
 *      start (Vertex) : starting vertex to perform algorithm.
 *
 ********************************************************************/
public class DFS {

    public DFS(Graph graph, Vertex start) {

        /* DFS algorithm */
        Stack<Vertex> vertexStack = new Stack<>();
        ArrayList<Vertex> visited = new ArrayList<>();
        vertexStack.push(start);

        while(!vertexStack.empty()){
            Vertex v = vertexStack.pop();
            if(!visited.contains(v)){
                visited.add(v);

                for(int i = 0; i < graph.columns(); i++){
                    if(graph.getIndex(v.getId(),i) == 1) {
                        vertexStack.push(graph.getVertex(i));
                    }
                }
            }
        }

        /* Color edges */
        for(int i = 0; i < visited.size()-1; i++){
            Vertex a = graph.getVertex(visited.get(i).getId());
            Vertex b = graph.getVertex(visited.get(i+1).getId());
            graph.getEdge(a,b).setColor(graph.parent().color(0,191,255));
        }
    }

}
