package gratheory.main;
import processing.core.PApplet;

import java.util.ArrayList;
import static processing.core.PApplet.str;

/* Class which acts as both a container and drawer for all vertices and edges in a graph.
*
* CONSTRUCTOR INPUTS:
*   int [][] otherMatrix - A Graph object only needs a single piece of information. An adjancy matrix representation
*   of a graph. This provides the information necessary to create all vertices and edges in the graph.
*
* */
public class Graph {

    private ArrayList<Vertex> vertexList; // Contains all the vertices in the graph
    private ArrayList<Edge> edgeList; // Contains all the edges in the graph
    private int [][]matrix; // Holds an adjancy matrix version of the graph, for initialization purposes.
    private int rows, columns;

    public Graph(int [][] otherMatrix){
        columns = otherMatrix[0].length;
        rows = otherMatrix.length;
        matrix = new int[rows][columns];

        /* Performs a shallow copy of otherMatrix to this.matrix. */
        for (int i = 0; i < otherMatrix.length; i++) {
            System.arraycopy(otherMatrix[i], 0, matrix[i], 0, otherMatrix[0].length);
        }

        initializeGraph();
    }

    /* Creates all the vertices with their positions and sizes on the graph. */
    private void initializeGraph(){
        vertexList = new ArrayList<Vertex>();
        edgeList   = new ArrayList<Edge>();
        int temp = 25;
        for (int x = 0; x < rows; x++)
            vertexList.add(new Vertex(temp % (x + 1) * x + temp * (x + 1),
                    temp % (x + 1) * temp + temp * (x + 1), 25, 25, 100));
    }

    /* Draws the Graph. Only call within the draw() method of AlgoGraph. */
    public void display(PApplet p) {
        drawEdges(p);
        drawVertices(p);
    }

    /* Draws all vertices in the graph to the screen */
    private void drawVertices(PApplet p){
        for (int x = 0; x < rows; x++) {
            vertexList.get(x).display(p);
            p.fill(255, 255, 0);
            p.textAlign(p.CENTER, p.CENTER);
            p.text(str(x), vertexList.get(x).getXCoord(), vertexList.get(x).getYCoord());
        }
    }

    /* Draws all edges in the graph to the screen. */
    public void drawEdges(PApplet p){
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < x; y++) {
                if (matrix[x][y] == 1) {
                    p.line(vertexList.get(x).getXCoord(), vertexList.get(x).getYCoord(), vertexList.get(y).getXCoord(), vertexList.get(y).getYCoord());
                }
            }
        }
    }

    /* Resets the graph by reassigning all vertices to their initial coordinates. */
    public void reset(){
        int temp = 25;
        for (int x = 0; x < rows; x++) {
            vertexList.get(x).setXCoord(temp % (x + 1) * x + temp * (x + 1));
            vertexList.get(x).setYCoord(temp % (x + 1) * temp + temp * (x + 1));
        }
    }

    void addVertex(int x, int y, int w, int h, int c){
        Vertex newVertex = new Vertex(x,y,w,h,c);
        vertexList.add(newVertex);
    }

    void addVertex(Vertex newVertex){
        vertexList.add(newVertex);
    }

    void addEdge(Vertex s, Vertex d, int c, int w){
        Edge newEdge = new Edge(s,d,c,w);
        edgeList.add(newEdge);
    }

    void addEdge(Edge newEdge){
        edgeList.add(newEdge);
    }

    public Vertex getVertex(int pos){
        return vertexList.get(pos);
    }

    public Edge getEdge(int pos){
        return edgeList.get(pos);
    }

    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }

}
