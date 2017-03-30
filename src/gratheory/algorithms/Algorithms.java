package gratheory.algorithms;
import gratheory.main.Graph;
import gratheory.main.Vertex;

import java.util.*;


/*******************************************************************
 *  CLASS BFS(Breadth First Search)
 *
 *  OVERVIEW: Class that performs a breadth first traversal on a given
 *  graph and start vertex.
 *
 *  CONSTRUCTOR PARAMETERS:
 *      graph (Graph)  : graph to perform BFS on.
 *      start (Vertex) : starting vertex to perform algorithm.
 *
 ********************************************************************/
public final class Algorithms {

    private Algorithms(){}

    public static void BFS(Graph graph, Vertex start) {
        /* BFS algorithm */
        Queue<Vertex> queue = new LinkedList<>();
        HashSet<Vertex> visited = new HashSet<>();
        queue.add(start);
        while(!queue.isEmpty()){
            int row = queue.peek().getId();
            for(int i = 0; i < graph.getVertexCount(); i++){
                if(graph.getIndex(row,i) == 1 && !visited.contains(graph.getVertex(i)) && !queue.contains(graph.getVertex(i))) {
                    queue.add(graph.getVertex(i));
                    // color edges
                    graph.getEdge(queue.peek(), graph.getVertex(i)).setColor(graph.parent().color(0,191,255));
                }
            }
            visited.add(queue.peek());
            queue.remove();
        }
    }

    public static void DFS(Graph graph, Vertex start) {

        /* DFS algorithm */
        Stack<Vertex> vertexStack = new Stack<>();
        ArrayList<Vertex> visited = new ArrayList<>();
        vertexStack.push(start);

        while(!vertexStack.empty()){
            Vertex v = vertexStack.pop();
            if(!visited.contains(v)){
                visited.add(v);

                for(int i = 0; i < graph.getVertexCount(); i++){
                    if(graph.getIndex(v.getId(),i) == 1) {
                        vertexStack.push(graph.getVertex(i));
                    }
                }
            }
        }

        // color edges
        for(int i = 0; i < visited.size()-1; i++){
            Vertex a = graph.getVertex(visited.get(i).getId());
            Vertex b = graph.getVertex(visited.get(i+1).getId());
            graph.getEdge(a,b).setColor(graph.parent().color(0,191,255));
        }
    }

    // Brute force Hamiltonian algorithm complexity : Worst case O(N!)
    public static void HamiltonianCircuit(Graph graph){
        hamCycle(graph.getMatrix());
    }

    /* A recursive utility function to solve hamiltonian
    cycle problem */
    private static boolean hamCycleUtil(int graph[][], int node, LinkedList<Integer> visited)
    {
        if(visited.contains(node)){
            if(visited.size()==graph.length && node==0){
                visited.add(node);
                return true;
            }
            return false;
        }

        visited.add(node);
        int[] list = graph[node];
        for(int i=0;i<list.length;i++){
            if(list[i]==1 && hamCycleUtil(graph,i,visited)){
                return true;
            }
        }
        visited.pollLast();
        return false;
    }

    /* This function solves the Hamiltonian Cycle problem using
    Backtracking. It mainly uses hamCycleUtil() to solve the
    problem. It returns false if there is no Hamiltonian Cycle
    possible, otherwise return true and prints the path.
    Note that there may be more than one solutions,
    this function prints one of the feasible solutions. */
    private static int hamCycle(int graph[][])
    {
        LinkedList<Integer> visited = new LinkedList<Integer>();
        if (hamCycleUtil(graph, 0, visited))
        {
            System.out.println("\nA Hamiltonian path exists");
            for(int i : visited){
                System.out.print(" "+i);
            }
        }else{
            System.out.println("\nA Hamiltonian path does not exist");
        }

        return 1;
    }
}
