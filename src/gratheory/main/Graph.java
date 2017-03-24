package gratheory.main;
import processing.core.PApplet;
import java.util.ArrayList;
import static processing.core.PApplet.str;
/*******************************************************************
 *  CLASS Graph
 *
 *  OVERVIEW: Class which acts as both a container and drawer for
 *  all vertices and edges in a graph.
 *
 *  CONSTRUCTOR PARAMETERS:
 *   otherMatrix<int[][]> : A Graph object only needs a single piece of
 *   information. An adjancy matrix representation of a graph. This
 *   provides the information necessary to create all vertices and edges
 *   in the graph.

 ********************************************************************/
public class Graph {

    private PApplet parent;
    private ArrayList<Vertex> vertexList; // Contains all the vertices in the graph
    private ArrayList<Edge> edgeList; // Contains all the edges in the graph
    private int [][]matrix; // Holds an adjancy matrix version of the graph, for initialization purposes.
    private final int ROWS, COLUMNS;

    public Graph(int [][] otherMatrix, PApplet parent){
        this.parent = parent;
        COLUMNS = otherMatrix[0].length;
        ROWS = otherMatrix.length;
        matrix = new int[ROWS][COLUMNS];

        for (int i = 0; i < otherMatrix.length; i++) {
            System.arraycopy(otherMatrix[i], 0, matrix[i], 0, otherMatrix[0].length);
        }

        initializeGraph();
    }

    /* Creates all the vertices with their positions and sizes on the graph. */
    private void initializeGraph(){
        vertexList = new ArrayList<>();
        edgeList   = new ArrayList<>();
        int temp = 25;

        /* Initializes vertices */
        for (int x = 0; x < ROWS; x++)
            vertexList.add(new Vertex(temp % (x + 1) * x + temp * (x + 1),
                    temp % (x + 1) * temp + temp * (x + 1), 25, 25, 100, x));

        /* Initializes edges */
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLUMNS; y++) {
                if (matrix[x][y] == 1 && !edgeExist(vertexList.get(x),vertexList.get(y))) {
                        edgeList.add(new Edge(vertexList.get(x), vertexList.get(y), 255, -1));
                }
            }
        }

    }

    /* Draws all vertices in the graph to the screen */
    private void drawVertices(){
        int counter = 0;
        for(Vertex v: vertexList){
            v.display(parent);
            parent.fill(255, 255, 0);
            parent.textAlign(parent.CENTER, parent.CENTER);
            parent.text(str(counter), v.xCoord(), v.yCoord());
            counter++;
        }
    }

    /* Draws all edges in the graph to the screen. */
    private void drawEdges(){
        for(Edge e: edgeList)
            e.display(parent);
    }

    /* Draws the Graph. Only call within the draw() method of Gratheory. */
    public void display() {
        drawEdges();
        drawVertices();
    }


    void addVertex(int x, int y, int w, int h, int c, int id){
        Vertex newVertex = new Vertex(x,y,w,h,c, id);
        vertexList.add(newVertex);
    }

    void addVertex(Vertex newVertex){
        vertexList.add(newVertex);
    }

    void addEdge(Vertex s, Vertex d, int c, int w){
        edgeList.add(new Edge(s,d,c,w));
    }

    /* Given two vertices, this function returns whether or a not an edge connects them. */
    public boolean edgeExist(Vertex a, Vertex b){
        for(Edge e : edgeList){
            if(e.source().equals(b) && e.dest().equals(a) ||
                    e.source().equals(a) && e.dest().equals(b)){
                return true;
            }
        }
        return false;
    }

    /* Returns the incident edge between vertex 'A' and vertex 'B' */
    public Edge getEdge(Vertex a, Vertex b){
        for(Edge e : edgeList){
            if(e.source().equals(a) && e.dest().equals(b)){
                return edgeList.get(edgeList.indexOf(e));
            }
        }
        return null;
    }

    public Vertex getVertex(int pos){
        return vertexList.get(pos);
    }

    public final int rows() {
        return ROWS;
    }
    public final int columns() {
        return COLUMNS;
    }

    /* Returns index of the graph's adjancy matrix. */
    public int getIndex(int x, int y) { return matrix[x][y]; }

    public PApplet parent() { return parent; }

}
